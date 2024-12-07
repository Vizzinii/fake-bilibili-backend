package com.bilibili.minio.service.impl;

import com.bilibili.common.util.Result;
import com.bilibili.minio.service.MinioService;
import com.bilibili.minio.util.MinioUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MinioServiceImpl implements MinioService {


    @Autowired
    MinioUtils minioUtils;

    /**
     *
     * @param bucketName 存储桶名称
     * @param file MultipartFile 类型的文件对象
     * @throws Exception
     */
    @Override
    public Result uploadFile(String bucketName, MultipartFile file) throws Exception {
        // 上传文件到 MinIO 服务器
        minioUtils.uploadFile(bucketName, file);
        return Result.success();
    }
}
