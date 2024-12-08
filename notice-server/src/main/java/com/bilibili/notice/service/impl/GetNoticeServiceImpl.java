package com.bilibili.notice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bilibili.common.domain.chat.entity.Chat;
import com.bilibili.common.domain.notice.entity.CommentNotice;
import com.bilibili.common.domain.notice.entity.DynamicToUser;
import com.bilibili.common.domain.notice.entity.LikeNotice;
import com.bilibili.common.domain.notice.vo.CommentNoticeVO;
import com.bilibili.common.domain.notice.vo.DynamicVideoVO;
import com.bilibili.common.domain.notice.vo.LikeNoticeVO;
import com.bilibili.common.domain.notice.vo.UnReadNoticeCountVO;
import com.bilibili.common.domain.video.entity.audience_reactions.Comment;
import com.bilibili.common.domain.video.entity.video_production.Video;
import com.bilibili.common.util.Result;
import com.bilibili.notice.mapper.DynamicToUserMapper;
import com.bilibili.notice.service.GetNoticeService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;

import javax.annotation.Resource;
import java.util.List;

import static com.alibaba.druid.sql.ast.expr.SQLBinaryOperator.Like;

public class GetNoticeServiceImpl implements GetNoticeService {

    @Resource
    DynamicToUserMapper dynamicToUserMapper;

    @Resource
    Cha

    @Override
    public Result<UnReadNoticeCountVO> getNoticeCount(Integer userId) {
        LambdaQueryWrapper<Chat> chatLambdaQueryWrapper = new LambdaQueryWrapper<>();
        MPJLambdaWrapper<Video> videoLikeLambdaQueryWrapper = new MPJLambdaWrapper<>();
        MPJLambdaWrapper<Comment> commentLikeLambdaQueryWrapper = new MPJLambdaWrapper<>();
        MPJLambdaWrapper<Video> videoLambdaQueryWrapper = new MPJLambdaWrapper<>();
        MPJLambdaWrapper<Comment> commentLamdaQueryWrapper=new MPJLambdaWrapper<>();
        LambdaQueryWrapper<DynamicToUser> dynamicToUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
        chatLambdaQueryWrapper.eq(Chat::getReceiverId, userId);
        chatLambdaQueryWrapper.eq(Chat::getStatus,0);
        videoLikeLambdaQueryWrapper.eq(Video::getUserId, userId);
        videoLikeLambdaQueryWrapper.leftJoin(LikeNotice.class,LikeNotice::getVideoId,Video::getId);
        videoLikeLambdaQueryWrapper.eq(LikeNotice::getStatus,0);
        videoLikeLambdaQueryWrapper.isNull(LikeNotice::getCommentId);
        long videoLikeCount= videoMapper.selectJoinCount(videoLikeLambdaQueryWrapper);
        commentLikeLambdaQueryWrapper.eq(Comment::getUserId,userId);
        commentLikeLambdaQueryWrapper.eq(LikeNotice::getStatus,0);
        commentLikeLambdaQueryWrapper.leftJoin(LikeNotice.class,LikeNotice::getCommentId,Comment::getId);
        long commentLikeCount=commentMapper.selectJoinCount(commentLikeLambdaQueryWrapper);
        commentLikeLambdaQueryWrapper.eq(Like::getUserId, userId);
        dynamicToUserLambdaQueryWrapper.eq(DynamicToUser::getUserId, userId);
        dynamicToUserLambdaQueryWrapper.eq(DynamicToUser::getStatus,0);
        long chatCount = chatMapper.selectCount(chatLambdaQueryWrapper);
        long likeCount = videoLikeCount+commentLikeCount;
        long dynamicVideoCount = dynamicToUserMapper.selectCount(dynamicToUserLambdaQueryWrapper);
        videoLambdaQueryWrapper.eq(Video::getUserId,userId);
        videoLambdaQueryWrapper.leftJoin(CommentNotice.class,CommentNotice::getVideoId,Video::getId);
        videoLambdaQueryWrapper.isNull(CommentNotice::getReceiverId);
        videoLambdaQueryWrapper.eq(CommentNotice::getStatus,0);
        long videoCommentCount=videoMapper.selectJoinCount(videoLambdaQueryWrapper);
        commentLamdaQueryWrapper.eq(Comment::getUserId,userId);
        commentLamdaQueryWrapper.leftJoin(CommentNotice.class,CommentNotice::getReceiverId,Comment::getId);
        commentLamdaQueryWrapper.eq(CommentNotice::getStatus,0);
        long commentCommentCount=commentMapper.selectJoinCount(commentLamdaQueryWrapper);
        long commentCount=videoCommentCount+commentCommentCount;
        long totalCount = chatCount + likeCount + commentCount;
        return Result.data(new UnReadNoticeCountResponse().setChatCount(chatCount).setLikeCount(likeCount)
                .setCommentCount(commentCount).setDynamicVideoCount(dynamicVideoCount).setTotalCount(totalCount));
    }

    @Override
    public Result<List<LikeNoticeVO>> getLikeNotice(Integer userId) {
        return null;
    }

    @Override
    public Result<List<CommentNoticeVO>> getCommentNotice(Integer userId) {
        return null;
    }

    @Override
    public Result<List<DynamicVideoVO>> getDynamicVideo(Integer userId) {
        return null;
    }
}
