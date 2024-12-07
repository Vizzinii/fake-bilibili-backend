package com.bilibili.user.controller;

import com.bilibili.common.util.Result;
import com.bilibili.common.domain.user.dto.UserInfoDTO;
import com.bilibili.user.service.UserInfoService;
import com.google.protobuf.ServiceException;
import io.minio.errors.*;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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
        log.info("获取用户信息");
        return userInfoService.getUserInfo(selfId, visitedId);
    }

    public Result<Boolean> editSelfInfo(
            @RequestParam("file") MultipartFile file,
            @RequestParam("userId") Integer userId,
            @RequestParam("username") String username,
            @RequestParam("introduction") String introduction
    )throws Exception {
        log.info("修改用户信息");
        return userInfoService.editSelfInfo(file,userId,username,introduction);
    }
}
