package com.thulasi.easyway.controller.v1;

import com.thulasi.easyway.payload.response.ResponseEntityDto;
import com.thulasi.easyway.service.CardItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/card-items")
@Tag(name = "Card Item Controller", description = "Endpoints for card items")
@RequiredArgsConstructor
public class CardItemController {

    private final CardItemService cardItemService;

    @Operation(summary = "Add product to card", description = "Add product to card by productId")
    @PostMapping(value = "/add/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> addToCard(@PathVariable Long productId) {
        ResponseEntityDto response = cardItemService.addToCard(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Remove product from card", description = "Remove product from card by productId")
    @DeleteMapping(value = "/remove/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> removeFromCart(@PathVariable Long productId) {
        ResponseEntityDto response = cardItemService.removeFromCart(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get my card items", description = "Get all card items for current user")
    @GetMapping(value = "/my", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> getMyCard() {
        ResponseEntityDto response = cardItemService.getMyCard();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
