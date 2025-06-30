package com.thulasi.easyway.service;

import com.thulasi.easyway.payload.request.SalesOrderRequestDto;
import com.thulasi.easyway.payload.response.ResponseEntityDto;
import jakarta.transaction.Transactional;

public interface SalesOrderService {

    ResponseEntityDto createSalesOrder(SalesOrderRequestDto dto);
}
