package com.thulasi.easyway.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProductFilterRequestDto {

	@Schema(description = "The product name", example = "Product 1")
	private String productName;

	@Schema(description = "The sub category id", example = "1")
	private Long subCategoryId;

	@Schema(description = "The category id", example = "1")
	private Long categoryId;

	@Schema(description = "The page number", example = "1")
	private Integer pageNumber;

	@Schema(description = "The page size", example = "10")
	private Integer pageSize;

}
