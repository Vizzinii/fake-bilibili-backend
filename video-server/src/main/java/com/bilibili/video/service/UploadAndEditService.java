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
    Result<Boolean> uploadTotal(UploadVideoDTO uploadVideoDTO);

    Result<List<String>> uploadPart(UploadPartDTO uploadPartDTO) throws IOException, EncoderException;

    Result<Boolean> edit(EditVideoDTO editVideoDTO);

    Result<Boolean> delete(DeleteVideoDTO deleteVideoDTO);

    ResponseEntity<Result<Boolean>> getProcessor(String resumableIdentifier, Integer resumableChunkNumber);
}
