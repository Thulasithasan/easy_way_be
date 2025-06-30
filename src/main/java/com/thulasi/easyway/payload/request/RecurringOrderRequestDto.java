package com.thulasi.easyway.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RecurringOrderRequestDto {
    @NotBlank(message = "Name is required")
    @Schema(description = "Recurring order name", example = "monthly")
    private String name;

    @Schema(description = "Note for the recurring order", example = "Recurring order on the 10th of every month")
    private String note;
}
