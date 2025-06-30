package com.thulasi.easyway.component;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Component
public class S3ImageHandler {

	private final AmazonS3 s3Client;

	@Value("${aws.s3.bucket.name}")
	private String bucketName;

	public S3ImageHandler(AmazonS3 s3Client) {
		this.s3Client = s3Client;
	}

	public Map<String, String> uploadImage(String folderPath, MultipartFile file) {
		String originalFilename = file.getOriginalFilename();
		String extension = StringUtils.getFilenameExtension(originalFilename);
		String uniqueFilename = UUID.randomUUID().toString();
		String key = String.format("%s/%s.%s", folderPath, uniqueFilename, extension);

		try {
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(file.getSize());
			metadata.setContentType(file.getContentType());

			PutObjectRequest request = new PutObjectRequest(bucketName, key, file.getInputStream(), metadata);
			s3Client.putObject(request);
		} catch (IOException e) {
			throw new IllegalArgumentException("Failed to upload image", e);
		}

		Map<String, String> response = new HashMap<>();
		response.put("key", key);
		response.put("url", getImageUrl(key));
		return response;
	}

//	public String getImageUrl(String key) {
//		return s3Client.getUrl(bucketName, key).toString();
//	}

	public String getImageUrl(String key) {
		// Set expiration time
		Date expiration = new Date();
		long expTimeMillis = expiration.getTime();
		expTimeMillis += (long) 10 * 60 * 1000; // Convert minutes to milliseconds
		expiration.setTime(expTimeMillis);

		// Generate pre-signed URL
		GeneratePresignedUrlRequest generatePresignedUrlRequest =
				new GeneratePresignedUrlRequest(bucketName, key)
						.withMethod(HttpMethod.GET)
						.withExpiration(expiration);

		return s3Client.generatePresignedUrl(generatePresignedUrlRequest).toString();
	}

	public void deleteImage(String key) {
		s3Client.deleteObject(bucketName, key);
	}

}
