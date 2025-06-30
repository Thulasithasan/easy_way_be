package com.thulasi.easyway.payload.response;

import com.thulasi.easyway.type.MeasurementUnit;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class HomeProductsResponseDto {

    private Long productId;

    private List<ProductNameResponseDto> nameTranslations;

    private String description;

    private BigDecimal measurementValue;

    private MeasurementUnit measurementUnit;

    private BigDecimal measurementSellingPrice;

    private String heroImageSignedUrl;

    private List<String> images;

    private Boolean isFavourite;
}
