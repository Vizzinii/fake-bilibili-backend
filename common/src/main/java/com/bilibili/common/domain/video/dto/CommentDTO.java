package com.bilibili.common.domain.video.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bilibili.common.domain.notice.entity.CommentNotice;
import com.bilibili.common.domain.video.entity.audience_reactions.Comment;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
@TableName("comment")
public class CommentDTO {

    @ApiModelProperty("发评论的用户的id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("所在视频id")
    @TableField("video_id")
    private Integer videoId;

    @ApiModelProperty("父级id")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty("评论内容")
    @TableField("cotent")
    private String content;

    @ApiModelProperty("顶层id")
    @TableField("top_id")
    private Integer topId;

    public Comment toEntity(){
        Comment danmaku=new Comment();
        BeanUtils.copyProperties(this,danmaku);
        return danmaku;
    }
    public CommentNotice toNotice(){
        CommentNotice commentNoticeAddOrDelete=new CommentNotice()
                .setReceiverId(parentId)
                .setVideoId(videoId);
        return commentNoticeAddOrDelete;
    }
}
