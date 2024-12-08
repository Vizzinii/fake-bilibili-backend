package com.bilibili.notice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bilibili.api.client.ChatClient;
import com.bilibili.api.client.VideoClient;
import com.bilibili.common.domain.chat.entity.Chat;
import com.bilibili.common.domain.notice.entity.CommentNotice;
import com.bilibili.common.domain.notice.entity.DynamicToUser;
import com.bilibili.common.domain.notice.entity.LikeNotice;
import com.bilibili.common.domain.notice.vo.CommentNoticeVO;
import com.bilibili.common.domain.notice.vo.DynamicVideoVO;
import com.bilibili.common.domain.notice.vo.LikeNoticeVO;
import com.bilibili.common.domain.notice.vo.UnReadNoticeCountVO;
import com.bilibili.common.domain.video.entity.audience_reactions.Comment;
import com.bilibili.common.domain.video.entity.audience_reactions.Like;
import com.bilibili.common.domain.video.entity.video_production.Video;
import com.bilibili.common.util.Result;
import com.bilibili.notice.mapper.DynamicToUserMapper;
import com.bilibili.notice.mapper.NoticeServiceMapper;
import com.bilibili.notice.service.GetNoticeService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;

import javax.annotation.Resource;
import java.util.List;


public class GetNoticeServiceImpl implements GetNoticeService {

    @Resource
    DynamicToUserMapper dynamicToUserMapper;

    @Resource
    NoticeServiceMapper noticeServiceMapper;

    @Resource
    ChatClient chatClient;

    @Resource
    VideoClient videoClient;

    /**
     * 获取指定用户未读的通知数量
     * @param userId
     * @return
     */
    @Override
    public Result<UnReadNoticeCountVO> getNoticeCount(Integer userId) {
        // 创建查询条件构造器
        LambdaQueryWrapper<Chat> chatLambdaQueryWrapper = new LambdaQueryWrapper<>();
        MPJLambdaWrapper<Video> videoLikeLambdaQueryWrapper = new MPJLambdaWrapper<>();
        MPJLambdaWrapper<Comment> commentLikeLambdaQueryWrapper = new MPJLambdaWrapper<>();
        MPJLambdaWrapper<Video> videoLambdaQueryWrapper = new MPJLambdaWrapper<>();
        MPJLambdaWrapper<Comment> commentLamdaQueryWrapper=new MPJLambdaWrapper<>();
        LambdaQueryWrapper<DynamicToUser> dynamicToUserLambdaQueryWrapper = new LambdaQueryWrapper<>();

        // 构造聊天记录查询条件
        chatLambdaQueryWrapper.eq(Chat::getReceiverId, userId);
        chatLambdaQueryWrapper.eq(Chat::getStatus,0);
        
        // 构造视频点赞通知查询条件
        videoLikeLambdaQueryWrapper.eq(Video::getUserId, userId);
        videoLikeLambdaQueryWrapper.leftJoin(LikeNotice.class,LikeNotice::getVideoId,Video::getId);
        videoLikeLambdaQueryWrapper.eq(LikeNotice::getStatus,0);
        videoLikeLambdaQueryWrapper.isNull(LikeNotice::getCommentId);
        long videoLikeCount= videoClient.selectVideoJoinCount(videoLikeLambdaQueryWrapper);

        // 构造评论点赞通知查询条件
        commentLikeLambdaQueryWrapper.eq(Comment::getUserId,userId);
        commentLikeLambdaQueryWrapper.eq(LikeNotice::getStatus,0);
        commentLikeLambdaQueryWrapper.leftJoin(LikeNotice.class,LikeNotice::getCommentId,Comment::getId);
        long commentLikeCount = videoClient.selectCommentJoinCount(commentLikeLambdaQueryWrapper);
        commentLikeLambdaQueryWrapper.eq(Like::getUserId, userId);

        // 构造动态视频通知查询条件
        dynamicToUserLambdaQueryWrapper.eq(DynamicToUser::getUserId, userId);
        dynamicToUserLambdaQueryWrapper.eq(DynamicToUser::getStatus,0);

        // 执行查询并计算总数
        long chatCount = chatClient.selectCount(chatLambdaQueryWrapper);
        long likeCount = videoLikeCount+commentLikeCount;
        long dynamicVideoCount = dynamicToUserMapper.selectCount(dynamicToUserLambdaQueryWrapper);
        videoLambdaQueryWrapper.eq(Video::getUserId,userId);
        videoLambdaQueryWrapper.leftJoin(CommentNotice.class,CommentNotice::getVideoId,Video::getId);
        videoLambdaQueryWrapper.isNull(CommentNotice::getReceiverId);
        videoLambdaQueryWrapper.eq(CommentNotice::getStatus,0);
        long videoCommentCount=videoClient.selectVideoJoinCount(videoLambdaQueryWrapper);
        commentLamdaQueryWrapper.eq(Comment::getUserId,userId);
        commentLamdaQueryWrapper.leftJoin(CommentNotice.class,CommentNotice::getReceiverId,Comment::getId);
        commentLamdaQueryWrapper.eq(CommentNotice::getStatus,0);
        long commentCommentCount=videoClient.selectCommentJoinCount(commentLamdaQueryWrapper);
        long commentCount=videoCommentCount+commentCommentCount;
        long totalCount = chatCount + likeCount + commentCount;

        return Result.data(new UnReadNoticeCountVO().setChatCount(chatCount).setLikeCount(likeCount)
                .setCommentCount(commentCount).setDynamicVideoCount(dynamicVideoCount).setTotalCount(totalCount));
    }

    /**
     * 获取点赞通知
     * @param userId
     * @return
     */
    @Override
    public Result<List<LikeNoticeVO>> getLikeNotice(Integer userId) {
        List<LikeNoticeVO> videoLikeNoticeVO=noticeServiceMapper.getVideoLikeNotice(userId);
        List<LikeNoticeVO> commentLikeNoticeVO=noticeServiceMapper.getCommentLikeNotice(userId);
        videoLikeNoticeVO.addAll(commentLikeNoticeVO);
        return Result.data(videoLikeNoticeVO);
    }

    /**
     * 获取评论通知
     * @param userId
     * @return
     */
    @Override
    public Result<List<CommentNoticeVO>> getCommentNotice(Integer userId) {
        List<CommentNoticeVO> videoCommentNoticeVO=noticeServiceMapper.getCommentToVideoNotice(userId);
        List<CommentNoticeVO> commentToCommentNoticeVO=noticeServiceMapper.getCommentToCommentNotice(userId);
        videoCommentNoticeVO.addAll(commentToCommentNoticeVO);
        return Result.data(videoCommentNoticeVO);
    }

    /**
     * 获取动态视频通知
     * @param userId
     * @return
     */
    @Override
    public Result<List<DynamicVideoVO>> getDynamicVideo(Integer userId) {
        return Result.data(noticeServiceMapper.getDynamicVideoNotice(userId));
    }

}
