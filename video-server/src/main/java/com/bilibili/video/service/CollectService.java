package com.bilibili.video.service;

import com.bilibili.common.domain.video.dto.CollectDTO;
import com.bilibili.common.domain.video.dto.CollectGroupDTO;
import com.bilibili.common.domain.video.entity.audience_reactions.CollectGroup;
import com.bilibili.common.domain.video.vo.CollectGroupVO;
import com.bilibili.common.domain.video.vo.CollectVideoVO;
import com.bilibili.common.util.Result;

import java.util.List;

public interface CollectService {
    Result<Boolean> collect(List<CollectDTO> collectDTOs);

    Result<List<CollectGroupVO>> getVideoToCollectGroup(Integer userId, Integer videoId);

    Result<Boolean> editCollectGroup(CollectGroupDTO createCollectGroupDTO);

    Result<Boolean> deleteCollectGroup(CollectGroupDTO collectGroupDTO);

    Result<List<CollectGroup>> getCollectGroup(Integer userId);

    Result<List<CollectVideoVO>> getCollectVideo(Integer collectGroupId);

}
