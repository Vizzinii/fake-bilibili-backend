package com.bilibili.controller;

import com.bilibili.client.MinioApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
@RequestMapping("/api")
public class MinioApiController {

    private final MinioApiClient minioApiClient;

    @Autowired
    public MinioApiController(MinioApiClient minioApiClient) {
        this.minioApiClient = minioApiClient;
    }

    // 上传文件接口
    @PostMapping("/minio/upload")
    public String uploadFile(@RequestParam String bucketName,
                             @RequestParam String objectName,
                             @RequestParam MultipartFile file) throws Exception {
        return minioApiClient.uploadFile(bucketName, objectName, file);
    }

    // 下载文件接口
    @GetMapping("/minio/download")
    public InputStream downloadFile(@RequestParam String bucketName,
                                    @RequestParam String objectName) throws Exception {
        return minioApiClient.downloadFile(bucketName, objectName);
    }

    // 删除文件接口
    @DeleteMapping("/minio/delete")
    public void deleteFile(@RequestParam String bucketName,
                           @RequestParam String objectName) throws Exception {
        minioApiClient.deleteFile(bucketName, objectName);
    }
}
