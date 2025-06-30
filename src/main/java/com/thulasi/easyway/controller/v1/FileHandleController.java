package com.thulasi.easyway.controller.v1;

import com.thulasi.easyway.component.S3ImageHandler;
import com.thulasi.easyway.payload.response.ResponseEntityDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/v1/files")
@Tag(name = "File Handle Controller", description = "Endpoints for file handling (S3)")
@RequiredArgsConstructor
public class FileHandleController {

    private final S3ImageHandler s3ImageHandler;

    @Operation(summary = "Upload image to S3", description = "Upload an image to S3 bucket")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> uploadImage(@RequestParam("key") String key, @RequestParam("file") MultipartFile file) {
        Map<String, String> result = s3ImageHandler.uploadImage(key, file);
        ResponseEntityDto response = new ResponseEntityDto(true, result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get image URL", description = "Get the URL for an image in S3 bucket")
    @GetMapping(value = "/url", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> getImageUrl(@RequestParam("key") String key) {
        String url = s3ImageHandler.getImageUrl(key);
        ResponseEntityDto response = new ResponseEntityDto(true, url);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Delete image from S3", description = "Delete an image from S3 bucket")
    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> deleteImage(@RequestParam("key") String key) {
        s3ImageHandler.deleteImage(key);
        ResponseEntityDto response = new ResponseEntityDto("Image deleted successfully", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
