package com.bilibili.notice.controller;

import com.bilibili.notice.service.SendDBChangeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/notice/DBChange")
@Api(tags = "发送数据库变更通知相关接口")
public class SendDBChangeController {

    @Resource
    SendDBChangeService sendDBChangeService;

    @ApiIgnore
    @PostMapping("/sendDBChangeNotice")
    public void sendDBChangeNotice(@RequestBody Map<String,Object> map) throws JsonProcessingException {
        sendDBChangeService.sendDBChangeNotice(map);
    }
}
