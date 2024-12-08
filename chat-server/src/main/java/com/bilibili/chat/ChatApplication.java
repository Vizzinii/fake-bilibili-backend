package com.bilibili.chat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.bilibili.chat", "com.bilibili.common"})
@MapperScan(basePackages = {"com.bilibili.chat.mapper"})
public class ChatApplication {
}
