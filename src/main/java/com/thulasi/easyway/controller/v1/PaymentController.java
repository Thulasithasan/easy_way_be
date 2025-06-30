package com.thulasi.easyway.controller.v1;

import com.thulasi.easyway.payload.request.PaymentRequestDto;
import com.thulasi.easyway.payload.response.ResponseEntityDto;
import com.thulasi.easyway.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/payments")
@Tag(name = "Payment Controller", description = "Endpoints for payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "Create payment", description = "Create a new payment")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> createPayment(@Valid @RequestBody PaymentRequestDto dto) {
        return new ResponseEntity<>(new ResponseEntityDto("Not implemented", false), HttpStatus.NOT_IMPLEMENTED);
    }
}
