package com.bilibili.common.domain.user.dto;

import com.bilibili.common.domain.user.entity.VideoEnsemble;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class AddOrUpdateEnsembleDTO {
    public Integer userId;
    public Integer id;
    public String name;
    public String intro;

    public VideoEnsemble toEntity(){
        VideoEnsemble videoEnsemble=new VideoEnsemble();
        BeanUtils.copyProperties(this,videoEnsemble);
        return videoEnsemble;
    }
}
