package com.thulasi.easyway.payload.request;

import java.math.BigDecimal;
import java.util.Map;

import com.thulasi.easyway.type.MeasurementUnit;

import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestDto {

	@NotEmpty(message = "Name translations must not be empty")
	@Schema(description = "Name of the product", example = "{\"en\": \"Product 1\", \"ta\": \"தயாரிப்பு 1\"}")
	private Map<String, String> nameTranslations;

	@NotBlank(message = "Description is required")
	@Schema(description = "Description of the product", example = "Description of the product")
	private String description;

	@NotNull(message = "Quantity is required")
	@Schema(description = "Quantity of the product", example = "10")
	private BigDecimal measurementValue;

	@NotNull(message = "Unit is required")
	@Schema(description = "Unit of the product", example = "PCS")
	private MeasurementUnit measurementUnit;

	@NotNull(message = "Sub category id is required")
	@Schema(description = "Sub category id of the product", example = "1")
	private Long subCategoryId;

}
