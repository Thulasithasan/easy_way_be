package com.thulasi.easyway.payload.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.thulasi.easyway.type.MeasurementUnit;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDto {

	private Long productId;

	private List<ProductNameResponseDto> nameTranslations;

	private String description;

	private BigDecimal measurementValue;

	private MeasurementUnit measurementUnit;

	private BigDecimal quantity;

	private BigDecimal measurementSellingPrice;

	private List<PriceResponseDto> priceResponseDtos;

	private String heroImageSignedUrl;

	private List<String> images;

	private Long subCategoryId;

}
