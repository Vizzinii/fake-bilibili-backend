package com.bilibili.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bilibili.common.domain.user.entity.Follow;
import com.bilibili.common.domain.user.entity.Privilege;
import com.bilibili.common.domain.user.entity.User;
import com.bilibili.common.domain.user.entity.VideoEnsemble;
import com.bilibili.user.mapper.FollowMapper;
import com.bilibili.user.mapper.PrivilegeMapper;
import com.bilibili.user.mapper.UserMapper;
import com.bilibili.user.mapper.VideoEnsembleMapper;
import com.bilibili.user.service.GetTableDataService;

import javax.annotation.Resource;
import java.util.List;

public class GetTableDataServiceImpl implements GetTableDataService {

    @Resource
    PrivilegeMapper privilegeMapper;

    @Resource
    VideoEnsembleMapper videoEnsembleMapper;

    @Resource
    FollowMapper followMapper;

    @Resource
    UserMapper userMapper;

    @Override
    public List<Privilege> getPrivilege() {
        return privilegeMapper.selectList(null);
    }

    @Override
    public List<VideoEnsemble> getVideoEnsemble() {
        return videoEnsembleMapper.selectList(null);
    }

    @Override
    public List<Follow> getFollow() {
        return followMapper.selectList(null);
    }

    @Override
    public List<User> getUser() {
        return userMapper.selectList(null);
    }

    @Override
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<Follow> selectFollowList(LambdaQueryWrapper<Follow> wrapper) {
        return followMapper.selectList(wrapper);
    }
}
