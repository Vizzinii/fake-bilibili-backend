package com.bilibili.video.mapper.blogger;
import com.bilibili.common.domain.video.entity.video_production.VideoData;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface VideoDataMapper extends MPJBaseMapper<VideoData> {

    // 构造函数、getters和setters可以由@Data注解自动生成
}
