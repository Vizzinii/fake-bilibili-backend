package com.bilibili.user.controller;

import com.bilibili.common.domain.user.entity.Follow;
import com.bilibili.common.domain.user.entity.Privilege;
import com.bilibili.common.domain.user.entity.User;
import com.bilibili.common.domain.user.entity.VideoEnsemble;
import com.bilibili.user.service.GetTableDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/getTableData")
@Api(tags = "获取表格数据")
@Slf4j
public class GetTableDataController {

    @Resource
    GetTableDataService getTableDataService;

    @GetMapping("/getPrivilege")
    @ApiOperation("远程调用user-server查看所有个人主页开放权限")
    public List<Privilege> getPrivilege(){
        log.info("正在远程调用user-server查看所有个人主页开放权限");
        log.info("\n");
        log.info("\n");
        log.info("\n");
        return getTableDataService.getPrivilege();
    }

    @GetMapping("/getVideoEnsemble")
    @ApiOperation("远程调用user-server查看视频合集")
    public List<VideoEnsemble> getVideoEnsemble(){
        log.info("正在远程调用user-server查看视频合集");
        log.info("\n");
        log.info("\n");
        log.info("\n");
        return getTableDataService.getVideoEnsemble();
    }

    @GetMapping("/getFollow")
    @ApiOperation("远程调用user-server查看关注表")
    public List<Follow> getFollow() {
        log.info("正在远程调用user-server查看关注表");
        log.info("\n");
        log.info("\n");
        log.info("\n");
        return getTableDataService.getFollow();
    }

    @GetMapping("/getUser")
    @ApiOperation("远程调用user-server查看用户表")
    public List<User> getUser() {
        log.info("正在远程调用user-server查看用户表");
        log.info("\n");
        log.info("\n");
        log.info("\n");
        return getTableDataService.getUser();
    }
}
