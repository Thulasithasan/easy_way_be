package com.thulasi.easyway.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubCategoryRequestDto {

	@NotBlank(message = "Name is required")
	@Schema(description = "Name of the category", example = "Electronics")
	private String name;

	@Schema(description = "Description of the category", example = "Electronics category")
	private String description;

	@NotNull(message = "Category id is required")
	@Schema(description = "Category id of the sub category", example = "1")
	private Long categoryId;

}
