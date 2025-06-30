package com.thulasi.easyway.service.impl;

import com.thulasi.easyway.component.S3ImageHandler;
import com.thulasi.easyway.payload.response.ResponseEntityDto;
import com.thulasi.easyway.service.S3HandleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class S3HandleServiceImpl implements S3HandleService {

    private final S3ImageHandler s3ImageHandler;

    @Override
    public ResponseEntityDto upload(String key, MultipartFile file) {
        log.info("s3ImageUpload: execution started");
        Map<String, String> uploadedImage = s3ImageHandler.uploadImage(key, file);
        log.info("s3ImageUpload: execution completed");
        return new ResponseEntityDto(true, uploadedImage);
    }

    @Override
    public ResponseEntityDto getPreSignedUrl(String key) {
        log.info("getPreSignedUrl: execution started");
        String imageUrl = s3ImageHandler.getImageUrl(key);
        log.info("s3ImagegetPreSignedUrlUpload: execution completed");
        return new ResponseEntityDto(true, imageUrl);
    }

    @Override
    public ResponseEntityDto deleteS3Image(String key) {
        log.info("deleteS3Image: execution started");
        s3ImageHandler.deleteImage(key);
        log.info("deleteS3Image: execution completed");
        return new ResponseEntityDto("Image deleted successfully",true);
    }
}
