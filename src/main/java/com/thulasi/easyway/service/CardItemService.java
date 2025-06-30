package com.thulasi.easyway.service;

import com.thulasi.easyway.payload.response.ResponseEntityDto;

public interface CardItemService {
    ResponseEntityDto addToCard(Long productId);

    ResponseEntityDto removeFromCart(Long productId);

    ResponseEntityDto getMyCard();
}
