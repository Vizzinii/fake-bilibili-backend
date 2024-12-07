package com.bilibili.video.controller;

import com.bilibili.common.domain.video.dto.CommentDTO;
import com.bilibili.common.domain.video.vo.VideoCommentVO;
import com.bilibili.common.util.Result;
import com.bilibili.video.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 评论、查看某个视频的评论
 */
@RestController
@RequestMapping("/comment")
@Api(tags = "评论相关接口")
@CrossOrigin(value = "*")
@Slf4j
public class CommentController {

    @Resource
    CommentService commentService;

    @PostMapping("/comment")
    @ApiOperation("发送对视频或评论的评论")
    public Result<Boolean> comment(@RequestBody CommentDTO commentDTO) {
        log.info("评论评论");
        log.info(commentDTO.toString());
        log.info("\n");
        log.info("\n");
        log.info("\n");
        return commentService.commentToComment(commentDTO);
    }


    @GetMapping("/getComment/{videoId}/{userId}/{type}")
    @ApiOperation("获取视频下的评论")
    public Result<VideoCommentVO> getComment(
            @PathVariable Integer videoId,
            @PathVariable Integer userId,
            @PathVariable Integer type) {
        log.info("获取评论");
        log.info(userId.toString());
        log.info("\n");
        log.info("\n");
        log.info("\n");
        return commentService.getComment(videoId,userId,type);
    }
}
