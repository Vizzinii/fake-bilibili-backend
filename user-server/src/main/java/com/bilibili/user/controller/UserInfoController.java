package com.bilibili.user.controller;

import com.bilibili.common.util.Result;
import com.bilibili.common.domain.user.dto.UserInfoDTO;
import com.bilibili.user.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
@Api(tags = "用户信息相关接口")
@Slf4j
public class UserInfoController {


    @Autowired
    UserInfoService userInfoService;

    @GetMapping("/info/{selfId}/{visitedId}")
    @ApiOperation("获取用户信息")
    public Result<UserInfoDTO> getUserInfo(
            @PathVariable Integer selfId,
            @PathVariable Integer visitedId) {
        log.info("获取用户信息");
        return userInfoService.getUserInfo(selfId, visitedId);
    }

    @PostMapping("/editSelfInfo")
    @ApiOperation("修改用户信息")
    public Result<Boolean> editUserInfo(
            @RequestParam("file") MultipartFile file,
            @RequestParam("userId") Integer userId,
            @RequestParam("username") String username,
            @RequestParam("introduction") String introduction
    )throws Exception {
        log.info("修改用户信息");
        return userInfoService.editUserInfo(file,userId,username,introduction);
    }
}
