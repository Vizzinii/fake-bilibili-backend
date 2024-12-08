package com.bilibili.notice.service.impl;

import com.bilibili.notice.service.SendDBChangeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
@Slf4j
public class SendDBChangeServiceImpl implements SendDBChangeService {

    @Resource
    RocketMQTemplate rocketMQTemplate;

    @Resource
    ObjectMapper objectMapper;
    String topic = "mysqlToEs";

    @Override
    public void sendDBChangeNotice(Map<String, Object> map) throws JsonProcessingException {
        String jsonMessage = objectMapper.writeValueAsString(map);
        rocketMQTemplate.asyncSend(topic, jsonMessage, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("Rocket发射成功");
            }

            @Override
            public void onException(Throwable e) {
                log.error("Rocket发射失败，您已坠机：" + e.getMessage());
            }
        });
    }
}
