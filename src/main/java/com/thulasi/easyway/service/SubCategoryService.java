package com.thulasi.easyway.service;

import com.thulasi.easyway.payload.request.SubCategoryFilterRequestDto;
import com.thulasi.easyway.payload.request.SubCategoryRequestDto;
import com.thulasi.easyway.payload.response.ResponseEntityDto;

public interface SubCategoryService {

    ResponseEntityDto createSubCategory(SubCategoryRequestDto subCategoryRequestDto);

    ResponseEntityDto getAllSubCategories();

    ResponseEntityDto getSubCategoryById(Long id);

    ResponseEntityDto filterSubCategories(SubCategoryFilterRequestDto dto);
}
