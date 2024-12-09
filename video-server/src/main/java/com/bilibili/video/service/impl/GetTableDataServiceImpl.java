package com.bilibili.video.service.impl;

import com.bilibili.common.domain.video.entity.audience_reactions.Comment;
import com.bilibili.common.domain.video.entity.video_production.Video;
import com.bilibili.video.mapper.blogger.VideoMapper;
import com.bilibili.video.mapper.visitor.CommentMapper;
import com.bilibili.video.service.GetTableDataService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GetTableDataServiceImpl implements GetTableDataService {

    @Resource
    VideoMapper videoMapper;

    @Resource
    CommentMapper commentMapper;

    @Override
    public long selectVideoJoinCount(MPJLambdaWrapper<Video> videoLikeLambdaQueryWrapper) {
        return videoMapper.selectJoinCount(videoLikeLambdaQueryWrapper);
    }

    @Override
    public long selectCommentJoinCount(MPJLambdaWrapper<Comment> commentLikeLambdaQueryWrapper) {
        return commentMapper.selectJoinCount(commentLikeLambdaQueryWrapper);
    }

    @Override
    public void updateById(Video video) {
        videoMapper.updateById(video);
    }


}
