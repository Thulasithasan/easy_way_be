package com.thulasi.easyway.payload.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductNameResponseDto {
    private String language;
    private String name;
}
