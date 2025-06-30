package com.thulasi.easyway.payload.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class RecurringOrderResponseDto {
    private List<RecurringOrderItemResponseDto> recurringOrderItemResponseDtos;
    private BigDecimal totalAmount;
    private String name;
    private String note;
}
