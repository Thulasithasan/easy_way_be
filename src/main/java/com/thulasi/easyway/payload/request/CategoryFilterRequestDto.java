package com.thulasi.easyway.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

@Data
public class CategoryFilterRequestDto {

	@Schema(description = "The category name", example = "Category 1")
	private String categoryName;

	@Schema(description = "The page number", example = "1")
	private Integer pageNumber;

	@Schema(description = "The page size", example = "10")
	private Integer pageSize;

}
