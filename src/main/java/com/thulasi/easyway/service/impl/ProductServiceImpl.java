package com.thulasi.easyway.service.impl;

import com.thulasi.easyway.component.S3ImageHandler;
import com.thulasi.easyway.exception.EntityNotFoundException;
import com.thulasi.easyway.model.*;
import com.thulasi.easyway.payload.response.*;
import com.thulasi.easyway.repository.FavouriteItemRepository;
import com.thulasi.easyway.repository.ProductNameRepository;
import com.thulasi.easyway.repository.StockRepository;
import com.thulasi.easyway.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thulasi.easyway.payload.request.ProductFilterRequestDto;
import com.thulasi.easyway.payload.request.ProductRequestDto;
import com.thulasi.easyway.service.ProductService;
import com.thulasi.easyway.repository.ProductRepository;
import com.thulasi.easyway.exception.ModuleException;
import com.thulasi.easyway.constant.CommonMessageConstant;
import com.thulasi.easyway.util.transformer.PageTransformer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.thulasi.easyway.util.PageableUtil.buildPageable;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final PageTransformer pageTransformer;
    private final S3ImageHandler s3ImageHandler;
    private final StockRepository stockRepository;
    private final FavouriteItemRepository favouriteItemRepository;
    private final UserService userService;
    private final ProductNameRepository productNameRepository;

    @Override
    public ResponseEntityDto createProduct(ProductRequestDto productRequestDto) {
        log.info("createProduct: execution started");

        Product product = Product.builder()
                .description(productRequestDto.getDescription())
                .measurementValue(productRequestDto.getMeasurementValue())
                .measurementUnit(productRequestDto.getMeasurementUnit())
                .subCategory(SubCategory.builder().id(productRequestDto.getSubCategoryId()).build())
                .images(new ArrayList<>())
                .build();

        Map<String, String> nameTranslations = productRequestDto.getNameTranslations();
        Product savedProduct = productRepository.save(product);

        List<ProductName> productNames = nameTranslations.entrySet().stream()
                .map(entry -> ProductName.builder()
                        .name(entry.getValue())
                        .language(entry.getKey())
                        .product(savedProduct)
                        .build())
                .toList();

        productNameRepository.saveAll(productNames);

        log.info("createProduct: execution completed");
        return new ResponseEntityDto("Product created successfully", true);
    }

    @Override
    public ResponseEntityDto getProductById(Long id) {
        log.info("getProductById: execution started");
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ModuleException(CommonMessageConstant.COMMON_ERROR_PRODUCT_NOT_FOUND));

        ProductResponseDto productResponseDto = mapToProductResponseDto(product);

        log.info("getProductById: execution completed");
        return new ResponseEntityDto(true, productResponseDto);
    }

    @Override
    public ResponseEntityDto filterProducts(ProductFilterRequestDto productFilterRequestDto) {
        log.info("filterProducts: execution started");

        Page<Product> products = productRepository.filterProducts(productFilterRequestDto.getProductName(),
                productFilterRequestDto.getSubCategoryId(), productFilterRequestDto.getCategoryId(),
                buildPageable(productFilterRequestDto.getPageNumber(), productFilterRequestDto.getPageSize()));

        PageDto pageDto = pageTransformer.transform(products);

        pageDto.setItems(products.getContent()
                .stream()
                .map(product -> ProductResponseDto.builder()
                        .productId(product.getId())
                        .nameTranslations(mapToProductNameResponseDtos(product.getNameTranslations()))
                        .description(product.getDescription())
                        .measurementValue(product.getMeasurementValue())
                        .measurementUnit(product.getMeasurementUnit())
                        .heroImageSignedUrl(getSignedUrl(product.getHeroImage()))
                        .subCategoryId(product.getSubCategory().getId())
                        .build())
                .toList());

        log.info("filterProducts: execution completed");
        return new ResponseEntityDto(true, pageDto);
    }

    @Override
    public ResponseEntityDto uploadProductImage(Long productId, boolean isHeroImage, MultipartFile file) {
        log.info("productImageUpload: execution started");

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(CommonMessageConstant.COMMON_ERROR_PRODUCT_NOT_FOUND));

        Map<String, String> uploadedImage = s3ImageHandler.uploadImage("products/", file);

        String imageKeyUrl = uploadedImage.get("key") + uploadedImage.get("url");
        if (isHeroImage) {
            product.setHeroImage(imageKeyUrl);
        } else {
            List<String> images = product.getImages();
            images.add(imageKeyUrl);
            product.setImages(images);
        }

        productRepository.save(product);

        log.info("productImageUpload: execution completed");
        return new ResponseEntityDto(true, uploadedImage);
    }

    @Override
    public ResponseEntityDto deleteProductImage(Long productId, boolean isHeroImage, String key) {
        log.info("productImageDelete: execution started");

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(CommonMessageConstant.COMMON_ERROR_PRODUCT_NOT_FOUND));

        s3ImageHandler.deleteImage(key);


        if (isHeroImage) {
            product.setHeroImage(null);
        } else {
            List<String> images = product.getImages();
            images.remove(key);

            product.setImages(images);
        }

        productRepository.save(product);

        log.info("productImageDelete: execution completed");
        return new ResponseEntityDto("Product image deleted successfully", true);
    }

    @Override
    public Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(CommonMessageConstant.COMMON_ERROR_PRODUCT_NOT_FOUND));
    }

    private ProductResponseDto mapToProductResponseDto(Product product) {
        String heroImageSignedUrl = product.getHeroImage() != null ?
                getSignedUrl(product.getHeroImage()) : null;

        List<String> signedUrlImages = product.getImages().stream()
                .map(this::getSignedUrl).toList();

        List<PriceResponseDto> priceResponseDtos = stockRepository.findByProductId(product.getId())
                .map(stock -> stock.getPrices().stream().map(this::mapToPriceResponseDto).toList())
                .orElse(List.of());

        return ProductResponseDto.builder()
                .productId(product.getId())
                .nameTranslations(mapToProductNameResponseDtos(product.getNameTranslations()))
                .description(product.getDescription())
                .measurementValue(product.getMeasurementValue())
                .measurementUnit(product.getMeasurementUnit())
                .heroImageSignedUrl(heroImageSignedUrl)
                .images(signedUrlImages)
                .subCategoryId(product.getSubCategory().getId())
                .priceResponseDtos(priceResponseDtos)
                .build();
    }

    private String getSignedUrl(String key) {
        return key != null ? s3ImageHandler.getImageUrl(key) : null;
    }

    private PriceResponseDto mapToPriceResponseDto(Price price) {
        return PriceResponseDto.builder()
                .priceId(price.getId())
                .actualPrice(price.getActualPrice())
                .quantity(price.getQuantity())
                .unit(price.getUnit())
                .discountPercent(price.getDiscountPercent())
                .discountAmount(price.getDiscountAmount())
                .finalPrice(price.getFinalPrice())
                .build();
    }

    private List<ProductNameResponseDto> mapToProductNameResponseDtos(List<ProductName> productNames) {
        return productNames.stream().map(this::mapToProductNameResponseDto).toList();
    }

    private ProductNameResponseDto mapToProductNameResponseDto(ProductName productName) {
        return ProductNameResponseDto.builder()
                .language(productName.getLanguage())
                .name(productName.getName())
                .build();
    }
}
