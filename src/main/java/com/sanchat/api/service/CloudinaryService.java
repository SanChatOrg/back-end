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

    public Map<?, ?> destroyFile(String oldPublicId) throws IOException {
        if (oldPublicId != null && !oldPublicId.isEmpty()) {
            return cloudinary.uploader().destroy(oldPublicId, ObjectUtils.emptyMap());
        }
        return null;
    }

    public Map<?, ?> uploadFile(MultipartFile newFile) throws IOException {
        return cloudinary.uploader().upload(newFile.getBytes(), ObjectUtils.emptyMap());
    }

}
