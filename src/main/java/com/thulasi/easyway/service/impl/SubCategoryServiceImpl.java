package com.thulasi.easyway.service.impl;

import com.thulasi.easyway.constant.CommonMessageConstant;
import com.thulasi.easyway.exception.ModuleException;
import com.thulasi.easyway.model.Category;
import com.thulasi.easyway.model.SubCategory;
import com.thulasi.easyway.payload.request.SubCategoryFilterRequestDto;
import com.thulasi.easyway.payload.request.SubCategoryRequestDto;
import com.thulasi.easyway.payload.response.PageDto;
import com.thulasi.easyway.payload.response.ResponseEntityDto;
import com.thulasi.easyway.payload.response.SubCategoryResponseDto;
import com.thulasi.easyway.repository.SubCategoryRepository;
import com.thulasi.easyway.util.transformer.PageTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.thulasi.easyway.service.SubCategoryService;

import java.util.List;

import static com.thulasi.easyway.util.PageableUtil.buildPageable;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final PageTransformer pageTransformer;

    @Override
    public ResponseEntityDto createSubCategory(SubCategoryRequestDto subCategoryRequestDto) {
        log.info("createSubCategory: execution started");
        SubCategory subCategory = SubCategory.builder()
                .name(subCategoryRequestDto.getName())
                .description(subCategoryRequestDto.getDescription())
                .category(Category.builder().id(subCategoryRequestDto.getCategoryId()).build())
                .build();
        log.info("createSubCategory: execution completed");
        subCategoryRepository.save(subCategory);
        return new ResponseEntityDto("SubCategory created successfully", true);
    }

    @Override
    public ResponseEntityDto getAllSubCategories() {
        log.info("getAllCategories: execution started");
        List<SubCategory> subCategories = subCategoryRepository.findAll();
        log.info("getAllSubCategories: execution completed");
        return new ResponseEntityDto(true, subCategories);
    }

    @Override
    public ResponseEntityDto getSubCategoryById(Long id) {
        log.info("getSubCategoryById: execution started");
        SubCategory subCategory = subCategoryRepository.findById(id)
                .orElseThrow(() -> new ModuleException(CommonMessageConstant.COMMON_ERROR_SUBCATEGORY_NOT_FOUND));
        log.info("getSubCategoryById: execution completed");
        return new ResponseEntityDto(true, subCategory);
    }

    @Override
    public ResponseEntityDto filterSubCategories(SubCategoryFilterRequestDto dto) {
        log.info("filterSubCategories: execution started");
        Page<SubCategory> subCategories = subCategoryRepository.filterSubCategories(dto.getSubCategoryName(),
                buildPageable(dto.getPageNumber(), dto.getPageSize()));

        PageDto pageDto = pageTransformer.transform(subCategories);

        pageDto.setItems(subCategories.getContent()
                .stream()
                .map(subCategory -> SubCategoryResponseDto.builder()
                        .subCategoryId(subCategory.getId())
                        .name(subCategory.getName())
                        .description(subCategory.getDescription())
                        .build())
                .toList());

        log.info("filterSubCategories: execution ended");
        return new ResponseEntityDto(true, pageDto);
    }
}
