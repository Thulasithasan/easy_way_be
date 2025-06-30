package com.thulasi.easyway.service.impl;

import java.util.List;

import com.thulasi.easyway.model.SubCategory;
import com.thulasi.easyway.payload.response.SubCategoryResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.thulasi.easyway.payload.request.CategoryFilterRequestDto;
import com.thulasi.easyway.payload.request.CategoryRequestDto;
import com.thulasi.easyway.payload.response.ResponseEntityDto;
import com.thulasi.easyway.repository.CategoryRepository;
import com.thulasi.easyway.model.Category;
import com.thulasi.easyway.service.CategoryService;
import com.thulasi.easyway.util.transformer.PageTransformer;
import com.thulasi.easyway.constant.CommonMessageConstant;
import com.thulasi.easyway.exception.ModuleException;
import com.thulasi.easyway.payload.response.CategoryResponseDto;
import com.thulasi.easyway.payload.response.PageDto;

import static com.thulasi.easyway.util.PageableUtil.buildPageable;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final PageTransformer pageTransformer;

    @Override
    public ResponseEntityDto createCategory(CategoryRequestDto categoryRequestDto) {
        log.info("createCategory: execution started");
        Category category = Category.builder()
                .name(categoryRequestDto.getName())
                .description(categoryRequestDto.getDescription())
                .build();
        log.info("createCategory: execution completed");
        categoryRepository.save(category);
        return new ResponseEntityDto("Category created successfully", true);
    }

    @Override
    public ResponseEntityDto getAllCategories() {
        log.info("getAllCategories: execution started");
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseDto> categoryResponseDtos = categories.stream().map(this::mapToCategoryResponseDto).toList();
        log.info("getAllCategories: execution completed");
        return new ResponseEntityDto(true, categoryResponseDtos);
    }

    @Override
    public ResponseEntityDto getCategoryById(Long id) {
        log.info("getCategoryById: execution started");

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ModuleException(CommonMessageConstant.COMMON_ERROR_CATEGORY_NOT_FOUND));

        log.info("getCategoryById: execution completed");
        return new ResponseEntityDto(true, mapToCategoryResponseDto(category));
    }

    @Override
    public ResponseEntityDto filterCategories(CategoryFilterRequestDto categoryFilterRequestDto) {
        log.info("filterCategories: execution started");
        Page<Category> categories = categoryRepository.filterCategories(categoryFilterRequestDto.getCategoryName(),
                buildPageable(categoryFilterRequestDto.getPageNumber(), categoryFilterRequestDto.getPageSize()));

        PageDto pageDto = pageTransformer.transform(categories);

        pageDto.setItems(categories.getContent()
                .stream()
                .map(this::mapToCategoryResponseDto)
                .toList());

        log.info("filterCategories: execution ended");
        return new ResponseEntityDto(true, pageDto);
    }

    private CategoryResponseDto mapToCategoryResponseDto(Category category) {
        return CategoryResponseDto.builder()
                .categoryId(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .subCategoryResponseDtos(category.getSubCategories().stream().map(this::mapToSubCategoryResponseDto).toList())
                .build();
    }

    private SubCategoryResponseDto mapToSubCategoryResponseDto(SubCategory subCategory) {
        return SubCategoryResponseDto.builder()
                .subCategoryId(subCategory.getId())
                .name(subCategory.getName())
                .description(subCategory.getDescription())
                .build();
    }
}
