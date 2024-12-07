package com.bilibili.common.domain.video.entity.audience_reactions;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("collect")
public class Collect {
    // 视频id
    @TableField("video_id")
    private Integer videoId;

    // 创建时间
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    // 收藏组id
    @TableField("collect_group_id")
    private int collectGroupId;

    // 用户id
    @TableId(type = IdType.AUTO)
    private Integer id;

}
