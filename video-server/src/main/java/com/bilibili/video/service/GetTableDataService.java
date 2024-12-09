package com.bilibili.video.service;

import com.bilibili.common.domain.video.entity.audience_reactions.Comment;
import com.bilibili.common.domain.video.entity.video_production.Video;
import com.github.yulichang.wrapper.MPJLambdaWrapper;

public interface GetTableDataService {
    long selectVideoJoinCount(MPJLambdaWrapper<Video> videoLikeLambdaQueryWrapper);

    long selectCommentJoinCount(MPJLambdaWrapper<Comment> commentLikeLambdaQueryWrapper);

    void updateById(Video video);
}
