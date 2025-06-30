package com.thulasi.easyway.controller.v1;

import com.thulasi.easyway.payload.request.StockFilterRequestDto;
import com.thulasi.easyway.payload.response.ResponseEntityDto;
import com.thulasi.easyway.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/stocks")
@Tag(name = "Stock Controller", description = "Endpoints for stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @Operation(summary = "Get home products", description = "Get home products")
    @GetMapping(value = "/home-products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> getHomeProducts(@Valid StockFilterRequestDto dto) {
        ResponseEntityDto response = stockService.getHomeProducts(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get home product info", description = "Get home product info by productId")
    @GetMapping(value = "/home-product-info/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> getHomeProductInfo(@PathVariable Long productId) {
        ResponseEntityDto response = stockService.getHomeProductInfo(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @Operation(summary = "Filter stock", description = "Filter stock")
    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> filterStock(@Valid StockFilterRequestDto dto) {
        ResponseEntityDto response = stockService.filterStock(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
