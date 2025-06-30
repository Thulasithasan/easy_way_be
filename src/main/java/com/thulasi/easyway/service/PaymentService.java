package com.thulasi.easyway.service;

import com.thulasi.easyway.payload.request.PaymentRequestDto;
import com.thulasi.easyway.payload.response.ResponseEntityDto;

public interface PaymentService {

    ResponseEntityDto createPayment(PaymentRequestDto dto);
}
