package com.bilibili.video.service;

import com.bilibili.common.domain.notice.entity.CommentNotice;
import com.bilibili.common.domain.notice.entity.DynamicToUser;
import com.bilibili.common.domain.notice.entity.LikeNotice;
import com.bilibili.common.domain.user.entity.Follow;
import com.bilibili.common.domain.user.entity.Privilege;
import com.bilibili.common.domain.user.entity.User;
import com.bilibili.common.domain.user.entity.VideoEnsemble;
import com.bilibili.common.domain.video.entity.audience_reactions.*;
import com.bilibili.common.domain.video.entity.video_production.VideoData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GetAllTableDataService {
    List<Privilege> getPrivilege();

    List<LikeNotice> getLikeNotice();

    List<CommentNotice> getCommentNotice();

    String getVideo();

    List<VideoEnsemble> getVideoEnsemble();

    List<AddToEnsemble> getAddToEnsemble();

    List<VideoData> getVideoData();

    List<CollectGroup> getCollectGroup();

    List<Comment> getComment();

    List<Danmaku> getDanmaku();

    List<Like> getCommentLike();

    List<Follow> getFollow();

    List<User> getUser();

    List<Play> getHistoryPlay();

    List<Collect> getCollect();

    List<DynamicToUser> getDynamicToUser();
}
