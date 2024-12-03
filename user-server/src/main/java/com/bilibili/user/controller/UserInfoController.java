package com.bilibili.user.controller;

import com.bilibili.common.util.Result;
import com.bilibili.common.domain.user.dto.UserInfoDTO;
import com.bilibili.user.service.UserInfoService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(tags = "用户信息")
@Slf4j
public class UserInfoController {


    @Autowired
    UserInfoService userInfoService;

    public Result<UserInfoDTO> getUserInfo(
            @PathVariable Integer selfId,
            @PathVariable Integer visitedId) {
        return userInfoService.getUserInfo(selfId, visitedId);
    }
}
