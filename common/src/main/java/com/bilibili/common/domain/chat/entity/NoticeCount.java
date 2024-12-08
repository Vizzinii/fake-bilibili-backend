package com.bilibili.common.domain.chat.entity;

import lombok.Data;

@Data
public class NoticeCount {

    //私聊会话id
    private Integer sessionId;

    //私聊数
    private Integer noticeCount;

}
