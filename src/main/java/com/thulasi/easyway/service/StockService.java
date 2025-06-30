package com.thulasi.easyway.service;

import com.thulasi.easyway.payload.request.StockFilterRequestDto;
import com.thulasi.easyway.payload.response.ResponseEntityDto;

public interface StockService {

    ResponseEntityDto getHomeProducts(StockFilterRequestDto dto);

    ResponseEntityDto getHomeProductInfo(Long productId);

    ResponseEntityDto filterStock(StockFilterRequestDto dto);
}
