package com.bilibili.common.domain.video.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class VideoCommentVO {

    @ApiModelProperty("评论总数")
    private long commentCount;

    @ApiModelProperty("每个评论个体")
    private List<CommentVO> commentVOList;
}
