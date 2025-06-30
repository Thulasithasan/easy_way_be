package com.thulasi.easyway.controller.v1;

import com.thulasi.easyway.payload.request.StockIncomeRequestDto;
import com.thulasi.easyway.payload.response.ResponseEntityDto;
import com.thulasi.easyway.service.StockIncomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/stock-incomes")
@Tag(name = "Stock Income Controller", description = "Endpoints for stock incomes")
@RequiredArgsConstructor
public class StockIncomeController {

    private final StockIncomeService stockIncomeService;

    @Operation(summary = "Create stock income", description = "Create a new stock income")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> createStockIncome(@Valid @RequestBody StockIncomeRequestDto dto) {
        ResponseEntityDto response = stockIncomeService.createStockIncome(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
