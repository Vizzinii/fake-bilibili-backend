package com.bilibili.common.domain.video.dto;

import com.bilibili.common.domain.api.pojo.LikeNoticeAddOrDelete;
import com.bilibili.common.domain.video.entity.audience_reactions.Like;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class LikeDTO {

    @ApiModelProperty("发评论的用户id")
    private Integer userId;

    @ApiModelProperty("评论所在视频的id")
    private Integer videoId;

    @ApiModelProperty("点赞的评论的id")
    private Integer commentId;

    public Like toEntity(){
        Like danmaku = new Like();
        BeanUtils.copyProperties(this,danmaku);
        return danmaku;
    }
    public LikeNoticeAddOrDelete toAddOrDeleteNotice(){
        LikeNoticeAddOrDelete likeNotice=new LikeNoticeAddOrDelete();
        BeanUtils.copyProperties(this,likeNotice);
        likeNotice.setSenderId(userId);
        return likeNotice;
    }
}