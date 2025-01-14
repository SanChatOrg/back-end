package com.sanchat.api.util;

public class CloudinaryUtil {
    public static String getPublicIdFromUrl(String url) {

        String[] splitUrl = url.split("/upload/");// /upload/ 이후의 경로를 가져옴
        if (splitUrl.length > 1) {
            String publicIdWithExt = splitUrl[1]; // /upload/ 이후 경로 (버전 포함)
            String publicId = publicIdWithExt.substring(publicIdWithExt.indexOf('/') + 1, publicIdWithExt.lastIndexOf('.')); // 버전 정보와 확장자를 제거
            return publicId;
        }
        return null; // URL 형식이 잘못되었을 경우 null 반환
    }
}
