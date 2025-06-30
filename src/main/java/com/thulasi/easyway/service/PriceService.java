package com.thulasi.easyway.service;

import com.thulasi.easyway.payload.request.PriceRequestDto;
import com.thulasi.easyway.payload.response.ResponseEntityDto;

public interface PriceService {

    ResponseEntityDto createProductPrice(PriceRequestDto dto);
}
