package com.bilibili.common.domain.video.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommentVO {
    @ApiModelProperty("顶级评论")
    public TopCommentVO topCommentResponse=new TopCommentVO();
    @ApiModelProperty("后续层评论")
    public List<CommentDetailVO> commentDetailResponses=new ArrayList<>();
}
