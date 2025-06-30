package com.thulasi.easyway.payload.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubCategoryResponseDto {

	private Long subCategoryId;

	private String name;

	private String description;

}
