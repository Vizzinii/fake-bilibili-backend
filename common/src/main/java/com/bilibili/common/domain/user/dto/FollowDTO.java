package com.bilibili.common.domain.user.dto;
import com.bilibili.common.domain.user.entity.Follow;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class FollowDTO {
    int fansId;
    int idolId;
    public Follow toEntity(){
        Follow follow=new Follow();
        BeanUtils.copyProperties(this,follow);
        return follow;
    }
}
