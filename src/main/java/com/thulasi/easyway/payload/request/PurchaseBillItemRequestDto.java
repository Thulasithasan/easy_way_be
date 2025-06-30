package com.thulasi.easyway.payload.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PurchaseBillItemRequestDto {
    private Long productId;
    private BigDecimal quantity;
    private BigDecimal unitBuyingPrice;
}
