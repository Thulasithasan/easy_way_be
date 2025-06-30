package com.thulasi.easyway.payload.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BuyingPriceDetail {
    private BigDecimal quantity;
    private BigDecimal unitBuyingPrice;
}
