package com.bilibili.minio.controller;

import com.bilibili.common.util.Result;
import com.bilibili.minio.service.MinioService;
import com.bilibili.minio.util.MinioUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/minio")
public class MinioController {

    @Autowired
    MinioService minioService;

    @PostMapping("/upload")
    public Result upload(@RequestParam("bucketName") String bucketName ,@RequestParam("file") MultipartFile file) throws Exception {
        return minioService.uploadFile(bucketName,file);
    }
}
