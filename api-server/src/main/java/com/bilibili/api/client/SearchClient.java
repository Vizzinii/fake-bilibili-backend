package com.bilibili.api.client;

import com.bilibili.common.domain.api.pojo.RecommendVideo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "search",url = "http://localhost:8221")
@Component
public interface SearchClient {
    @GetMapping("/search/likelyVideoRecommend/{videoId}")
    List<RecommendVideo> getRecommendVideo(@PathVariable String videoId);
}
