package com.bilibili.user.service;


import com.bilibili.common.util.Result;
import com.bilibili.common.domain.user.dto.UserInfoDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UserInfoService {
    Result<UserInfoDTO> getUserInfo(Integer selfId, Integer visitedId);

    Result<Boolean> editSelfInfo(MultipartFile file, Integer userId, String nickname, String introduction) throws Exception;
}
