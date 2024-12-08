package com.bilibili.video.service;

import com.bilibili.api.client.NoticeClient;
import com.bilibili.api.client.UserClient;
import com.bilibili.common.domain.notice.entity.CommentNotice;
import com.bilibili.common.domain.notice.entity.DynamicToUser;
import com.bilibili.common.domain.notice.entity.LikeNotice;
import com.bilibili.common.domain.user.entity.Follow;
import com.bilibili.common.domain.user.entity.Privilege;
import com.bilibili.common.domain.user.entity.User;
import com.bilibili.common.domain.user.entity.VideoEnsemble;
import com.bilibili.common.domain.video.entity.audience_reactions.*;
import com.bilibili.common.domain.video.entity.video_production.Video;
import com.bilibili.common.domain.video.entity.video_production.VideoData;


import com.bilibili.video.mapper.blogger.VideoDataMapper;
import com.bilibili.video.mapper.blogger.VideoMapper;
import com.bilibili.video.mapper.visitor.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GetAllTableDataService {

    @Resource
    UserClient userClient;

    @Resource
    NoticeClient noticeClient;

    @Resource
    CollectMapper collectMapper;

    @Resource
    CollectGroupMapper collectGroupMapper;

    @Resource
    PlayMapper playMapper;

    @Resource
    CommentMapper commentMapper;

    @Resource
    DanmakuMapper danmakuMapper;

    @Resource
    LikeMapper likeMapper;

    @Resource
    VideoDataMapper videoDataMapper;

    @Resource
    AddToEnsembleMapper addToEnsembleMapper;

    @Resource
    VideoMapper videoMapper;


    public List<Privilege> getPrivilege() {
        return userClient.getPrivilege();
    }

    public List<LikeNotice> getLikeNotice() {
        return noticeClient.getLikeNotice();
    }

    public List<CommentNotice> getCommentNotice() {
        return noticeClient.getCommentNotice();
    }

    public String getVideo() {
        List<Video> list = videoMapper.selectList(null);
        String response = "";
        for (Video video : list) {
            response += video.getName() + ",";
        }
        return response;
    }

    public List<VideoEnsemble> getVideoEnsemble() {
        return userClient.getVideoEnsemble();
    }

    public List<AddToEnsemble> getAddToEnsemble() {
        return addToEnsembleMapper.selectList(null);
    }

    public List<VideoData> getVideoData() {
        return videoDataMapper.selectList(null);
    }

    public List<CollectGroup> getCollectGroup() {
        return collectGroupMapper.selectList(null);
    }

    public List<Comment> getComment() {
        return commentMapper.selectList(null);
    }

    public List<Danmaku> getDanmaku() {
        return danmakuMapper.selectList(null);
    }

    public List<Like> getCommentLike() {
        return likeMapper.selectList(null);
    }

    public List<Follow> getFollow() {
        return userClient.getFollow();
    }

    public List<User> getUser() {
        return userClient.getUser();
    }

    public List<Play> getHistoryPlay() {
        return playMapper.selectList(null);
    }

    public List<Collect> getCollect() {
        return collectMapper.selectList(null);
    }

    public List<DynamicToUser> getDynamicToUser() {
        return noticeClient.getDynamicToUser();
    }
}
