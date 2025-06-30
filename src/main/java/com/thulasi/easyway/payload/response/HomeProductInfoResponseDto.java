package com.thulasi.easyway.payload.response;

import com.thulasi.easyway.type.MeasurementUnit;
import software.amazon.awssdk.services.route53.endpoints.internal.Value;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class HomeProductInfoResponseDto {
    private Long productId;

    private Map<String, String> nameTranslations;

    private String description;

    private BigDecimal measurementValue;

    private MeasurementUnit measurementUnit;

    private BigDecimal measurementSellingPrice;

    private String heroImageSignedUrl;

    private List<String> imageSignedUrls;

    private List<PriceResponseDto> priceResponseDtos;

    private Boolean isFavourite;
}
