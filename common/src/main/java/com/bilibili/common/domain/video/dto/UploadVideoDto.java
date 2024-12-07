package com.bilibili.common.domain.video.dto;

import com.bilibili.common.domain.notice.entity.Dynamic;
import com.bilibili.common.domain.user.entity.User;
import com.bilibili.common.domain.video.entity.video_production.Video;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

public class UploadVideoDto {
    @ApiModelProperty("视频路径")
    private String url;
    @ApiModelProperty("视频名称")
    private String name;
    @ApiModelProperty("视频介绍")
    private String intro;
    @ApiModelProperty("作者id")
    private int userId;
    @ApiModelProperty("视频封面")
    private String videoCover;
    public Video toEntity() {
        Video video = new Video();
        BeanUtils.copyProperties(this, video);
        return video;
    }


    public Dynamic toNoCoverDynamic(User user, Video video){
        return new Dynamic().setVideoId(video.getId()).setVideoName(video.getName())
                .setAuthorId(user.getId());
    }
    public Dynamic toCoverDynamic(User user, Video video){
        return new Dynamic().setVideoId(video.getId()).setVideoCover(video.getCover()).setVideoName(video.getName())
                .setAuthorId(user.getId());
    }
}
