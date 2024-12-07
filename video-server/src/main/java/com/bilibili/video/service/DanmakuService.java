package com.bilibili.video.service;

import com.bilibili.common.domain.video.dto.AddDanmakuDTO;
import com.bilibili.common.domain.video.vo.DanmakuVO;
import com.bilibili.common.util.Result;

import java.util.List;

public interface DanmakuService {
    Result<List<DanmakuVO>> getDanmaku(Integer videoId);

    Result<Boolean> addDanmaku(AddDanmakuDTO addDanmakuDTO);
}
