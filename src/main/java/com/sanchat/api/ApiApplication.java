package com.sanchat.api;

import io.github.cdimascio.dotenv.Dotenv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ApiApplication {

    public static void main(String[] args) {

        // .env 파일 환경 변수 등록
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        System.setProperty("cloudinary.cloud-name", dotenv.get("CLOUD_NAME"));
        System.setProperty("cloudinary.api-key", dotenv.get("API_KEY"));
        System.setProperty("cloudinary.api-secret", dotenv.get("API_SECRET"));

        SpringApplication.run(ApiApplication.class, args);
    }
}
