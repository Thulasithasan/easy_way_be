package com.thulasi.easyway.payload.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class RecurringOrderItemResponseDto {
    private Long recurringOrderItemId;
    private List<ProductNameResponseDto> nameTranslations;
    private String heroImageSignedUrl;
    private BigDecimal measurementPrice;
    private BigDecimal quantity;
}
