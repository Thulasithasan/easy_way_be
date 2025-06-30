package com.thulasi.easyway.payload.request;

import com.thulasi.easyway.type.MeasurementUnit;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PriceRequestDto {
    @NotNull(message = "Product id is required")
    @Schema(description = "Product id", example = "1")
    private Long productId;

    @Schema(description = "Quantity of the product", example = "5.00")
    @NotNull(message = "Quantity must not be null")
    private BigDecimal quantity;

    @Schema(description = "Measurement unit (e.g., KG, LITER, PIECE)", example = "KG")
    @NotNull(message = "Unit must not be null")
    private MeasurementUnit unit;

    @Schema(description = "Original price before discounts", example = "100.00")
    @NotNull(message = "Actual price must not be null")
    private BigDecimal actualPrice;

    @Schema(description = "Discount percentage (0 to 100)", example = "10.00")
    @NotNull(message = "Discount percent must not be null")
    private BigDecimal discountPercent = BigDecimal.ZERO;

    @Schema(description = "Discount amount in absolute value", example = "10.00")
    @NotNull(message = "Discount amount must not be null")
    private BigDecimal discountAmount = BigDecimal.ZERO;

    @NotNull(message = "isDefault must not be null")
    @Schema(description = "Indicates if this is default price", example = "false")
    private Boolean isDefault = false;
}
