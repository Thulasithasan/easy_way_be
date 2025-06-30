package com.thulasi.easyway.service;

import com.thulasi.easyway.payload.request.CategoryFilterRequestDto;
import com.thulasi.easyway.payload.request.CategoryRequestDto;
import com.thulasi.easyway.payload.response.ResponseEntityDto;

public interface CategoryService {

	ResponseEntityDto createCategory(CategoryRequestDto categoryRequestDto);

	ResponseEntityDto getAllCategories();

	ResponseEntityDto getCategoryById(Long id);

	ResponseEntityDto filterCategories(CategoryFilterRequestDto categoryFilterRequestDto);

}
