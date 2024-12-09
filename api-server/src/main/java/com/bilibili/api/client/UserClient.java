package com.bilibili.api.client;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bilibili.common.domain.user.entity.Follow;
import com.bilibili.common.domain.user.entity.Privilege;
import com.bilibili.common.domain.user.entity.User;
import com.bilibili.common.domain.user.entity.VideoEnsemble;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "user-server")
public interface UserClient {

    @GetMapping("/user/getTableData/getPrivilegeAll")
    List<Privilege> getPrivilege();

    @GetMapping("/user/getTableData/getVideoEnsembleAll")
    List<VideoEnsemble> getVideoEnsemble();

    @GetMapping("/user/getTableData/getFollowAll")
    List<Follow> getFollow();

    @GetMapping("/user/getTableData/getUserAll")
    List<User> getUser();

    @GetMapping("/user/getTableData/selectUserById")
    User selectById(Integer id);

    @GetMapping("/user/getTableData/selectFollowList")
    List<Follow> selectFollowList(LambdaQueryWrapper<Follow> wrapper);
}
