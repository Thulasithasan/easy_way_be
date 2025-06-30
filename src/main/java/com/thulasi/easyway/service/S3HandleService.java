package com.thulasi.easyway.service;

import com.thulasi.easyway.payload.response.ResponseEntityDto;
import org.springframework.web.multipart.MultipartFile;

public interface S3HandleService {

    ResponseEntityDto upload(String key, MultipartFile file);

    ResponseEntityDto getPreSignedUrl(String key);

    ResponseEntityDto deleteS3Image(String key);
}
