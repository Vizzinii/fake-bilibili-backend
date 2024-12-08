package com.bilibili.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bilibili.api.client.SendNoticeClient;
import com.bilibili.common.domain.user.entity.IdCount;
import com.bilibili.common.domain.video.dto.CommentDTO;
import com.bilibili.common.domain.video.entity.audience_reactions.Comment;
import com.bilibili.common.domain.video.entity.audience_reactions.Like;
import com.bilibili.common.domain.video.entity.video_production.Video;
import com.bilibili.common.domain.video.entity.video_production.VideoData;
import com.bilibili.common.domain.video.vo.CommentDetailVO;
import com.bilibili.common.domain.video.vo.CommentVO;
import com.bilibili.common.domain.video.vo.TopCommentVO;
import com.bilibili.common.domain.video.vo.VideoCommentVO;
import com.bilibili.video.mapper.visitor.CommentMapper;
import com.bilibili.video.mapper.visitor.LikeMapper;
import com.bilibili.video.mapper.blogger.VideoDataMapper;
import com.bilibili.video.mapper.blogger.VideoMapper;
import com.bilibili.common.util.Result;
import com.bilibili.video.mapper.VideoServiceMapper;
import com.bilibili.video.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    CommentMapper commentMapper;

    @Resource
    LikeMapper likeMapper;

    @Resource
    VideoMapper videoMapper;

    @Resource
    VideoDataMapper videoDataMapper;

    @Resource
    VideoServiceMapper videoServiceMapper;

    @Resource
    SendNoticeClient client;

    /**
     * 发送评论
     * @param commentDTO
     * @return
     */
    @Override
    public Result<Boolean> commentToComment(CommentDTO commentDTO) {
        Comment comment = commentDTO.toEntity();
        commentMapper.insert(comment);
        LambdaQueryWrapper<Video> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Video::getId,comment.getVideoId());
        //只有该评论不是自己对自己发送的才会生成评论通知
        if(!videoMapper.selectOne(wrapper).getUserId().equals(commentDTO.getUserId())){
            client.sendCommentNotice(commentDTO.toNotice().setSenderId(comment.getId()));
        }
        return Result.success(true);
    }

    /**
     * 获取评论
     * @param videoId
     * @param userId
     * @param type
     * @return
     */
    @Override
    public Result<VideoCommentVO> getComment(Integer videoId, Integer userId, Integer type) {
        // 查找 videoId 相符合的 VideoData 对象
        LambdaQueryWrapper<VideoData> commentCountWrapper = new LambdaQueryWrapper<>();
        commentCountWrapper.eq(VideoData::getVideoId,videoId);

        //查询对视频的评论和对评论的评论并合并到一起
        List<CommentDetailVO> commentToVideoVOs = videoServiceMapper.getCommentToVideo(videoId);
        List<CommentDetailVO> commentToCommentVOs= videoServiceMapper.getCommentToComment(videoId);
        commentToVideoVOs.addAll(commentToCommentVOs);

        //如果评论数大于0
        if (commentToVideoVOs.size() > 0) {
            Set<Integer> commentIds = commentToVideoVOs.stream()
                    .map(CommentDetailVO::getId)
                    .collect(Collectors.toSet());
            //查询点赞记录并封装到对应的评论
            LambdaQueryWrapper<Like> likeLambdaQueryWrapper = new LambdaQueryWrapper<>();
            likeLambdaQueryWrapper.in(Like::getCommentId, commentIds);
            likeLambdaQueryWrapper.eq(Like::getVideoId, videoId);
            likeLambdaQueryWrapper.eq(Like::getUserId, userId);
            List<Like> likes = likeMapper.selectList(likeLambdaQueryWrapper);
            Map<Integer, Boolean> likeMap = likes.stream()
                    .collect(Collectors.toMap(Like::getCommentId, like -> true, (existing, replacement) -> existing));
            commentToVideoVOs.forEach(response -> {
                response.setIsLiked(likeMap.getOrDefault(response.getId(), false));
            });
            List<IdCount> ids= commentMapper.getCommentCount(commentIds);
            Map<Integer, Integer> idCount=new HashMap<>(10);
            for(IdCount id : ids){
                idCount.put(id.getId(),id.getCount());
            }
            commentToVideoVOs.forEach(response -> {
                response.setLikeCount(idCount.getOrDefault(response.getId(), 0));
            });
        }

        //排序方式，根据值设置按点赞排序还是按创建时间排序，默认创建时间排序
        if(type==1){
            commentToVideoVOs.sort(Comparator.comparingInt(CommentDetailVO::getLikeCount).reversed());
        }
        long count= videoDataMapper.selectOne(commentCountWrapper).getCommentCount();
        Map<Integer, CommentVO> responseMap=new HashMap<>();

        // 将评论封装到对应的评论中
        for(CommentDetailVO response : commentToVideoVOs){
            //如果是对视频的评论，即topId为null，封装更多内容
            if(response.getTopId()==null){
                CommentVO commentVO=new CommentVO();
                commentVO.setTopCommentVO(new TopCommentVO().setCreateTime(response.getCreateTime()).setId(response.getId()).setContent(response.getContent()).setSenderId(response.getSenderId()).setLikeCount(response.getLikeCount()).setIsLiked(response.getIsLiked()).setSenderName(response.getSenderName()).setSenderCoverUrl(response.getSenderCoverUrl()));
                responseMap.put(response.getId(),commentVO);
            }

            //如果是对评论的评论
            else{
                CommentVO commentVO=responseMap.getOrDefault(response.getTopId(),new CommentVO());
                commentVO.getCommentDetailResponses().add(response);
            }
        }
        List<CommentVO> commentVOList=new ArrayList<>();
        for(Map.Entry<Integer,CommentVO> responseEntry : responseMap.entrySet()){
            commentVOList.add(responseEntry.getValue());
        }

        // 构建以返回一个 VideoCommentVO 对象
        VideoCommentVO videoCommentVO = new VideoCommentVO();
        videoCommentVO.setCommentVOList(commentVOList);
        videoCommentVO.setCommentCount(count);

        return Result.data(videoCommentVO);
    }

}
