package com.thulasi.easyway.payload.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class CardResponseDto {
    private List<CardItemResponseDto> cardItemResponseDtos;
    private BigDecimal totalAmount;
}
