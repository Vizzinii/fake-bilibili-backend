package com.bilibili.notice.service;

import com.bilibili.common.domain.notice.entity.CommentNotice;
import com.bilibili.common.domain.notice.entity.DynamicToUser;
import com.bilibili.common.domain.notice.entity.LikeNotice;

import java.util.List;

public interface GetTableDataService {
    List<LikeNotice> getLikeNotice();

    List<CommentNotice> getCommentNotice();

    List<DynamicToUser> getDynamicToUser();
}
