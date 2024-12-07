package com.bilibili.minio.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.bilibili.minio.config.MinioConfig;
import io.minio.*;
import io.minio.messages.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

@Component
@Slf4j
public class MinioUtils {

    @Autowired
    private MinioConfig minioConfig;

    @Resource
    private  MinioClient minioClient;

    /**
     * 检查Bucket是否存在
     * @param bucketName
     * @return
     * @throws Exception
     */
    public boolean isBucketExists(String bucketName) throws Exception {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    /**
     * 创建Bucket
     * @param bucketName
     * @throws Exception
     */
    public void createBucket(String bucketName) throws Exception {
        if (!isBucketExists(bucketName)) {
            log.info("Bucket不存在，创建Bucket: {}", bucketName);
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    /**
     * 删除Bucket
     * @param bucketName
     * @return
     */
    public Boolean removeBucket(String bucketName) {
        try {
            minioClient.removeBucket(RemoveBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
            return true;
        } catch (Exception e) {
            log.error("删除Bucket失败", e);
            return false;
        }
    }


    /**
     * 获取所有Buckets
     * @return
     * @throws Exception
     */
    public List<Bucket> getAllBuckets() throws Exception {
        try {
            return minioClient.listBuckets();
        } catch (Exception e) {
            log.error("列出所有Buckets失败", e);
            return null;
        }
    }

    /**
     *
     * @param bucketName
     * @param file
     * @return
     * @throws Exception
     */
    public String uploadFile(String bucketName, MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();
        if(StringUtils.isBlank(originalFilename)){
            throw new RuntimeException("文件名不能为空");
        }
        // 获取文件后缀名
        String fileName = FileUtils.generateFileNameMd5(file.getName()) + originalFilename.substring(originalFilename.lastIndexOf("."));

        // 生成上传到Minio的文件名
        String objectName = FileUtils.generateFileMd5(file) + fileName;

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
}
