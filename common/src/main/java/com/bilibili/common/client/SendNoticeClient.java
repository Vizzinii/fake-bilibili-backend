package com.bilibili.common.client;

import com.bilibili.common.domain.api.pojo.LikeNoticeAddOrDelete;
import com.bilibili.common.domain.api.pojo.UploadVideo;
import com.bilibili.common.domain.chat.entity.Chat;
import com.bilibili.common.domain.notice.entity.CommentNotice;
import com.bilibili.common.domain.notice.entity.Dynamic;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "notice",url="http://localhost:8222")
@Component
public interface SendNoticeClient {
    @PostMapping("/notice/sendDynamicNotice")
    Boolean dynamicNotice(@RequestBody Dynamic dynamic);
    @PostMapping("/DBChange/sendDBChangeNotice")
    Boolean sendDBChangeNotice(@RequestBody Map<String,Object> map);
    @PostMapping("/notice/sendLikeNotice")
    Boolean sendLikeNotice(@RequestBody LikeNoticeAddOrDelete likeNotice);
    @PostMapping("/notice/sendCommentNotice")
    Boolean sendCommentNotice(@RequestBody CommentNotice commentNotice);
    @PostMapping("/notice/sendChatNotice")
    Boolean sendChatNotice(@RequestBody Chat chat);
    @PostMapping("/notice/sendUploadNotice")
    Boolean sendUploadNotice(@RequestBody UploadVideo uploadVideo);
}