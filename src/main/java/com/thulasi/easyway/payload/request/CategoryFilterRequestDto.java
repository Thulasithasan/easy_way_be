package com.thulasi.easyway.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CategoryFilterRequestDto {

	@Schema(description = "Exact category name for filtering", example = "Category 1")
	private String categoryName;

	@Schema(description = "Keyword for pattern search in category name or description", example = "food")
	private String searchTerm;

	@Schema(description = "Whether the category is active", example = "true")
	private Boolean isActive;

	@Schema(description = "The page number (0-based)", example = "0")
	private Integer pageNumber;

	@Schema(description = "The page size", example = "10")
	private Integer pageSize;

}