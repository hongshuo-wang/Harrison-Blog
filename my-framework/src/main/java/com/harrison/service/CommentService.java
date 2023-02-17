package com.harrison.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harrison.domain.entity.Comment;
import com.harrison.domain.result.ResponseResult;
import com.harrison.domain.vo.CommentVo;
import com.harrison.domain.vo.PageVo;

/**
 * 评论表(Comment)表服务接口
 *
 * @author Harrison
 * @date 2023-02-17 17:01:58
 * @Description:  
 */
public interface CommentService extends IService<Comment> {

    ResponseResult<PageVo<CommentVo>> getCommentList(String commentType, Long articleId, Integer pageSize, Integer pageNum);

    ResponseResult addComment(Comment comment);
}

