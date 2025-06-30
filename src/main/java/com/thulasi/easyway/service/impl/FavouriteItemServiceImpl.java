package com.thulasi.easyway.service.impl;

import com.thulasi.easyway.component.S3ImageHandler;
import com.thulasi.easyway.model.*;
import com.thulasi.easyway.payload.request.FavouriteItemResponseDto;
import com.thulasi.easyway.payload.response.ProductNameResponseDto;
import com.thulasi.easyway.payload.response.ResponseEntityDto;
import com.thulasi.easyway.repository.FavouriteItemRepository;
import com.thulasi.easyway.service.FavouriteItemService;
import com.thulasi.easyway.service.ProductService;
import com.thulasi.easyway.service.UserService;
import com.thulasi.easyway.util.transformer.PageTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FavouriteItemServiceImpl implements FavouriteItemService {

    private final FavouriteItemRepository favouriteItemRepository;
    private final UserService userService;
    private final PageTransformer pageTransformer;
    private final ProductService productService;
    private final S3ImageHandler s3ImageHandler;

    @Override
    public ResponseEntityDto addFavourite(Long productId) {
        log.info("addFavourite: execution started");
        User currentUser = userService.getCurrentUser();

        boolean exists = favouriteItemRepository.existsByUserIdAndProductId(currentUser.getId(), productId);

        Product product = productService.getProduct(productId);

        if (!exists) {
            favouriteItemRepository.save(FavouriteItem.builder()
                    .userId(currentUser.getId())
                    .product(product)
                    .build());
        }

        log.info("addFavourite: execution completed");
        return new ResponseEntityDto("Favourite item added successfully", true);
    }

    @Override
    public ResponseEntityDto removeFavourite(Long productId) {
        log.info("removeFavourite: execution started");

        User currentUser = userService.getCurrentUser();

        Optional<FavouriteItem> optionalFavouriteItem = favouriteItemRepository
                .findByUserIdAndProductId(currentUser.getId(), productId);

        optionalFavouriteItem.ifPresent(favouriteItemRepository::delete);

        log.info("removeFavourite: execution completed");
        return new ResponseEntityDto("Favourite item removed successfully", true);
    }

    @Override
    public ResponseEntityDto getMyFavouteItems() {
        log.info("getMyFavouteItems: execution started");

        User currentUser = userService.getCurrentUser();

        List<FavouriteItem> favouriteItems = favouriteItemRepository.findByUserId(currentUser.getId());

        List<FavouriteItemResponseDto> favouriteItemResponseDtos = favouriteItems.stream().map(this::mapToFavouriteItemResponseDto).toList();

        log.info("getMyFavouteItems: execution completed");
        return new ResponseEntityDto(true, favouriteItemResponseDtos);
    }

    private FavouriteItemResponseDto mapToFavouriteItemResponseDto(FavouriteItem favouriteItem) {
        return FavouriteItemResponseDto.builder()
                .id(favouriteItem.getId())
                .productNameResponseDtos(mapToProductNameResponseDtos(favouriteItem.getProduct().getNameTranslations()))
                .heroImageSignedUrl(s3ImageHandler.getImageUrl(favouriteItem.getProduct().getHeroImage()))
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
