package com.thulasi.easyway.controller.v1;

import com.thulasi.easyway.payload.request.PriceRequestDto;
import com.thulasi.easyway.payload.response.ResponseEntityDto;
import com.thulasi.easyway.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/prices")
@Tag(name = "Price Controller", description = "Endpoints for prices")
@RequiredArgsConstructor
public class PriceContoller {

    private final PriceService priceService;

    @Operation(summary = "Create product price", description = "Create a new product price")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> createProductPrice(@Valid @RequestBody PriceRequestDto dto) {
        ResponseEntityDto response = priceService.createProductPrice(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
