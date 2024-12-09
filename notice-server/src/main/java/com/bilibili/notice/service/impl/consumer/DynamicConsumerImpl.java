package com.bilibili.notice.service.impl.consumer;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bilibili.api.client.UserClient;
import com.bilibili.common.domain.notice.entity.Dynamic;
import com.bilibili.common.domain.notice.entity.DynamicToUser;
import com.bilibili.common.domain.user.entity.Follow;
import com.bilibili.notice.mapper.DynamicMapper;
import com.bilibili.notice.mapper.DynamicToUserMapper;
import com.bilibili.notice.service.DynamicToUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RocketMQMessageListener(
        topic = "dynamic",
        consumerGroup = "dynamic-group",
        consumeMode = ConsumeMode.CONCURRENTLY
)
public class DynamicConsumerImpl implements RocketMQListener<MessageExt> {

    @Resource
    ObjectMapper objectMapper;

    @Resource
    DynamicMapper dynamicMapper;

    @Resource
    UserClient userClient;

    @Resource
    DynamicToUserMapper dynamicToUserMapper;

    @Resource
    DynamicToUserService dynamicToUserService;

    @Override
    public void onMessage(MessageExt messageExt) {
        String json = new String(messageExt.getBody(), StandardCharsets.UTF_8);
        Dynamic dynamic;
        try {
            dynamic = objectMapper.readValue(json, Dynamic.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        log.info(dynamic.getVideoId().toString());
        dynamicMapper.insert(dynamic);

        //查询出该动态会推送给哪些人然后插入记录到中间联系表中
        LambdaQueryWrapper<Follow> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Follow::getIdolId, dynamic.getAuthorId());
        List<Follow> list = userClient.selectFollowList(wrapper);
        List<DynamicToUser> dynamicToUserList=new ArrayList<>();
        for (Follow follow : list) {
            dynamicToUserList.add(new DynamicToUser().setUserId(follow.getFansId()).setDynamicId(dynamic.getId()));
        }
        dynamicToUserService.saveBatch(dynamicToUserList);
    }
}
