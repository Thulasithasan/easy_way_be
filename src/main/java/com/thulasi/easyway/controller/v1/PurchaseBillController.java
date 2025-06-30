package com.thulasi.easyway.controller.v1;

import com.thulasi.easyway.payload.request.PurchaseBillRequestDto;
import com.thulasi.easyway.payload.response.ResponseEntityDto;
import com.thulasi.easyway.service.PurchaseBillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/purchase-bills")
@Tag(name = "Purchase Bill Controller", description = "Endpoints for purchase bills")
@RequiredArgsConstructor
public class PurchaseBillController {

    private final PurchaseBillService purchaseBillService;

    @Operation(summary = "Create purchase bill", description = "Create a new purchase bill")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> createPurchaseBill(@Valid @RequestBody PurchaseBillRequestDto dto) {
        ResponseEntityDto response = purchaseBillService.createPurchaseBill(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Upload purchase bill image", description = "Upload an image for a specific purchase bill")
    @PutMapping(value = "/{id}/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> uploadPurchaseBillImage(@PathVariable Long id, @RequestParam MultipartFile image) {
        ResponseEntityDto response = purchaseBillService.uploadPurchaseBillImage(id, image);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Delete purchase bill image", description = "Delete an image from a specific purchase bill")
    @DeleteMapping(value = "/{id}/images", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> deletePurchaseBillImage(@PathVariable Long id, @RequestParam String key) {
        ResponseEntityDto response = purchaseBillService.deletePurchaseBillImage(id, key);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
