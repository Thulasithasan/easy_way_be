package com.thulasi.easyway.payload.response;

import com.thulasi.easyway.model.Stock;
import com.thulasi.easyway.type.MeasurementUnit;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PriceResponseDto {
    private Long priceId;
    private BigDecimal quantity;
    private MeasurementUnit unit;
    private BigDecimal actualPrice;
    private BigDecimal discountPercent;
    private BigDecimal discountAmount;
    private BigDecimal finalPrice;
    private Boolean isActive;
    private Boolean isDefault;
}
