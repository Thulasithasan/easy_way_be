package com.thulasi.easyway.service;

import org.springframework.web.multipart.MultipartFile;

import com.thulasi.easyway.payload.response.ResponseEntityDto;

public interface ImageHandleService {

	ResponseEntityDto uploadImage(String key, MultipartFile file);

	ResponseEntityDto getImageUrl(String key);

}
