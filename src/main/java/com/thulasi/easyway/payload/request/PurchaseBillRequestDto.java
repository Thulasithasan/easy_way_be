package com.thulasi.easyway.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class PurchaseBillRequestDto {
    @Schema(description = "Supplier's name", example = "ABC Traders")
    @NotBlank(message = "Supplier name must not be blank")
    private String supplierName;

    @Schema(description = "Bill number", example = "BILL-202306")
    @NotBlank(message = "Bill number must not be blank")
    private String billNumber;

    @Schema(description = "Bill date", example = "2024-06-25")
    @NotNull(message = "Bill date must not be null")
    private LocalDate billDate;

    @Schema(description = "Total bill amount", example = "12500.50")
    @NotNull(message = "Total amount must not be null")
    private BigDecimal totalAmount;

    @Schema(description = "Additional notes", example = "Paid in full by cash")
    private String note;
}
