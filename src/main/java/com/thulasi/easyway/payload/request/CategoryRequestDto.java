package com.thulasi.easyway.payload.request;

import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CategoryRequestDto {

	@NotBlank(message = "Name is required")
	@Schema(description = "Name of the category", example = "Electronics")
	private String name;

	@Schema(description = "Description of the category", example = "Electronics category")
	private String description;

}
