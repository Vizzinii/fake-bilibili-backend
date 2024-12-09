package com.bilibili.notice.controller;

import com.bilibili.common.domain.api.pojo.LikeNoticeAddOrDelete;
import com.bilibili.common.domain.api.pojo.UploadVideo;
import com.bilibili.common.domain.chat.entity.Chat;
import com.bilibili.common.domain.notice.entity.CommentNotice;
import com.bilibili.common.domain.notice.entity.Dynamic;
import com.bilibili.notice.service.SendNoticeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

@RestController
@RequestMapping("/notice")
@Api(tags = "发送通知相关接口 Producer")
@Slf4j
public class SendNoticeController {

    @Resource
    SendNoticeService sendNoticeService;

    @PostMapping("/sendDynamicNotice")
    //@ApiIgnore
    @ApiOperation("发送弹幕通知")
    public Boolean dynamicNotice(@RequestBody Dynamic dynamic) throws JsonProcessingException {
        log.info("已发送弹幕通知");
        log.info("\n");
        sendNoticeService.sendVideoUpdateMessageWithCallback(dynamic);
        return true;
    }


    @PostMapping("/sendLikeNotice")
    //@ApiIgnore
    @ApiOperation("发送视频点赞通知")
    public void videolikeNotice(@RequestBody LikeNoticeAddOrDelete likeNotice) throws JsonProcessingException {
        log.info("已发送视频点赞通知");
        log.info("\n");
        sendNoticeService.sendVideoLikeMessageWithCallback(likeNotice);
    }


    @PostMapping("/sendCommentNotice")
    //@ApiIgnore
    @ApiOperation("发送评论通知")
    public void commentNotice(@RequestBody CommentNotice commentNotice) throws JsonProcessingException {
        log.info("已发送评论通知");
        log.info("\n");
        sendNoticeService.sendCommentMessageWithCallback(commentNotice);
    }


    @PostMapping("/sendChatNotice")
    //@ApiIgnore
    @ApiOperation("发送私聊通知")
    public void chatNotice(@RequestBody Chat chat) throws JsonProcessingException {
        log.info("已发送私聊通知");
        log.info("\n");
        sendNoticeService.sendChatMessageWithCallback(chat);
    }


    @PostMapping("/sendUploadNotice")
    //@ApiIgnore
    @ApiOperation("发送视频上传通知")
    public Boolean sendUploadNotice(@RequestBody UploadVideo uploadVideo) throws JsonProcessingException {
        log.info("已发送视频上传通知");
        log.info("\n");
        return sendNoticeService.sendUploadNotice(uploadVideo);
    }

}
