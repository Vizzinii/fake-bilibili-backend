package com.bilibili.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.bilibili.api.client.SendNoticeClient;
import com.bilibili.common.domain.user.entity.Follow;
import com.bilibili.common.domain.user.entity.User;
import com.bilibili.common.mapper.user.FollowMapper;
import com.bilibili.common.mapper.user.UserMapper;
import com.bilibili.common.util.Result;
import com.bilibili.common.domain.user.dto.UserInfoDTO;
import com.bilibili.api.controller.MinioApiController;
import com.bilibili.user.mapper.UserCenterServiceMapper;
import com.bilibili.user.service.UserInfoService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.bilibili.common.constant.UserConstant.*;

//import static com.bilibili.user.constant.Constant.*;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FollowMapper followMapper;

    @Autowired
    UserCenterServiceMapper userCenterServiceMapper;

    @Resource
    MinioClient minioClient;

    @Resource
    SendNoticeClient sendNoticeClient;

    private final   MinioApiController minioApiController;

    private String bucketName ; // TODO 桶名称未定义

    @Autowired
    public UserInfoServiceImpl( MinioApiController minioApiController) {
        this.minioApiController = minioApiController;
    }

    String filePath="https://labilibili.com/";

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
    public Result<Boolean> editSelfInfo(MultipartFile file, Integer userId, String nickname, String introduction) throws Exception {
        // 存储数据库变更通知，构建更新条件
        Map<String,Object> map=new HashMap<>();
        LambdaUpdateWrapper<User> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(User::getId,userId);
        map.put(TABLE_ID,userId);
        map.put(OPERATION_TYPE,OPERATION_TYPE_UPDATE);
        map.put(TABLE_NAME,USER_TABLE_NAME);

        // 处理头像文件
        if(file!=null){
            String coverName= UUID.randomUUID().toString().substring(0,10)+file.getOriginalFilename();
            minioClient.putObject(PutObjectArgs.builder().contentType(file.getContentType()).stream(file.getInputStream(),-1,10485760).bucket(bucketName).object(coverName).build());
            String url= filePath+bucketName+"/"+coverName;
            map.put(USER_COVER,url);
            wrapper.set(User::getCover,url);
        }

        // 处理昵称
        if(nickname!=null){
            map.put(USER_NICKNAME,nickname);
            wrapper.set(User::getNickname,nickname);
        }

        // 处理简介
        if(introduction!=null){
            map.put(USER_INTRO,introduction);
            wrapper.set(User::getIntro,introduction);
        }

        // 更新数据库和发送通知
        userMapper.update(null,wrapper);
        sendNoticeClient.sendDBChangeNotice(map);

        return Result.success(true);
    }
}
