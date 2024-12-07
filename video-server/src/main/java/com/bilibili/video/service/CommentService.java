package com.bilibili.video.service;

import com.bilibili.common.domain.video.dto.CommentDTO;
import com.bilibili.common.domain.video.vo.VideoCommentVO;
import com.bilibili.common.util.Result;

public interface CommentService {

    Result<Boolean> commentToComment(CommentDTO commentDTO);

    Result<VideoCommentVO> getComment(Integer videoId, Integer userId, Integer type);

}
