package com.thulasi.easyway.controller.v1;

import com.thulasi.easyway.payload.request.RecurringOrderRequestDto;
import com.thulasi.easyway.payload.response.ResponseEntityDto;
import com.thulasi.easyway.service.RecurringOrderItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/recurring-orders")
@Tag(name = "Recurring Order Controller", description = "Endpoints for recurring orders")
@RequiredArgsConstructor
public class RecurringOrderController {

    private final RecurringOrderItemService recurringOrderItemService;

    @Operation(summary = "Create recurring order", description = "Create a new recurring order")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> createRecurringOrder(@Valid @RequestBody RecurringOrderRequestDto dto) {
        ResponseEntityDto response = recurringOrderItemService.createRecurringOrder(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Add item to recurring order", description = "Add item to recurring order")
    @PostMapping(value = "/{recurringOrderId}/add-item/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> addToRecurringOrderItem(@PathVariable Long recurringOrderId, @PathVariable Long productId) {
        ResponseEntityDto response = recurringOrderItemService.addToRecurringOrderItem(recurringOrderId, productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Remove item from recurring order", description = "Remove item from recurring order")
    @DeleteMapping(value = "/{recurringOrderId}/remove-item/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> removeFromRecurringOrderItem(@PathVariable Long recurringOrderId, @PathVariable Long productId) {
        ResponseEntityDto response = recurringOrderItemService.removeFromRecurringOrderItem(recurringOrderId, productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get my recurring orders", description = "Get my recurring orders")
    @GetMapping(value = "/my", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> getMyRecurringOrder() {
        ResponseEntityDto response = recurringOrderItemService.getMyRecurringOrder();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
