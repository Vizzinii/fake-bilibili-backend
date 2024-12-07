package com.bilibili.minio.service;

import com.bilibili.common.util.Result;
import org.springframework.web.multipart.MultipartFile;


public interface MinioService {

    /**
     * 上传文件到 MinIO 服务器。
     *
     * @param bucketName 存储桶名称
     * @param file MultipartFile 类型的文件对象
     * @throws Exception 上传文件异常
     */
    public Result uploadFile(String bucketName, MultipartFile file) throws Exception;
}
