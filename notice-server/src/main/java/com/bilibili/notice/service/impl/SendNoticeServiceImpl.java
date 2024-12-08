package com.bilibili.notice.service.impl;

import com.bilibili.common.domain.api.pojo.LikeNoticeAddOrDelete;
import com.bilibili.common.domain.api.pojo.UploadVideo;
import com.bilibili.common.domain.chat.entity.Chat;
import com.bilibili.common.domain.notice.entity.CommentNotice;
import com.bilibili.common.domain.notice.entity.Dynamic;
import com.bilibili.notice.service.SendNoticeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class SendNoticeServiceImpl implements SendNoticeService {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Resource
    ObjectMapper objectMapper;

    /**
     * 异步发送视频更新消息
     * @param dynamic
     * @throws JsonProcessingException
     */
    @Override
    public void sendVideoUpdateMessageWithCallback(Dynamic dynamic) throws JsonProcessingException {
        String topic = "dynamic";
        String jsonMessage = objectMapper.writeValueAsString(dynamic);
        rocketMQTemplate.
                asyncSend(topic,jsonMessage, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        log.info("Rocket发射成功" );
                    }

                    @Override
                    public void onException(Throwable e) {
                        e.getMessage();
                        log.error("发送视频更新消息的牢大坠机: " + e.getMessage());
                    }
                });
    }

    /**
     * 异步发送视频点赞消息
     * @param likeNotice
     * @throws JsonProcessingException
     */
    @Override
    public void sendVideoLikeMessageWithCallback(LikeNoticeAddOrDelete likeNotice) throws JsonProcessingException {
        String topic = "like";
        String jsonMessage = objectMapper.writeValueAsString(likeNotice);
        rocketMQTemplate.
                asyncSend(topic,jsonMessage, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        log.info("Rocket发射成功" );
                    }

                    @Override
                    public void onException(Throwable e) {
                        e.getMessage();
                        log.error("发送视频点赞消息的牢大坠机: " + e.getMessage());
                    }
                });
    }

    /**
     * 异步发送评论消息
     * @param commentNotice
     * @throws JsonProcessingException
     */
    @Override
    public void sendCommentMessageWithCallback(CommentNotice commentNotice) throws JsonProcessingException {
        String topic = "comment";
        String jsonMessage = objectMapper.writeValueAsString(commentNotice);
        rocketMQTemplate.
                asyncSend(topic,jsonMessage, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        log.info("Rocket发射成功" );
                    }

                    @Override
                    public void onException(Throwable e) {
                        e.getMessage();
                        log.error("发送评论消息的牢大坠机: " + e.getMessage());
                    }
                });
    }

    /**
     * 异步发送私聊消息
     * @param chat
     * @throws JsonProcessingException
     */
    @Override
    public void sendChatMessageWithCallback(Chat chat) throws JsonProcessingException {
        String topic = "chat";
        String jsonMessage = objectMapper.writeValueAsString(chat);
        rocketMQTemplate.
                asyncSend(topic,jsonMessage, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        log.info("Rocket发射成功" );
                    }

                    @Override
                    public void onException(Throwable e) {
                        e.getMessage();
                        log.error("发送私聊消息的牢大坠机: " + e.getMessage());
                    }
                });
    }

    /**
     * 异步发送视频上传消息
     * @param uploadVideo
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public Boolean sendUploadNotice(UploadVideo uploadVideo) throws JsonProcessingException {
        String topic="video-encode";
        String jsonMessage=objectMapper.writeValueAsString(uploadVideo);
        rocketMQTemplate.asyncSend(topic, jsonMessage, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("Rocket发射成功");
            }

            @Override
            public void onException(Throwable throwable) {
                throwable.getMessage();
                log.error("上传视频的牢大坠机:");
            }
        });
        return true;
    }
}
