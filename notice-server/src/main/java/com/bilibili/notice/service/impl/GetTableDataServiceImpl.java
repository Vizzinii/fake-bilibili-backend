package com.bilibili.notice.service.impl;

import com.bilibili.common.domain.notice.entity.CommentNotice;
import com.bilibili.common.domain.notice.entity.DynamicToUser;
import com.bilibili.common.domain.notice.entity.LikeNotice;
import com.bilibili.notice.mapper.CommentNoticeMapper;
import com.bilibili.notice.mapper.DynamicToUserMapper;
import com.bilibili.notice.mapper.LikeNoticeMapper;
import com.bilibili.notice.service.GetTableDataService;

import javax.annotation.Resource;
import java.util.List;

public class GetTableDataServiceImpl implements GetTableDataService {

    @Resource
    LikeNoticeMapper likeNoticeMapper;

    @Resource
    CommentNoticeMapper commentNoticeMapper;

    @Resource
    DynamicToUserMapper dynamicToUserMapper;

    @Override
    public List<LikeNotice> getLikeNotice() {
        return likeNoticeMapper.selectList(null);
    }

    @Override
    public List<CommentNotice> getCommentNotice() {
        return commentNoticeMapper.selectList(null);
    }

    @Override
    public List<DynamicToUser> getDynamicToUser() {
        return dynamicToUserMapper.selectList(null);
    }
}
