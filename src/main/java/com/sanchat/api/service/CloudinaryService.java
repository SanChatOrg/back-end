package com.sanchat.api.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService(
            @Value("${cloudinary.cloud-name}") String cloudName,
            @Value("${cloudinary.api-key}") String apiKey,
            @Value("${cloudinary.api-secret}") String apiSecret
    ) {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        this.cloudinary = new Cloudinary(config); // 위 config 설정값으로 위 Cloudinary 객체를 통해 Cloudinary API 통신
    }

    public String upload(MultipartFile file) throws IOException {
        Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()); // 바이너리 데이터로 변환한 파일을 Cloudinary 업로드하는 메서드 / ObjectUtils.emptyMap() 업로드 시 추가 옵션을 설정하지 않음
        return (String) result.get("secure_url"); // secure_url: Cloudinary 에서 업로드된 파일의 URL(HTTPS 형식)
    }
}
