package com.thulasi.easyway.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class StockIncomeRequestDto {
    @NotNull(message = "Product id is required")
    @Schema(description = "Product id", example = "1")
    private Long productId;

    @Schema(description = "Purchase bill id", example = "1")
    private Long purchaseBillId;

    @NotNull(message = "Quantity is required")
    @Schema(description = "Quantity of the product", example = "10.5")
    private BigDecimal quantity;

    @NotNull(message = "Unit buying price is required")
    @Schema(description = "Unit buying price", example = "100.00")
    private BigDecimal unitBuyingPrice;
}
