package com.bilibili.notice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bilibili.common.domain.notice.entity.DynamicToUser;
import com.bilibili.notice.mapper.DynamicToUserMapper;
import com.bilibili.notice.service.DynamicToUserService;
import org.springframework.stereotype.Service;

/**
 *批量插入会用到的mybatis-plus模版写法
 */
@Service
public class DynamicToUserToUserServiceImpl extends ServiceImpl<DynamicToUserMapper, DynamicToUser> implements DynamicToUserService {
}
