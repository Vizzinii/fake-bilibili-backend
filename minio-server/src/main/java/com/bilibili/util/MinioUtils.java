package com.bilibili.util;

import io.minio.*;
import io.minio.messages.Bucket;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
public class MinioUtils {

    private final MinioClient minioClient;

    public MinioUtils(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    // 检查Bucket是否存在
    public boolean bucketExists(String bucketName) throws Exception {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    // 创建Bucket
    public void createBucket(String bucketName) throws Exception {
        if (!bucketExists(bucketName)) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    // 上传文件
    public String uploadFile(String bucketName, String objectName, MultipartFile file) throws Exception {
        createBucket(bucketName);
        try (InputStream inputStream = file.getInputStream()) {
            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(inputStream, file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build()
            );
        }
        return objectName;
    }

    // 下载文件
    public InputStream downloadFile(String bucketName, String objectName) throws Exception {
        return minioClient.getObject(
            GetObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build()
        );
    }

    // 删除文件
    public void deleteFile(String bucketName, String objectName) throws Exception {
        minioClient.removeObject(
            RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build()
        );
    }

    // 列出所有Buckets
    public List<Bucket> listBuckets() throws Exception {
        return minioClient.listBuckets();
    }
}
