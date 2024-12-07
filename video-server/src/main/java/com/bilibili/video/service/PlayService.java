package com.bilibili.video.service;

import com.bilibili.common.domain.video.dto.DeleteHistoryVideoDTO;
import com.bilibili.common.domain.video.vo.CommendVideoVO;
import com.bilibili.common.domain.video.vo.DetailVideoVO;
import com.bilibili.common.domain.video.vo.FirstPageVideoVO;
import com.bilibili.common.domain.video.vo.HistoryVideoVO;
import com.bilibili.common.util.Result;

import java.util.List;

public interface PlayService {
    Result<Boolean> addPlayRecord(int videoId, int userId);
    Result<Boolean> deleteHistoryVideo(DeleteHistoryVideoDTO deleteHistoryVideoRequest);
    Result<DetailVideoVO> getDetailVideo(Integer videoId, Integer userId, String collectGroupId);
    Result<List<CommendVideoVO>> getRecommendVideo(String videoId);
    Result<List<FirstPageVideoVO>> getFirstPageVideoResponse(Integer count);
    Result<List<HistoryVideoVO>> getHistoryVideo(int userId);
}
