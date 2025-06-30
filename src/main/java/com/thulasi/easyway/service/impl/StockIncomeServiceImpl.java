package com.thulasi.easyway.service.impl;

import com.thulasi.easyway.constant.CommonMessageConstant;
import com.thulasi.easyway.exception.ModuleException;
import com.thulasi.easyway.model.Product;
import com.thulasi.easyway.model.PurchaseBill;
import com.thulasi.easyway.model.Stock;
import com.thulasi.easyway.model.StockIncome;
import com.thulasi.easyway.payload.request.StockIncomeRequestDto;
import com.thulasi.easyway.payload.response.ResponseEntityDto;
import com.thulasi.easyway.repository.ProductRepository;
import com.thulasi.easyway.repository.PurchaseBillRepository;
import com.thulasi.easyway.repository.StockIncomeRepository;
import com.thulasi.easyway.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.thulasi.easyway.service.StockIncomeService;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockIncomeServiceImpl implements StockIncomeService {

    private final StockIncomeRepository stockIncomeRepository;
    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final PurchaseBillRepository purchaseBillRepository;

    @Override
    public ResponseEntityDto createStockIncome(StockIncomeRequestDto dto) {
        log.info("createStockIncome: execution started");

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ModuleException(CommonMessageConstant.COMMON_ERROR_PRODUCT_NOT_FOUND));

        PurchaseBill purchaseBill = purchaseBillRepository.findById(dto.getPurchaseBillId())
                .orElseThrow(() -> new ModuleException(CommonMessageConstant.COMMON_ERROR_PURCHASE_BILL_NOT_FOUND));

        Stock stock = stockRepository.findByProductId(product.getId())
                .orElse(Stock.builder()
                        .product(product)
                        .quantity(BigDecimal.ZERO)
                        .reservedQuantity(BigDecimal.ZERO)
                        .isActive(true)
                        .build());

        stock.setQuantity(stock.getQuantity().add(dto.getQuantity()));

        stockRepository.save(stock);

        StockIncome income = StockIncome.builder()
                .stock(stock)
                .quantity(dto.getQuantity())
                .availableQuantity(dto.getQuantity())
                .unitBuyingPrice(dto.getUnitBuyingPrice())
                .totalBuyingAmount(dto.getQuantity().multiply(dto.getUnitBuyingPrice()))
                .purchaseBill(purchaseBill)
                .build();

        stockIncomeRepository.save(income);

        log.info("createStockIncome: execution completed");
        return new ResponseEntityDto("Stock income created successfully", true);
    }
}
