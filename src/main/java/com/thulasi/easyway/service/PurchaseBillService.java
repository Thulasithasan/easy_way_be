package com.thulasi.easyway.service;

import com.thulasi.easyway.payload.request.PurchaseBillRequestDto;
import com.thulasi.easyway.payload.response.ResponseEntityDto;
import org.springframework.web.multipart.MultipartFile;

public interface PurchaseBillService {
    ResponseEntityDto createPurchaseBill(PurchaseBillRequestDto dto);

    ResponseEntityDto uploadPurchaseBillImage(Long purchaseBillId, MultipartFile file);

    ResponseEntityDto deletePurchaseBillImage(Long purchaseBillId, String key);
}
