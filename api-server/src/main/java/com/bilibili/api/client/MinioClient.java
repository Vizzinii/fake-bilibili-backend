package com.bilibili.api.client;

import com.bilibili.common.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient("minio-server")
public interface MinioClient {

    /**
     * 上传文件
     * @param bucketName
     * @param file
     * @return
     */
    @PostMapping("/minio/upload")
    Result upload(@RequestParam("bucketName") String bucketName , @RequestParam("file") MultipartFile file);

}
