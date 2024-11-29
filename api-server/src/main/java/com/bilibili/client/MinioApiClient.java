package com.bilibili.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@FeignClient(name = "minio-service", url = "http://minio-service:8080")
public interface MinioApiClient {

    @PostMapping("/minio/upload")
    String uploadFile(@RequestParam("bucketName") String bucketName,
                      @RequestParam("objectName") String objectName,
                      @RequestParam("file") MultipartFile file);

    @GetMapping("/minio/download")
    InputStream downloadFile(@RequestParam("bucketName") String bucketName,
                             @RequestParam("objectName") String objectName);

    @DeleteMapping("/minio/delete")
    void deleteFile(@RequestParam("bucketName") String bucketName,
                    @RequestParam("objectName") String objectName);
}
