package com.bilibili.chat.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bilibili.common.domain.chat.entity.Chat;

public interface GetTableDataService {
    long selectCount(LambdaQueryWrapper<Chat> chatLambdaQueryWrapper);
}
