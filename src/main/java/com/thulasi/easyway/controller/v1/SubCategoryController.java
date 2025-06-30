package com.thulasi.easyway.controller.v1;

import com.thulasi.easyway.payload.request.SubCategoryFilterRequestDto;
import com.thulasi.easyway.payload.request.SubCategoryRequestDto;
import com.thulasi.easyway.payload.response.ResponseEntityDto;
import com.thulasi.easyway.service.SubCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/sub-categories")
@Tag(name = "SubCategory Controller", description = "Endpoints for subCategories")
@Validated
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @Operation(summary = "Create category", description = "Create a new category")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> createCategory(@Valid @RequestBody SubCategoryRequestDto dto) {
        ResponseEntityDto response = subCategoryService.createSubCategory(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get all categories", description = "Get all categories")
    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> getAll() {
        ResponseEntityDto response = subCategoryService.getAllSubCategories();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get a category by id", description = "Get a category by id")
    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> getById(@PathVariable Long id) {
        ResponseEntityDto response = subCategoryService.getSubCategoryById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get categories by filter", description = "Get categories by filter")
    @GetMapping(value = "/get-by-filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> getCategoriesByFilter(@Valid SubCategoryFilterRequestDto dto) {
        ResponseEntityDto response = subCategoryService.filterSubCategories(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}


//[
//        {
//        "categoryId": 1,
//        "name": "Grains",
//        "description": "Grains",
//        "subCategoryResponseDtos": [
//        { "subCategoryId": 101, "name": "Rice" },
//        { "subCategoryId": 102, "name": "Wheat" }
//        ]
//        },
//        {
//        "categoryId": 2,
//        "name": "Dairy",
//        "description": "Dairy",
//        "subCategoryResponseDtos": [
//        { "subCategoryId": 201, "name": "Milk" },
//        { "subCategoryId": 202, "name": "Cheese" }
//        ]
//        },
//        {
//        "categoryId": 3,
//        "name": "Beverages",
//        "description": "Beverages",
//        "subCategoryResponseDtos": [
//        { "subCategoryId": 301, "name": "Juices" },
//        { "subCategoryId": 302, "name": "Soft Drinks" }
//        ]
//        },
//        {
//        "categoryId": 4,
//        "name": "Biscuits",
//        "description": "Biscuits",
//        "subCategoryResponseDtos": [
//        { "subCategoryId": 401, "name": "Cream Biscuits" },
//        { "subCategoryId": 402, "name": "Glucose Biscuits" }
//        ]
//        }
//        ]
