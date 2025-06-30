package com.thulasi.easyway.controller.v1;

import com.thulasi.easyway.payload.request.SalesOrderRequestDto;
import com.thulasi.easyway.payload.response.ResponseEntityDto;
import com.thulasi.easyway.service.SalesOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/sales-orders")
@Tag(name = "Sales Order Controller", description = "Endpoints for sales orders")
@RequiredArgsConstructor
public class SalesOrderController {

    private final SalesOrderService salesOrderService;

    @Operation(summary = "Create sales order", description = "Create a new sales order")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> createSalesOrder(@Valid @RequestBody SalesOrderRequestDto dto) {
        ResponseEntityDto response = salesOrderService.createSalesOrder(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
