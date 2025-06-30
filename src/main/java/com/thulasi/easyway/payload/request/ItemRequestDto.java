package com.thulasi.easyway.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemRequestDto {
    @NotBlank(message = "Product id is required")
    @Schema(description = "Product id", example = "1")
    private Long productId;

    @NotNull(message = "Quantity is required")
    @Schema(description = "Quantity of the product", example = "10.5")
    private BigDecimal quantity;
}
