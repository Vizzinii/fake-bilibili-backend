package com.bilibili.video.controller;

import com.bilibili.common.domain.video.dto.DeleteVideoDTO;
import com.bilibili.common.domain.video.dto.EditVideoDTO;
import com.bilibili.common.domain.video.dto.UploadPartDTO;
import com.bilibili.common.domain.video.dto.UploadVideoDTO;
import com.bilibili.common.util.Result;
import com.bilibili.video.service.UploadAndEditService;
import com.sun.jdi.InternalException;
import io.netty.handler.codec.EncoderException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.rmi.ServerException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/createCenter")
@Api(tags = "视频上传和编辑")
@CrossOrigin(value = "*")
@Slf4j
public class UploadAndEditController {

    @Resource
    UploadAndEditService uploadAndEditService;

    @ApiOperation("上传视频")
    @PostMapping("/uploadTotal")
    public Result<Boolean> uploadTotal(@RequestBody UploadVideoDTO uploadVideoDTO) throws IOException {
        log.info("上传视频");
        log.info(uploadVideoDTO.toString());
        log.info("\n");
        log.info("\n");
        log.info("\n");
        return uploadAndEditService.uploadTotal(uploadVideoDTO);
    }


    @ApiOperation("上传分片并获取视频第一帧图片和合并后的路径")
    @PostMapping("/uploadPart")
    public Result<List<String>> uploadPart(@ModelAttribute UploadPartDTO uploadPartDTO) throws EncoderException, IOException, ServerException, NoSuchAlgorithmException, InvalidKeyException, InternalException, ws.schild.jave.EncoderException {
        log.info("上传分片");
        log.info(uploadPartDTO.toString());
        log.info("\n");
        log.info("\n");
        log.info("\n");
        return uploadAndEditService.uploadPart(uploadPartDTO);
    }


    @ApiOperation("编辑视频")
    @PostMapping("edit")
    public Result<Boolean> edit(@ModelAttribute EditVideoDTO editVideoDTO){
        log.info("编辑视频");
        log.info(editVideoDTO.toString());
        log.info("\n");
        log.info("\n");
        log.info("\n");
        return uploadAndEditService.edit(editVideoDTO);
    }


    @ApiOperation("删除视频")
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody DeleteVideoDTO deleteVideoDTO){
        log.info("删除视频");
        log.info(deleteVideoDTO.toString());
        log.info("\n");
        log.info("\n");
        log.info("\n");
        return uploadAndEditService.delete(deleteVideoDTO);
    }


    @ApiOperation("查询进度")
    @GetMapping("/getProcessor")
    public ResponseEntity<Result<Boolean>> getProcessor(@RequestParam("resumableIdentifier") String resumableIdentifier, @RequestParam("resumableChunkNumber")Integer resumableChunkNumber) {
        log.info("查询进度");
        log.info("\n");
        log.info("\n");
        log.info("\n");
        return uploadAndEditService.getProcessor(resumableIdentifier,resumableChunkNumber);
    }

}
