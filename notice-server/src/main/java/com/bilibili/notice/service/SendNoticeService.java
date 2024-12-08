package com.bilibili.notice.service;

import com.bilibili.common.domain.api.pojo.LikeNoticeAddOrDelete;
import com.bilibili.common.domain.api.pojo.UploadVideo;
import com.bilibili.common.domain.chat.entity.Chat;
import com.bilibili.common.domain.notice.entity.CommentNotice;
import com.bilibili.common.domain.notice.entity.Dynamic;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface SendNoticeService {
    void sendVideoUpdateMessageWithCallback(Dynamic dynamic) throws JsonProcessingException;

    void sendVideoLikeMessageWithCallback(LikeNoticeAddOrDelete likeNotice) throws JsonProcessingException;

    void sendCommentMessageWithCallback(CommentNotice commentNotice) throws JsonProcessingException;

    void sendChatMessageWithCallback(Chat chat) throws JsonProcessingException;

    Boolean sendUploadNotice(UploadVideo uploadVideo) throws JsonProcessingException;
}
