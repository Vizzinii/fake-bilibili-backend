package com.bilibili.common.domain.video.dto;

import lombok.Data;

@Data
public class DeleteVideoDTO {
    private int userId;
    private int videoId;
}
