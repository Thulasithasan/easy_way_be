package com.thulasi.easyway.service.impl;

import com.thulasi.easyway.component.S3ImageHandler;
import com.thulasi.easyway.constant.CommonMessageConstant;
import com.thulasi.easyway.exception.ModuleException;
import com.thulasi.easyway.model.*;
import com.thulasi.easyway.payload.request.RecurringOrderRequestDto;
import com.thulasi.easyway.payload.response.*;
import com.thulasi.easyway.repository.*;
import com.thulasi.easyway.service.RecurringOrderItemService;
import com.thulasi.easyway.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecurringOrderItemServiceImpl implements RecurringOrderItemService {

    private final StockRepository stockRepository;
    private final S3ImageHandler s3ImageHandler;
    private final UserService userService;
    private final ProductRepository productRepository;
    private final RecurringOrderRepository recurringOrderRepository;
    private final RecurringOrderItemRepository recurringOrderItemRepository;

    @Override
    public ResponseEntityDto createRecurringOrder(RecurringOrderRequestDto dto) {
        log.info("createRecurringOrder: execution started");
        User currentUser = userService.getCurrentUser();

        recurringOrderRepository.save(RecurringOrder.builder()
                .userId(currentUser.getId())
                .name(dto.getName())
                .note(dto.getNote())
                .build());

        log.info("createRecurringOrder: execution completed");
        return new ResponseEntityDto("Added to card successfully", true);
    }

    @Override
    public ResponseEntityDto addToRecurringOrderItem(Long recurringOrderId, Long productId) {
        log.info("addToRecurringOrderItem: execution started");

        RecurringOrder recurringOrder = recurringOrderRepository.findById(recurringOrderId).orElseThrow(() ->
                new ModuleException(CommonMessageConstant.COMMON_ERROR_RECURRING_ORDER_NOT_FOUND));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ModuleException(CommonMessageConstant.COMMON_ERROR_PRODUCT_NOT_FOUND));

        RecurringOrderItem recurringOrderItem = recurringOrderItemRepository
                .findByProductIdAndRecurringOrderId(product.getId(), recurringOrderId).orElse(RecurringOrderItem.builder()
                        .recurringOrder(recurringOrder)
                        .product(product)
                        .quantity(BigDecimal.ONE)
                        .build());

        recurringOrderItem.setQuantity(recurringOrderItem.getQuantity().add(BigDecimal.ONE));

        recurringOrderItemRepository.save(recurringOrderItem);

        log.info("addToRecurringOrderItem: execution completed");
        return new ResponseEntityDto("Added to recurring order item successfully", true);
    }

    @Override
    public ResponseEntityDto removeFromRecurringOrderItem(Long recurringOrderId, Long productId) {
        log.info("removeFromRecurringOrderItem: execution started");

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ModuleException(CommonMessageConstant.COMMON_ERROR_PRODUCT_NOT_FOUND));

        RecurringOrderItem recurringOrderItem = recurringOrderItemRepository
                .findByProductIdAndRecurringOrderId(product.getId(), recurringOrderId)
                .orElseThrow(() -> new ModuleException(CommonMessageConstant.COMMON_ERROR_RECURRING_ORDER_ITEM_NOT_FOUND));

        if (recurringOrderItem.getQuantity().compareTo(BigDecimal.ONE) > 0) {
            recurringOrderItem.setQuantity(recurringOrderItem.getQuantity().subtract(BigDecimal.ONE));
            recurringOrderItemRepository.save(recurringOrderItem);
        } else {
            recurringOrderItemRepository.delete(recurringOrderItem);
        }
        log.info("removeFromRecurringOrderItem: execution completed");
        return new ResponseEntityDto("Removed to recurring order item successfully", true);
    }


    @Override
    public ResponseEntityDto getMyRecurringOrder() {
        log.info("getMyRecurringOrder: execution started");
        User currentUser = userService.getCurrentUser();
        List<RecurringOrder> recurringOrders = recurringOrderRepository.findByUserId(currentUser.getId());

        List<RecurringOrderResponseDto> recurringOrderResponseDtos = new ArrayList<>();
        for (RecurringOrder recurringOrder : recurringOrders) {
            RecurringOrderResponseDto recurringOrderResponseDto = RecurringOrderResponseDto.builder()
                    .name(recurringOrder.getName())
                    .note(recurringOrder.getNote())
                    .build();

            List<RecurringOrderItem> items = recurringOrder.getItems();

            BigDecimal totalAmount = BigDecimal.ZERO;
            List<RecurringOrderItemResponseDto> responseDtos = new ArrayList<>();
            for (RecurringOrderItem item : items) {
                Product product = item.getProduct();

                Stock stock = stockRepository.findByProductId(product.getId())
                        .orElseThrow(() -> new ModuleException(CommonMessageConstant.COMMON_ERROR_STOCK_NOT_FOUND));

                BigDecimal price = getDefaultSellingPrice(stock);
                BigDecimal itemTotal = price.multiply(item.getQuantity());

                totalAmount = totalAmount.add(itemTotal);
                responseDtos.add(RecurringOrderItemResponseDto.builder()
                        .nameTranslations(mapToProductNameResponseDtos(product.getNameTranslations()))
                        .heroImageSignedUrl(s3ImageHandler.getImageUrl(product.getHeroImage()))
                        .measurementPrice(price)
                        .quantity(item.getQuantity())
                        .build());
            }

            recurringOrderResponseDto.setRecurringOrderItemResponseDtos(responseDtos);
            recurringOrderResponseDtos.add(recurringOrderResponseDto);
        }

        log.info("getMyRecurringOrder: execution completed");
        return new ResponseEntityDto(true, recurringOrderResponseDtos);
    }

    private BigDecimal getDefaultSellingPrice(Stock stock) {
        return stock.getPrices().stream()
                .filter(Price::getIsDefault)
                .map(Price::getFinalPrice)
                .findFirst()
                .orElse(BigDecimal.ZERO);
    }

    private List<ProductNameResponseDto> mapToProductNameResponseDtos(List<ProductName> productNames) {
        return productNames.stream().map(this::mapToProductNameResponseDto).toList();
    }

    private ProductNameResponseDto mapToProductNameResponseDto(ProductName productName) {
        return ProductNameResponseDto.builder()
                .language(productName.getLanguage())
                .name(productName.getName())
                .build();
    }
}
