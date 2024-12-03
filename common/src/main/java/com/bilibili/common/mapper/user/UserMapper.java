package com.bilibili.common.mapper.user;

import com.bilibili.common.domain.user.entity.User;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends MPJBaseMapper<User> {

}
