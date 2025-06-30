package com.thulasi.easyway.controller.v1;

import com.thulasi.easyway.payload.response.ResponseEntityDto;
import com.thulasi.easyway.repository.FavouriteItemRepository;
import com.thulasi.easyway.service.FavouriteItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/favourites")
@Tag(name = "Favourite Item Controller", description = "Endpoints for favourite items")
@RequiredArgsConstructor
public class FavouriteItemController {

    private final FavouriteItemRepository favouriteItemRepository;
    private final FavouriteItemService favouriteItemService;

    @Operation(summary = "Get all favourite items for current user", description = "Get all favourite items for current user")
    @GetMapping(value = "/my", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> getMyFavourites() {
        ResponseEntityDto response = favouriteItemService.getMyFavouteItems();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Add product to favourites", description = "Add product to favourites by productId")
    @PostMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> addFavourite(@PathVariable Long productId) {
        ResponseEntityDto response = favouriteItemService.addFavourite(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Remove product from favourites", description = "Remove product from favourites by productId")
    @DeleteMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> removeFavourite(@PathVariable Long productId) {
        ResponseEntityDto response = favouriteItemService.removeFavourite(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
