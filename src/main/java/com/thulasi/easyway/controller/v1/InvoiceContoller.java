package com.thulasi.easyway.controller.v1;

import com.thulasi.easyway.payload.response.ResponseEntityDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/invoices")
@Tag(name = "Invoice Controller", description = "Endpoints for invoices")
public class InvoiceContoller {
    @GetMapping("/not-implemented")
    public ResponseEntity<ResponseEntityDto> notImplemented() {
        return new ResponseEntity<>(new ResponseEntityDto("Not implemented", false), HttpStatus.NOT_IMPLEMENTED);
    }
}
