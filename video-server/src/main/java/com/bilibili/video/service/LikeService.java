package com.bilibili.video.service;

import com.bilibili.common.domain.video.dto.LikeDTO;
import com.bilibili.common.util.Result;

public interface LikeService {
    Result<Boolean> like(LikeDTO likeDTO);

    Result<Boolean> recallLike(LikeDTO likeDTO);
}
