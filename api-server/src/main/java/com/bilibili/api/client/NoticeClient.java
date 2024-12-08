package com.bilibili.api.client;

import com.bilibili.common.domain.notice.entity.CommentNotice;
import com.bilibili.common.domain.notice.entity.DynamicToUser;
import com.bilibili.common.domain.notice.entity.LikeNotice;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "notice-server")
public interface NoticeClient {

    @GetMapping("/getTableData/getLikeNotice")
    List<LikeNotice> getLikeNotice();

    @GetMapping("/getTableData/getCommentNotice")
    List<CommentNotice> getCommentNotice();

    @GetMapping("/getTableData/getDynamicToUser")
    List<DynamicToUser> getDynamicToUser();

}
