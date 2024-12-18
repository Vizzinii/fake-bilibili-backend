package com.bilibili.common.domain.user.dto;

import com.bilibili.common.constant.UserConstant;
import com.bilibili.common.domain.user.entity.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Data
public class EditUserInfoDTO {
    private Integer id;
    private String nickname;
    private MultipartFile coverImg;
    private String intro;
    public User toEntity(){
        User user =new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }
    public Map<String,Object> toMap(){
        Map<String,Object> map=new HashMap<>();
        map.put(UserConstant.TABLE_NAME, UserConstant.USER_TABLE_NAME);
        map.put(UserConstant.OPERATION_TYPE, UserConstant.OPERATION_TYPE_UPDATE);
        map.put(UserConstant.TABLE_ID,id);
        if(nickname!=null){
            map.put(UserConstant.USER_NICKNAME,nickname);
        }
        if(intro!=null){
            map.put(UserConstant.USER_INTRO,intro);
        }
        return map;
    }
}
