package com.bilibili.notice.service;

import com.bilibili.common.domain.notice.vo.CommentNoticeVO;
import com.bilibili.common.domain.notice.vo.DynamicVideoVO;
import com.bilibili.common.domain.notice.vo.LikeNoticeVO;
import com.bilibili.common.domain.notice.vo.UnReadNoticeCountVO;
import com.bilibili.common.util.Result;

import java.util.List;

public interface GetNoticeService {
    Result<UnReadNoticeCountVO> getNoticeCount(Integer userId);

    Result<List<LikeNoticeVO>> getLikeNotice(Integer userId);

    Result<List<CommentNoticeVO>> getCommentNotice(Integer userId);

    Result<List<DynamicVideoVO>> getDynamicVideo(Integer userId);
}
