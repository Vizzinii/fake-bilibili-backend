package com.bilibili.video.service.impl;

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
import com.bilibili.video.service.GetAllTableDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GetAllTableDataServiceImpl implements GetAllTableDataService {

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


    /**
     * 查看所有个人主页开放权限
     * @return
     */
    @Override
    public List<Privilege> getPrivilege() {
        return userClient.getPrivilege();
    }

    /**
     * 查看所有关注通知
     * @return
     */
    @Override
    public List<LikeNotice> getLikeNotice() {
        return noticeClient.getLikeNotice();
    }

    /**
     * 查看所有评论通知
     * @return
     */
    @Override
    public List<CommentNotice> getCommentNotice() {
        return noticeClient.getCommentNotice();
    }

    /**
     * 查看所有视频
     * @return
     */
    @Override
    public String getVideo() {
        List<Video> list = videoMapper.selectList(null);
        String response = "";
        for (Video video : list) {
            response += video.getName() + ",";
        }
        return response;
    }

    /**
     * 查看所有视频集
     * @return
     */
    @Override
    public List<VideoEnsemble> getVideoEnsemble() {
        return userClient.getVideoEnsemble();
    }

    /**
     * 查看所有视频集
     * @return
     */
    @Override
    public List<AddToEnsemble> getAddToEnsemble() {
        return addToEnsembleMapper.selectList(null);
    }

    /**
     * 查看所有视频数据
     * @return
     */
    @Override
    public List<VideoData> getVideoData() {
        return videoDataMapper.selectList(null);
    }

    /**
     * 查看所有收藏夹
     * @return
     */
    @Override
    public List<CollectGroup> getCollectGroup() {
        return collectGroupMapper.selectList(null);
    }

    /**
     * 查看所有评论
     * @return
     */
    @Override
    public List<Comment> getComment() {
        return commentMapper.selectList(null);
    }

    /**
     * 查看所有弹幕
     * @return
     */
    @Override
    public List<Danmaku> getDanmaku() {
        return danmakuMapper.selectList(null);
    }

    /**
     * 查看所有点赞
     * @return
     */
    @Override
    public List<Like> getCommentLike() {
        return likeMapper.selectList(null);
    }

    /**
     * 查看所有关注
     * @return
     */
    @Override
    public List<Follow> getFollow() {
        return userClient.getFollow();
    }

    /**
     * 查看所有用户
     * @return
     */
    @Override
    public List<User> getUser() {
        return userClient.getUser();
    }

    /**
     * 查看所有播放记录
     * @return
     */
    @Override
    public List<Play> getHistoryPlay() {
        return playMapper.selectList(null);
    }

    /**
     * 查看所有收藏
     * @return
     */
    @Override
    public List<Collect> getCollect() {
        return collectMapper.selectList(null);
    }

    /**
     * 查看所有动态
     * @return
     */
    @Override
    public List<DynamicToUser> getDynamicToUser() {
        return noticeClient.getDynamicToUser();
    }
}
