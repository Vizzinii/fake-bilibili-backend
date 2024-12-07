package com.bilibili.common.domain.video.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class UploadProcessorVO {
    private double percent;
    private List<Integer> uploadedIndexList;
}