package com.bilibili.api.client;

import com.bilibili.common.domain.user.entity.Follow;
import com.bilibili.common.domain.user.entity.Privilege;
import com.bilibili.common.domain.user.entity.User;
import com.bilibili.common.domain.user.entity.VideoEnsemble;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "user-server")
public interface UserClient {

    @GetMapping("/getTableData/getPrivilege")
    List<Privilege> getPrivilege();

    @GetMapping("/getTableData/getVideoEnsemble")
    List<VideoEnsemble> getVideoEnsemble();

    @GetMapping("/getTableData/getFollow")
    List<Follow> getFollow();

    @GetMapping("/getTableData/getUser")
    List<User> getUser();
}
