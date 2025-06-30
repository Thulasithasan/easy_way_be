package com.thulasi.easyway.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class StockFilterRequestDto {

	@Schema(description = "The product name", example = "prodcut 1")
	private String productName;

	@Schema(description = "The subCategory id", example = "1")
	private Long subCategoryId;

	@Schema(description = "The category id", example = "1")
	private Long categoryId;

	@Schema(description = "The page number", example = "1")
	private Integer pageNumber;

	@Schema(description = "The page size", example = "10")
	private Integer pageSize;

	@Schema(hidden = true)
	@JsonIgnore
	public String getProductNameLike() {
		if (productName == null || productName.isBlank()) {
			return null;
		}
		return "%" + productName + "%";
	}

}
