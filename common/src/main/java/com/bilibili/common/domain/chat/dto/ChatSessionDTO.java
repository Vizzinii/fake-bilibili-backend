package com.bilibili.common.domain.chat.dto;

import com.bilibili.common.domain.chat.entity.Chat;
import com.bilibili.common.domain.chat.entity.ChatSession;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class ChatSessionDTO {

    Integer senderId;

    Integer receiverId;

    String updateContent;

    public ChatSession toSessionEntity(){
        ChatSession chatSession=new ChatSession();
        BeanUtils.copyProperties(this,chatSession);
        return chatSession;
    }
    public Chat toChatEntity(){
        Chat chat=new Chat();
        BeanUtils.copyProperties(this,chat);
        return chat;
    }
}
