package com.bilibili.video.controller;

import com.bilibili.common.domain.video.dto.LikeDTO;
import com.bilibili.common.util.Result;
import com.bilibili.video.service.LikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 点赞视频和评论、撤销点赞
 */
@RestController
@RequestMapping("/like")
@Api(tags = "点赞相关接口")
@CrossOrigin(value = "*")
@Slf4j
public class LikeController {

    @Resource
    LikeService likeService;

    @ApiOperation("点赞视频或评论")
    @PostMapping("/like")
    public Result<Boolean> like(@RequestBody LikeDTO likeDTO) {
        log.info("点赞");
        log.info(likeDTO.toString());
        log.info("\n");
        log.info("\n");
        log.info("\n");
        return likeService.like(likeDTO);
    }

    @PostMapping("/reCallLike")
    @ApiOperation("取消点赞视频或评论")
    public Result<Boolean> recallLike(@RequestBody LikeDTO likeDTO) {
        log.info("取消点赞");
        log.info(likeDTO.toString());
        log.info("\n");
        log.info("\n");
        log.info("\n");
        return likeService.recallLike(likeDTO);
    }
}
