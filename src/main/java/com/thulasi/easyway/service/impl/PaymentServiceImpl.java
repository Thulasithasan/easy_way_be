package com.thulasi.easyway.service.impl;

import com.thulasi.easyway.constant.CommonMessageConstant;
import com.thulasi.easyway.exception.ModuleException;
import com.thulasi.easyway.model.Invoice;
import com.thulasi.easyway.model.Payment;
import com.thulasi.easyway.model.SalesOrder;
import com.thulasi.easyway.payload.request.PaymentRequestDto;
import com.thulasi.easyway.payload.response.ResponseEntityDto;
import com.thulasi.easyway.repository.InvoiceRepository;
import com.thulasi.easyway.repository.PaymentRepository;
import com.thulasi.easyway.repository.SalesOrderRepository;
import com.thulasi.easyway.type.PaymentStatus;
import com.thulasi.easyway.type.SalesOrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.thulasi.easyway.service.PaymentService;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;
    private SalesOrderRepository salesOrderRepository;
    private InvoiceRepository invoiceRepository;

    @Override
    public ResponseEntityDto createPayment(PaymentRequestDto dto) {
        log.info("createProductPrice: execution started");

        Invoice invoice = invoiceRepository.findById(dto.getInvoiceId())
                .orElseThrow(() -> new ModuleException(CommonMessageConstant.COMMON_ERROR_INVOICE_NOT_FOUND));

        Payment payment = Payment.builder()
                .invoice(invoice)
                .paymentAmount(dto.getAmount())
                .paymentMethod(dto.getPaymentMethod())
                .paymentStatus(PaymentStatus.COMPLETED)
                .build();

        paymentRepository.save(payment);

        SalesOrder salesOrder = invoice.getSalesOrder();
        salesOrder.setStatus(SalesOrderStatus.PAYMENT_COMPLETED);

        log.info("createProductPrice: execution completed");
        return new ResponseEntityDto("Payment created successfully", true);
    }
}
