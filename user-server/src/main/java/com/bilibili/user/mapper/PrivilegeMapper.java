package com.bilibili.user.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bilibili.common.domain.user.entity.Privilege;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface PrivilegeMapper extends BaseMapper<Privilege> {
}
