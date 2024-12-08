package com.bilibili.notice.controller;

import com.bilibili.common.domain.notice.vo.CommentNoticeVO;
import com.bilibili.common.domain.notice.vo.DynamicVideoVO;
import com.bilibili.common.domain.notice.vo.LikeNoticeVO;
import com.bilibili.common.domain.notice.vo.UnReadNoticeCountVO;
import com.bilibili.common.util.Result;
import com.bilibili.notice.service.GetNoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/getNotice")
@Api(tags = "获取动态消息相关接口")
@Slf4j
public class GetNoticeController {

    @Resource
    GetNoticeService getNoticeService;

    @ApiOperation("获取该用户未读评论数、点赞数和回复数（及总数）；获取未读的动态数量")
    @GetMapping("/getNoticeCount/{userId}")
    public Result<UnReadNoticeCountVO> getNoticeCount(@PathVariable Integer userId) {
        log.info("1");
        return getNoticeService.getNoticeCount(userId);
    }

    @GetMapping("/getLikeNotice/{userId}")
    @ApiOperation("获取该用户历史点赞消息")
    public Result<List<LikeNoticeVO>> getLikeNotice(@PathVariable Integer userId){
        log.info("1");
        return getNoticeService.getLikeNotice(userId);
    }
    @ApiOperation("获取该用户历史评论消息")
    @GetMapping("/getCommentNotice/{userId}")
    public Result<List<CommentNoticeVO>> getCommentNotice(@PathVariable Integer userId){
        log.info("1");
        return getNoticeService.getCommentNotice(userId);
    }
    @GetMapping("/getDynamicVideo/{userId}")
    @ApiOperation("获取该用户首页动态视频")
    public Result<List<DynamicVideoVO>> getDynamicVideo(@PathVariable Integer userId) {
        log.info("1");
        return getNoticeService.getDynamicVideo(userId);
    }

}
