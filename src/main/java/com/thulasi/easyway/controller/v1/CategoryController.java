package com.thulasi.easyway.controller.v1;

import com.thulasi.easyway.payload.request.CategoryFilterRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.thulasi.easyway.payload.request.CategoryRequestDto;
import com.thulasi.easyway.payload.response.ResponseEntityDto;
import com.thulasi.easyway.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/categories")
@Tag(name = "Category Controller", description = "Endpoints for categories")
@Validated
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "Create category", description = "Create a new category")
	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseEntityDto> createCategory(@Valid @RequestBody CategoryRequestDto dto) {
		ResponseEntityDto response = categoryService.createCategory(dto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "Get all categories", description = "Get all categories")
	@GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseEntityDto> getAll() {
		ResponseEntityDto response = categoryService.getAllCategories();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "Get a category by id", description = "Get a category by id")
	@GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseEntityDto> getById(@PathVariable Long id) {
		ResponseEntityDto response = categoryService.getCategoryById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "Get categories by filter", description = "Get categories by filter")
	@GetMapping(value = "/get-by-filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseEntityDto> getCategoriesByFilter(@Valid CategoryFilterRequestDto dto) {
		ResponseEntityDto response = categoryService.filterCategories(dto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
