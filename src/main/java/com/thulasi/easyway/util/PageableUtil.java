package com.thulasi.easyway.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageableUtil {

    public static Pageable buildPageable(Integer pageNumber, Integer pageSize) {
        int page = (pageNumber != null && pageNumber >= 0) ? (pageNumber - 1) : 0;
        int size = (pageSize != null && pageSize > 0) ? pageSize : 10;
        return PageRequest.of(page, size);
    }
}
