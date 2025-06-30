package com.thulasi.easyway.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class StockRequestDto {
    @NotBlank(message = "Name is required")
    @Schema(description = "Name of the category", example = "Electronics")
    private Long productId;
}
