package com.thulasi.easyway.controller.v1;

import com.thulasi.easyway.payload.request.ProductFilterRequestDto;
import com.thulasi.easyway.payload.request.ProductRequestDto;
import com.thulasi.easyway.payload.response.ResponseEntityDto;
import com.thulasi.easyway.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.route53.endpoints.internal.Value;

@RestController
@RequestMapping("/v1/products")
@Tag(name = "Product Controller", description = "Endpoints for products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Create product", description = "Create a new product")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> createProduct(@Valid @RequestBody ProductRequestDto dto) {
        ResponseEntityDto response = productService.createProduct(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get product by id", description = "Get product by id")
    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> getProductById(@PathVariable Long id) {
        ResponseEntityDto response = productService.getProductById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Filter products", description = "Filter products")
    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> filterProducts(@Valid ProductFilterRequestDto dto) {
        ResponseEntityDto response = productService.filterProducts(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Upload product image", description = "Upload an image for a specific product")
    @PutMapping(value = "/{id}/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> uploadProductImage(@PathVariable Long id, @RequestParam Boolean isHeroImage, @RequestParam MultipartFile image) {
        ResponseEntityDto response = productService.uploadProductImage(id, isHeroImage, image);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Delete product image", description = "Delete an image from a specific product")
    @DeleteMapping(value = "/{id}/images", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> deleteProductImage(@PathVariable Long id, @RequestParam Boolean isHeroImage, @RequestParam String key) {
        ResponseEntityDto response = productService.deleteProductImage(id, isHeroImage, key);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
