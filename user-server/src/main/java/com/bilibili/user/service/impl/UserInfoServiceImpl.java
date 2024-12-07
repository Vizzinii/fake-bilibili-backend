package com.bilibili.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bilibili.common.domain.user.entity.Follow;
import com.bilibili.common.domain.user.entity.User;
import com.bilibili.common.mapper.user.FollowMapper;
import com.bilibili.common.mapper.user.UserMapper;
import com.bilibili.common.util.Result;
import com.bilibili.common.domain.user.dto.UserInfoDTO;
import com.bilibili.user.mapper.UserCenterServiceMapper;
import com.bilibili.user.service.UserInfoService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FollowMapper followMapper;

    @Autowired
    UserCenterServiceMapper userCenterServiceMapper;


    @Override
    public Result<UserInfoDTO> getUserInfo(Integer selfId, Integer visitedId) {
        // 设置查询条件构造器
        MPJLambdaWrapper<User> fansCountWrapper = new MPJLambdaWrapper<>();
        fansCountWrapper.eq(User::getId, visitedId);
        fansCountWrapper.leftJoin(Follow.class,Follow::getIdolId, User::getId);
        MPJLambdaWrapper<User> idolCountWrapper = new MPJLambdaWrapper<>();
        idolCountWrapper.eq(User::getId, visitedId);
        idolCountWrapper.leftJoin(Follow.class,Follow::getFansId, User::getId);


        // 设置返回值信息
        UserInfoDTO userInfoDTO = userCenterServiceMapper.getUserInfo(visitedId)
                .setFansCount(Math.toIntExact(userMapper.selectJoinCount(fansCountWrapper)))
                .setIdolCount(Math.toIntExact(userMapper.selectJoinCount(idolCountWrapper)));

        // 设置是否关注
        if(selfId>0){
            LambdaQueryWrapper<Follow> followLambdaQueryWrapper = new LambdaQueryWrapper<>();
            followLambdaQueryWrapper.eq(Follow::getFansId,selfId);
            followLambdaQueryWrapper.eq(Follow::getIdolId,visitedId);
            if(followMapper.selectCount(followLambdaQueryWrapper)>0){
                userInfoDTO.setIsFollowing(true);
            }else {
                userInfoDTO.setIsFollowing(false);
            }
        }
        return Result.success(userInfoDTO);
    }

    @Override
    public Result<Boolean> editSelfInfo(MultipartFile file, Integer userId, String nickname, String intro) throws Exception {
        return null;
    }
}
