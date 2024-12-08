package com.bilibili.video.mapper.visitor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bilibili.common.domain.video.entity.audience_reactions.Danmaku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DanmakuMapper extends BaseMapper<Danmaku> {
    @Select("select * from danmaku")
    public List<Danmaku> getAll();
}