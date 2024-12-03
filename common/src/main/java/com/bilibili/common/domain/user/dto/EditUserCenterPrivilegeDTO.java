package com.bilibili.common.domain.user.dto;

import com.bilibili.common.domain.user.entity.Privilege;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class EditUserCenterPrivilegeDTO {
    int userId;
    int collectGroup;
    int remotelyLike;
    int fansList;
    int idolList;
    public Privilege toEntity(){
        Privilege privilege=new Privilege();
        BeanUtils.copyProperties(this,privilege);
        return privilege;
    }
}
