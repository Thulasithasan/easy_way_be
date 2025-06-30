package com.thulasi.easyway.service.impl;

import com.thulasi.easyway.component.S3ImageHandler;
import com.thulasi.easyway.constant.CommonMessageConstant;
import com.thulasi.easyway.exception.ModuleException;
import com.thulasi.easyway.model.*;
import com.thulasi.easyway.payload.response.*;
import com.thulasi.easyway.repository.CardItemRepository;
import com.thulasi.easyway.repository.FavouriteItemRepository;
import com.thulasi.easyway.repository.ProductRepository;
import com.thulasi.easyway.repository.StockRepository;
import com.thulasi.easyway.service.CardItemService;
import com.thulasi.easyway.service.UserService;
import com.thulasi.easyway.util.transformer.PageTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardItemServiceImpl implements CardItemService {

    private final StockRepository stockRepository;
    private final S3ImageHandler s3ImageHandler;
    private final UserService userService;
    private final ProductRepository productRepository;
    private final CardItemRepository cardItemRepository;

    @Override
    public ResponseEntityDto addToCard(Long productId) {
        log.info("addToCard: execution started");

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ModuleException(CommonMessageConstant.COMMON_ERROR_PRODUCT_NOT_FOUND));

        User user = userService.getCurrentUser();

        CardItem cardItem = cardItemRepository.findByUserIdAndProductId(user.getId(), product.getId())
                .orElse(CardItem.builder()
                        .userId(user.getId())
                        .product(product)
                        .quantity(BigDecimal.ZERO)
                        .build());

        cardItem.setQuantity(cardItem.getQuantity().add(BigDecimal.ONE));

        cardItemRepository.save(cardItem);

        log.info("addToCard: execution completed");
        return new ResponseEntityDto("Added to card successfully", true);
    }

    @Override
    public ResponseEntityDto removeFromCart(Long productId) {
        log.info("removeFromCart: execution started");
        User user = userService.getCurrentUser();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ModuleException(CommonMessageConstant.COMMON_ERROR_PRODUCT_NOT_FOUND));

        CardItem item = cardItemRepository.findByUserIdAndProductId(user.getId(), product.getId())
                .orElseThrow(() -> new ModuleException(CommonMessageConstant.COMMON_ERROR_CARD_ITEM_NOT_FOUND));

        if (item.getQuantity().compareTo(BigDecimal.ONE) > 0) {
            item.setQuantity(item.getQuantity().subtract(BigDecimal.ONE));
            cardItemRepository.save(item);
        } else {
            cardItemRepository.delete(item);
        }
        log.info("removeFromCart: execution completed");
        return new ResponseEntityDto("Removed to card successfully", true);
    }


    @Override
    public ResponseEntityDto getMyCard() {
        log.info("getMyCard: execution started");
        User currentUser = userService.getCurrentUser();
        List<CardItem> cardItems = cardItemRepository.findByUserId(currentUser.getId());

        BigDecimal totalAmount = BigDecimal.ZERO;

        List<CardItemResponseDto> cardItemResponseDtos = new ArrayList<>();

        for (CardItem cardItem : cardItems) {
            Product product = cardItem.getProduct();

            Stock stock = stockRepository.findByProductId(product.getId())
                    .orElseThrow(() -> new ModuleException(CommonMessageConstant.COMMON_ERROR_STOCK_NOT_FOUND));

            BigDecimal price = getDefaultSellingPrice(stock);
            BigDecimal itemTotal = price.multiply(cardItem.getQuantity());

            totalAmount = totalAmount.add(itemTotal);

            cardItemResponseDtos.add(CardItemResponseDto.builder()
                    .nameTranslations(mapToProductNameResponseDtos(product.getNameTranslations()))
                    .heroImageSignedUrl(s3ImageHandler.getImageUrl(product.getHeroImage()))
                    .measurementPrice(price)
                    .quantity(cardItem.getQuantity())
                    .build());
        }

        CardResponseDto cardResponseDto = CardResponseDto.builder()
                .cardItemResponseDtos(cardItemResponseDtos)
                .totalAmount(totalAmount)
                .build();

        log.info("getMyCard: execution completed");
        return new ResponseEntityDto(true, cardResponseDto);
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
