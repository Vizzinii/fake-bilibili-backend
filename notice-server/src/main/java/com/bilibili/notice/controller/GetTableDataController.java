package com.bilibili.notice.controller;

import com.bilibili.common.domain.notice.entity.CommentNotice;
import com.bilibili.common.domain.notice.entity.DynamicToUser;
import com.bilibili.common.domain.notice.entity.LikeNotice;
import com.bilibili.notice.service.GetTableDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/notice/getTableData")
@Api(tags = "获取表格数据相关接口")
@Slf4j
public class GetTableDataController {

    @Resource
    GetTableDataService getTableDataService;

    @GetMapping("/getLikeNotice")
    @ApiOperation("远程调用notice-server查看所有点赞动态")
    public List<LikeNotice> getLikeNotice(){
        log.info("正在调用notice-server查看所有点赞动态");
        log.info("\n");
        log.info("\n");
        log.info("\n");
        return getTableDataService.getLikeNotice();
    }

    @GetMapping("/getCommentNotice")
    @ApiOperation("远程调用notice-server查看所有评论动态")
    public List<CommentNotice> getCommentNotice(){
        log.info("正在调用notice-server查看所有评论动态");
        log.info("\n");
        log.info("\n");
        log.info("\n");
        return getTableDataService.getCommentNotice();
    }

    @GetMapping("/getDynamicToUser")
    @ApiOperation("远程调用notice-server查看推送至用户的动态表")
    public List<DynamicToUser> getDynamicToUser(){
        log.info("正在调用notice-server查看推送至用户的动态表");
        log.info("\n");
        log.info("\n");
        log.info("\n");
        return getTableDataService.getDynamicToUser();
    }

}
