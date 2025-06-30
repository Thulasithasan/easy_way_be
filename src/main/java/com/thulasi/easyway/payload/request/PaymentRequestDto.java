package com.thulasi.easyway.payload.request;

import com.thulasi.easyway.type.PaymentMethod;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequestDto {
    private Long invoiceId;
    private PaymentMethod paymentMethod;
    private BigDecimal amount;
}
