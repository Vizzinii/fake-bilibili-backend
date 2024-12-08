package com.bilibili.chat.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bilibili.chat.service.GetTableDataService;
import com.bilibili.common.domain.chat.entity.Chat;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/table/getTableData")
@Api(tags = "获取表格数据相关接口")
@Slf4j
public class GetTableDataController {

    @Resource
    GetTableDataService getTableDataService;

    @GetMapping("selectCount")
    public long selectCount(LambdaQueryWrapper<Chat> chatLambdaQueryWrapper){
        return getTableDataService.selectCount(chatLambdaQueryWrapper);
    }

}
