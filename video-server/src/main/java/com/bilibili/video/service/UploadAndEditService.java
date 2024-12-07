package com.bilibili.video.service;

import com.bilibili.common.domain.video.dto.DeleteVideoDTO;
import com.bilibili.common.domain.video.dto.EditVideoDTO;
import com.bilibili.common.domain.video.dto.UploadPartDTO;
import com.bilibili.common.domain.video.dto.UploadVideoDTO;
import com.bilibili.common.util.Result;
import org.springframework.http.ResponseEntity;
import ws.schild.jave.EncoderException;

import java.io.IOException;
import java.util.List;

public interface UploadAndEditService {
    //上传视频
    Result<Boolean> uploadTotal(UploadVideoDTO uploadVideoDTO);

    //上传分片
    Result<List<String>> uploadPart(UploadPartDTO uploadPartDTO) throws IOException, EncoderException;

    //编辑视频
    Result<Boolean> edit(EditVideoDTO editVideoDTO);

    //删除视频
    Result<Boolean> delete(DeleteVideoDTO deleteVideoDTO);

    //获取处理进度
    ResponseEntity<Result<Boolean>> getProcessor(String resumableIdentifier, Integer resumableChunkNumber);
}
