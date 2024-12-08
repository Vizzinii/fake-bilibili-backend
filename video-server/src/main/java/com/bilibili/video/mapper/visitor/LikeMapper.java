package com.bilibili.video.mapper.visitor;
import com.bilibili.common.domain.video.entity.audience_reactions.Like;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface LikeMapper extends MPJBaseMapper<Like> {
}
