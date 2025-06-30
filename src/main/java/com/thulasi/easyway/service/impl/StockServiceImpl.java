package com.thulasi.easyway.service.impl;

import com.thulasi.easyway.component.S3ImageHandler;
import com.thulasi.easyway.constant.CommonMessageConstant;
import com.thulasi.easyway.exception.ModuleException;
import com.thulasi.easyway.model.*;
import com.thulasi.easyway.payload.request.StockFilterRequestDto;
import com.thulasi.easyway.payload.response.*;
import com.thulasi.easyway.repository.FavouriteItemRepository;
import com.thulasi.easyway.repository.StockRepository;
import com.thulasi.easyway.service.UserService;
import com.thulasi.easyway.util.transformer.PageTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.thulasi.easyway.service.StockService;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.thulasi.easyway.util.PageableUtil.buildPageable;

@Service
@Slf4j
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final PageTransformer pageTransformer;
    private final S3ImageHandler s3ImageHandler;
    private final UserService userService;
    private final FavouriteItemRepository favouriteItemRepository;

    @Override
    public ResponseEntityDto getHomeProducts(StockFilterRequestDto dto) {
        log.info("getHomeProducts: execution started");

        Optional<User> optionalCurrentUser = userService.getOptionalCurrentUser();

        Page<Stock> stocks = stockRepository.filterStocks(
                dto.getCategoryId(),
                dto.getSubCategoryId(),
                dto.getProductNameLike(),
                buildPageable(dto.getPageNumber(), dto.getPageSize())
        );

        Set<Long> favouriteProductIds = optionalCurrentUser
                .map(user -> favouriteItemRepository.findByUserId(user.getId()))
                .orElse(Collections.emptyList())
                .stream()
                .map(fav -> fav.getProduct().getId())
                .collect(Collectors.toSet());

        PageDto pageDto = pageTransformer.transform(stocks);
        pageDto.setItems(
                stocks.getContent()
                        .stream()
                        .map(stock -> mapToHomeProductResponse(stock, favouriteProductIds, false))
                        .toList());

        log.info("getHomeProducts: execution completed");
        return new ResponseEntityDto(true, pageDto);
    }

    @Override
    public ResponseEntityDto getHomeProductInfo(Long productId) {
        log.info("getHomeProductInfo: execution started");

        Stock stock = stockRepository.findByProductId(productId)
                .orElseThrow(() -> new ModuleException(CommonMessageConstant.COMMON_ERROR_STOCK_NOT_FOUND));

        Optional<User> optionalCurrentUser = userService.getOptionalCurrentUser();

        Set<Long> ids = optionalCurrentUser
                .flatMap(user -> favouriteItemRepository.findByUserIdAndProductId(user.getId(), productId))
                .map(favouriteItem -> Set.of(favouriteItem.getId()))
                .orElseGet(HashSet::new);

        HomeProductsResponseDto homeProductsResponseDto = mapToHomeProductResponse(stock, ids, true);

        log.info("getHomeProductInfo: execution completed");
        return new ResponseEntityDto(true, homeProductsResponseDto);
    }

    @Override
    public ResponseEntityDto filterStock(StockFilterRequestDto dto) {
        log.info("getCurrentStock: execution started");
        Page<Stock> stocks = stockRepository.filterStocks(dto.getCategoryId(), dto.getSubCategoryId(),
                dto.getProductNameLike(), buildPageable(dto.getPageNumber(), dto.getPageSize()));

        PageDto pageDto = pageTransformer.transform(stocks);

        pageDto.setItems(stocks.getContent()
                .stream()
                .map(this::mapToProductResponse)
                .toList());

        log.info("getCurrentStock: execution completed");
        return new ResponseEntityDto(true, pageDto);
    }

    private HomeProductsResponseDto mapToHomeProductResponse(Stock stock, Set<Long> favouriteIds, Boolean images) {
        Product product = stock.getProduct();

        List<String> signedUrlImages = new ArrayList<>();
        if (Boolean.TRUE.equals(images)) {
            signedUrlImages = product.getImages().stream()
                    .map(this::getSignedUrl).toList();
        }
        return HomeProductsResponseDto.builder()
                .productId(product.getId())
                .nameTranslations(mapToProductNameResponseDtos(product.getNameTranslations()))
                .description(product.getDescription())
                .measurementUnit(product.getMeasurementUnit())
                .measurementValue(product.getMeasurementValue())
                .heroImageSignedUrl(s3ImageHandler.getImageUrl(product.getHeroImage()))
                .isFavourite(favouriteIds.contains(product.getId()))
                .measurementSellingPrice(getDefaultSellingPrice(stock))
                .images(signedUrlImages)
                .build();
    }

    private ProductResponseDto mapToProductResponse(Stock stock) {
        Product product = stock.getProduct();

        String heroImageSignedUrl = product.getHeroImage() != null ?
                getSignedUrl(product.getHeroImage()) : null;

        List<String> signedUrlImages = product.getImages().stream()
                .map(this::getSignedUrl).toList();

        return ProductResponseDto.builder()
                .productId(product.getId())
                .nameTranslations(mapToProductNameResponseDtos(product.getNameTranslations()))
                .description(product.getDescription())
                .measurementUnit(product.getMeasurementUnit())
                .measurementValue(product.getMeasurementValue())
                .heroImageSignedUrl(heroImageSignedUrl)
                .images(signedUrlImages)
                .measurementSellingPrice(getDefaultSellingPrice(stock))
                .priceResponseDtos(mapToPriceResponseDtos(stock.getPrices()))
                .quantity(stock.getQuantity())
                .build();
    }

    private BigDecimal getDefaultSellingPrice(Stock stock) {
        return stock.getPrices().stream()
                .filter(Price::getIsDefault)
                .map(Price::getFinalPrice)
                .findFirst()
                .orElse(BigDecimal.ZERO);
    }

    private List<PriceResponseDto> mapToPriceResponseDtos(List<Price> prices) {
        return prices.stream().map(this::mapToPriceResponseDto).toList();
    }

    private PriceResponseDto mapToPriceResponseDto(Price price) {
        return PriceResponseDto.builder()
                .priceId(price.getId())
                .actualPrice(price.getActualPrice())
                .unit(price.getUnit())
                .quantity(price.getQuantity())
                .discountAmount(price.getDiscountAmount())
                .discountPercent(price.getDiscountPercent())
                .finalPrice(price.getFinalPrice())
                .isDefault(price.getIsDefault())
                .isActive(price.getIsActive())
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

    private String getSignedUrl(String key) {
        return key != null ? s3ImageHandler.getImageUrl(key) : null;
    }
}
