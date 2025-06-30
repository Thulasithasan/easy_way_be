package com.thulasi.easyway.service.impl;

import com.thulasi.easyway.constant.CommonMessageConstant;
import com.thulasi.easyway.exception.ModuleException;
import com.thulasi.easyway.model.*;
import com.thulasi.easyway.payload.request.PriceRequestDto;
import com.thulasi.easyway.payload.response.ResponseEntityDto;
import com.thulasi.easyway.repository.*;
import com.thulasi.easyway.service.PriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
@Slf4j
public class PriceServiceImpl implements PriceService {

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final PriceRepository priceRepository;

    @Override
    public ResponseEntityDto createProductPrice(PriceRequestDto dto) {
        log.info("createProductPrice: execution started");

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ModuleException(CommonMessageConstant.COMMON_ERROR_PRODUCT_NOT_FOUND));

        Stock stock = stockRepository.findByProductId(product.getId())
                .orElseThrow(() -> new ModuleException(CommonMessageConstant.COMMON_ERROR_STOCK_NOT_FOUND));

        BigDecimal finalPrice = dto.getActualPrice();

        if (dto.getDiscountAmount().compareTo(BigDecimal.ZERO) > 0) {
            finalPrice = finalPrice.subtract(dto.getDiscountAmount());
        } else if (dto.getDiscountPercent().compareTo(BigDecimal.ZERO) > 0) {
            finalPrice = finalPrice.subtract(
                    dto.getActualPrice().multiply(dto.getDiscountPercent())
                            .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP));
        }

        Price price = Price.builder()
                .stock(stock)
                .quantity(dto.getQuantity())
                .unit(dto.getUnit())
                .actualPrice(dto.getActualPrice())
                .discountAmount(dto.getDiscountAmount())
                .discountPercent(dto.getDiscountPercent())
                .finalPrice(finalPrice)
                .isDefault(dto.getIsDefault())
                .build();

        priceRepository.save(price);
        log.info("createProductPrice: execution completed");

        return new ResponseEntityDto("Product price created successfully", true);
    }

//    @Override
//    public ResponseEntityDto findByProductId(Long productId) {
//        log.info("findByProductId: execution started");
//
//        log.info("findByProductId: execution completed");
//        return new ResponseEntityDto("Product price created successfully", true);
//    }
}
