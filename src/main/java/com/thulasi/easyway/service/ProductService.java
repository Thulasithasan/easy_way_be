package com.thulasi.easyway.service;

import com.thulasi.easyway.model.Product;
import com.thulasi.easyway.payload.request.ProductFilterRequestDto;
import com.thulasi.easyway.payload.request.ProductRequestDto;
import com.thulasi.easyway.payload.response.ResponseEntityDto;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

	ResponseEntityDto createProduct(ProductRequestDto productRequestDto);

	ResponseEntityDto getProductById(Long id);

	ResponseEntityDto filterProducts(ProductFilterRequestDto productFilterRequestDto);

	ResponseEntityDto uploadProductImage(Long productId, boolean isHeroImage, MultipartFile file);

	ResponseEntityDto deleteProductImage(Long productId, boolean isHeroImage, String key);

    Product getProduct(Long productId);
}
