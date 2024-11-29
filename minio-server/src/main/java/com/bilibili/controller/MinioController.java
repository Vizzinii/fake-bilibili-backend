package com.bilibili.controller;

import com.bilibili.util.MinioUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
@RequestMapping("/minio")
public class MinioController {

    private final MinioUtils minioUtils;

    public MinioController(MinioUtils minioUtils) {
        this.minioUtils = minioUtils;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam String bucketName, 
                             @RequestParam String objectName, 
                             @RequestParam MultipartFile file) throws Exception {
        return minioUtils.uploadFile(bucketName, objectName, file);
    }

    @GetMapping("/download")
    public InputStream downloadFile(@RequestParam String bucketName, 
                                    @RequestParam String objectName) throws Exception {
        return minioUtils.downloadFile(bucketName, objectName);
    }

    @DeleteMapping("/delete")
    public void deleteFile(@RequestParam String bucketName, 
                           @RequestParam String objectName) throws Exception {
        minioUtils.deleteFile(bucketName, objectName);
    }
}
