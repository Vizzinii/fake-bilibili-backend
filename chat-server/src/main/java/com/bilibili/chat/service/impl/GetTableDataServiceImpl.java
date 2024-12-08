package com.bilibili.chat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bilibili.chat.mapper.ChatMapper;
import com.bilibili.chat.service.GetTableDataService;
import com.bilibili.common.domain.chat.entity.Chat;

import javax.annotation.Resource;

public class GetTableDataServiceImpl implements GetTableDataService {

    @Resource
    ChatMapper chatMapper;

    @Override
    public long selectCount(LambdaQueryWrapper<Chat> chatLambdaQueryWrapper) {
        return chatMapper.selectCount(chatLambdaQueryWrapper);
    }
}
