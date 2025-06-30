package com.thulasi.easyway.payload.request;

import com.thulasi.easyway.payload.response.ProductNameResponseDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FavouriteItemResponseDto {
    private Long id;
    private List<ProductNameResponseDto> productNameResponseDtos;
    private String heroImageSignedUrl;
}
