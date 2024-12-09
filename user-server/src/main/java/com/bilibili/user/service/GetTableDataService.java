package com.bilibili.user.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bilibili.common.domain.user.entity.Follow;
import com.bilibili.common.domain.user.entity.Privilege;
import com.bilibili.common.domain.user.entity.User;
import com.bilibili.common.domain.user.entity.VideoEnsemble;

import java.util.List;

public interface GetTableDataService {
    List<Privilege> getPrivilege();

    List<VideoEnsemble> getVideoEnsemble();

    List<Follow> getFollow();

    List<User> getUser();

    User selectById(Integer id);

    List<Follow> selectFollowList(LambdaQueryWrapper<Follow> wrapper);
}
