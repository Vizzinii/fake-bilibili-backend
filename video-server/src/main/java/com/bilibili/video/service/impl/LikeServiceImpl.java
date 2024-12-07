package com.bilibili.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bilibili.api.client.SendNoticeClient;
import com.bilibili.common.domain.video.dto.LikeDTO;
import com.bilibili.common.domain.video.entity.audience_reactions.Like;
import com.bilibili.common.domain.video.entity.video_production.Video;
import com.bilibili.common.domain.video.entity.video_production.VideoData;
import com.bilibili.common.mapper.video.audience_reactions.LikeMapper;
import com.bilibili.common.util.Result;
import com.bilibili.video.service.LikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

@Service
public class LikeServiceImpl implements LikeService {

    @Resource
    LikeMapper likeMapper;

    @Resource
    SendNoticeClient client;

    /**
     * 点赞
     * @param likeDTO
     * @return
     */
    @Override
    public Result<Boolean> like(LikeDTO likeDTO) {
        likeMapper.insert(likeDTO.toEntity());
        LambdaQueryWrapper<Video> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Video::getId,likeDTO.getVideoId());
        CompletableFuture<Void> completableFuture=CompletableFuture.runAsync(()->{
            LambdaQueryWrapper<VideoData> videoDataLambdaQueryWrapper=new LambdaQueryWrapper<>();
            videoDataLambdaQueryWrapper.eq(VideoData::getId,likeDTO.getVideoId());

            //根据type值在点赞消息消费者中做出新增或删除操作
            client.sendLikeNotice(likeDTO.toAddOrDeleteNotice().setType(0));
        });
        return Result.success(true);
    }

    /**
     * 取消点赞
     * @param likeDTO
     * @return
     */
    @Override
    public Result<Boolean> recallLike(LikeDTO likeDTO) {
        LambdaQueryWrapper<Like> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Like::getUserId, likeDTO.getUserId());
        wrapper.eq(Like::getVideoId, likeDTO.getVideoId());

        //根据评论id是否为空决定删除评论时的删除细节，因为一个人可以同时在一个视频下发出对视频的评论和对评论的评论，因此需要作出区分
        if(likeDTO.getCommentId()!=null){
            wrapper.eq(Like::getCommentId, likeDTO.getCommentId());
        }else {
            wrapper.isNull(Like::getCommentId);
        }
        client.sendLikeNotice(likeDTO.toAddOrDeleteNotice().setType(1));
        likeMapper.delete(wrapper);
        return Result.success(true);

    }
}
