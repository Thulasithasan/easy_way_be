package com.thulasi.easyway.service;

import com.thulasi.easyway.payload.request.StockIncomeRequestDto;
import com.thulasi.easyway.payload.response.ResponseEntityDto;

public interface StockIncomeService {

    ResponseEntityDto createStockIncome(StockIncomeRequestDto dto);
}
