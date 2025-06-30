package com.thulasi.easyway.service.impl;

import com.thulasi.easyway.component.S3ImageHandler;
import com.thulasi.easyway.constant.CommonMessageConstant;
import com.thulasi.easyway.exception.EntityNotFoundException;
import com.thulasi.easyway.model.PurchaseBill;
import com.thulasi.easyway.payload.request.PurchaseBillRequestDto;
import com.thulasi.easyway.payload.response.ResponseEntityDto;
import com.thulasi.easyway.repository.PurchaseBillRepository;
import com.thulasi.easyway.service.PurchaseBillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class PurchaseBillServiceImpl implements PurchaseBillService {

    private final PurchaseBillRepository purchaseBillRepository;
    private final S3ImageHandler s3ImageHandler;

    @Override
    public ResponseEntityDto createPurchaseBill(PurchaseBillRequestDto dto) {
        log.info("createPurchaseBill: execution started");
        PurchaseBill bill = PurchaseBill.builder()
                .supplierName(dto.getSupplierName())
                .billNumber(dto.getBillNumber())
                .billDate(dto.getBillDate())
                .totalAmount(dto.getTotalAmount())
                .note(dto.getNote())
                .build();

        // Save all in one transaction
        purchaseBillRepository.save(bill);
        log.info("createPurchaseBill: execution completed");
        return new ResponseEntityDto("Purchase bill created successfully", true);
    }

    @Override
    public ResponseEntityDto uploadPurchaseBillImage(Long purchaseBillId, MultipartFile file) {
        log.info("purchaseBillImageUpload: execution started");

        PurchaseBill purchaseBill = purchaseBillRepository.findById(purchaseBillId)
                .orElseThrow(() -> new EntityNotFoundException(CommonMessageConstant.COMMON_ERROR_PURCHASE_BILL_NOT_FOUND));

        Map<String, String> uploadedImage = s3ImageHandler.uploadImage("purchaseBills/", file);

        String imageKeyUrl = uploadedImage.get("key") + uploadedImage.get("url");

        purchaseBill.setBillImageUrl(imageKeyUrl);

        purchaseBillRepository.save(purchaseBill);

        log.info("purchaseBillImageUpload: execution completed");
        return new ResponseEntityDto(true, uploadedImage);
    }

    @Override
    public ResponseEntityDto deletePurchaseBillImage(Long purchaseBillId, String key) {
        log.info("purchaseBillImageDelete: execution started");

        PurchaseBill purchaseBill = purchaseBillRepository.findById(purchaseBillId)
                .orElseThrow(() -> new EntityNotFoundException(CommonMessageConstant.COMMON_ERROR_PURCHASE_BILL_NOT_FOUND));

        s3ImageHandler.deleteImage(key);

        purchaseBillRepository.save(purchaseBill);

        log.info("purchaseBillImageDelete: execution completed");
        return new ResponseEntityDto("Product image deleted successfully", true);
    }
}
