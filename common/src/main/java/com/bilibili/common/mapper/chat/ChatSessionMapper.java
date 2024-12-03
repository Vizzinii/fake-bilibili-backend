package com.bilibili.common.mapper.chat;
import com.bilibili.common.domain.chat.entity.ChatSession;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatSessionMapper extends MPJBaseMapper<ChatSession> {
}
