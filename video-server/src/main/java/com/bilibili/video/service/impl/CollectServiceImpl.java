package com.bilibili.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bilibili.api.client.SendNoticeClient;
import com.bilibili.common.domain.video.dto.CollectDTO;
import com.bilibili.common.domain.video.dto.CollectGroupDTO;
import com.bilibili.common.domain.video.entity.audience_reactions.Collect;
import com.bilibili.common.domain.video.entity.audience_reactions.CollectGroup;
import com.bilibili.common.domain.video.vo.CollectGroupVO;
import com.bilibili.common.domain.video.vo.CollectVideoVO;
import com.bilibili.video.mapper.visitor.CollectGroupMapper;
import com.bilibili.video.mapper.visitor.CollectMapper;
import com.bilibili.common.util.Result;
import com.bilibili.video.mapper.VideoServiceMapper;
import com.bilibili.video.service.CollectService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectServiceImpl implements CollectService {

    @Resource
    CollectMapper collectMapper;

    @Resource
    CollectGroupMapper collectGroupMapper;

    @Resource
    VideoServiceMapper videoServiceMapper;

    @Resource
    SendNoticeClient client;

    /**
     * 新增收藏视频
     * 通过查询并改变状态来实现。是对db里视频的状态进行修改来实现“已收藏”和“取消收藏”的状态变化。
     * @param collectDTOs
     * @return
     */
    @Override
    public Result<Boolean> collect(List<CollectDTO> collectDTOs) {
        for(CollectDTO collectDTO : collectDTOs){
            //如果收藏夹的 type 值是 true 则为收藏;否则是取消收藏
            if(collectDTO.getType()){
                Collect collect = new Collect();
                collect.setCollectGroupId(collectDTO.getCollectGroupId());
                collect.setVideoId(collectDTO.getVideoId());
                collectMapper.insert(collect);
            }else {
                LambdaQueryWrapper<Collect> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(Collect::getVideoId, collectDTO.getVideoId());
                wrapper.eq(Collect::getCollectGroupId, collectDTO.getCollectGroupId());
                collectMapper.delete(wrapper);
            }
        }
        return Result.success(true);
    }

    /**
     * 获取收藏夹，及列表中的视频
     * @param userId
     * @param videoId
     * @return
     */
    @Override
    public Result<List<CollectGroupVO>> getVideoToCollectGroup(Integer userId, Integer videoId) {
        // 构建查询条件，获取当前用户的收藏夹
        LambdaQueryWrapper<CollectGroup> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(CollectGroup::getUserId,userId);
        List<CollectGroup> collectGroupList=collectGroupMapper.selectList(wrapper);

        // 如果收藏夹不为空，则进行下一步操作
        // 如果收藏夹列表为空，则直接返回空列表
        if(collectGroupList.size()>0){
            List<Integer> ids=new ArrayList<>();
            Map<Integer, CollectGroupVO> map=new HashMap<>();

            // 遍历收藏夹列表，将收藏夹id和收藏夹对象存入map中
            // 获取收藏夹的视频id集合用于查询收藏记录，并封装成最后的响应类的一部分
            for(CollectGroup collectGroup : collectGroupList){
                ids.add(collectGroup.getId());
                map.put(collectGroup.getId(),new CollectGroupVO().setCollectGroupId(collectGroup.getId()).setCollectGroupName(collectGroup.getName()).setCreateTime(collectGroup.getCreateTime()));
            }
            LambdaQueryWrapper<Collect> collectLambdaQueryWrapper=new LambdaQueryWrapper<>();
            collectLambdaQueryWrapper.in(Collect::getCollectGroupId,ids);
            collectLambdaQueryWrapper.eq(Collect::getVideoId,videoId);
            List<Collect> collects=collectMapper.selectList(collectLambdaQueryWrapper);

            //设置收藏夹的状态，可能有些收藏夹并没有收藏该视频则为默认值false
            for(Collect collect : collects){
                if(map.get(collect.getCollectGroupId())!=null){
                    map.get(collect.getCollectGroupId()).setHasCollect(true);
                }
            }

            //遍历收藏夹map并封装成集合
            List<CollectGroupVO> collectGroupVOList=new ArrayList<>();
            for(Map.Entry<Integer,CollectGroupVO> entry : map.entrySet()){
                collectGroupVOList.add(entry.getValue());
            }
            return Result.data(collectGroupVOList);
        }
        return Result.data(new ArrayList<>());
    }

    /**
     * 新增及更新收藏夹
     * @param createCollectGroupDTO
     * @return
     */
    @Override
    public Result<Boolean> editCollectGroup(CollectGroupDTO createCollectGroupDTO) {
        if(createCollectGroupDTO.getId() != null){
            collectGroupMapper.updateById(createCollectGroupDTO.toEntity());
        }else {
            collectGroupMapper.insert(createCollectGroupDTO.toEntity());
        }
        return Result.success(true);
    }

    /**
     * 删除收藏夹
     * @param collectGroupDTO
     * @return
     */
    @Override
    public Result<Boolean> deleteCollectGroup(CollectGroupDTO collectGroupDTO) {
        collectGroupMapper.deleteById(collectGroupDTO.toEntity());
        return Result.success(true);
    }

    /**
     * 获取收藏夹列表
     * @param userId
     * @return
     */
    @Override
    public Result<List<CollectGroup>> getCollectGroup(Integer userId) {
        LambdaQueryWrapper<CollectGroup> collectGroupQueryWrapper = new LambdaQueryWrapper<>();
        collectGroupQueryWrapper.eq(CollectGroup::getUserId, userId);
        collectGroupQueryWrapper.orderByDesc(CollectGroup::getCreateTime);
        return Result.data(collectGroupMapper.selectList(collectGroupQueryWrapper));
    }

    /**
     * 获取某个收藏夹下的视频
     * @param collectGroupId
     * @return
     */
    @Override
    public Result<List<CollectVideoVO>> getCollectVideo(Integer collectGroupId) {
        return Result.data(videoServiceMapper.getCollectVideo(collectGroupId));
    }
}
