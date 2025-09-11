package com.thulasi.easyway.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableUtil {

    public static Pageable buildPageable(Integer pageNumber, Integer pageSize) {
        int page = (pageNumber != null && pageNumber >= 0) ? (pageNumber - 1) : 0;
        int size = (pageSize != null && pageSize > 0) ? pageSize : 10;
        return PageRequest.of(page, size);
    }

    public static Pageable buildPageable(Integer pageNumber, Integer pageSize, String orderBy, String direction) {
        int page = (pageNumber != null && pageNumber >= 0) ? (pageNumber - 1) : 0;
        int size = (pageSize != null && pageSize > 0) ? pageSize : 10;

        if (orderBy != null && !orderBy.isEmpty()) {
            Sort sort = "desc".equalsIgnoreCase(direction)
                    ? Sort.by(orderBy).descending()
                    : Sort.by(orderBy).ascending();
            return PageRequest.of(page, size, sort);
        }

        return PageRequest.of(page, size);
    }
}