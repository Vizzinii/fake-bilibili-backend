package com.bilibili.api.client;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bilibili.common.domain.chat.entity.Chat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "chat-server")
public interface ChatClient {

    @GetMapping("/table/getTableData/selectCount")
    long selectCount(LambdaQueryWrapper<Chat> chatLambdaQueryWrapper);

}
