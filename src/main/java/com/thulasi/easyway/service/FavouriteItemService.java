package com.thulasi.easyway.service;

import com.thulasi.easyway.payload.response.ResponseEntityDto;

public interface FavouriteItemService {
    ResponseEntityDto addFavourite(Long productId);

    ResponseEntityDto removeFavourite(Long productId);

    ResponseEntityDto getMyFavouteItems();
}
