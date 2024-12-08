package com.bilibili.video.service;

import com.bilibili.common.domain.api.pojo.UploadVideo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

public interface VideoEncodeService {
    ResponseEntity<Resource> getVideoInputStream(UploadVideo uploadVideo);

    void uploadVideo(MultipartFile multipartFile) throws IOException;

    void uploadVideoCover(MultipartFile multipartFile) throws IOException;
}
