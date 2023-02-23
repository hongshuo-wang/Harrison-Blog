package com.harrison.controller;


import com.harrison.annotation.SystemLog;
import com.harrison.constants.SystemConstants;
import com.harrison.domain.entity.Comment;
import com.harrison.domain.query.CommentQuery;
import com.harrison.domain.query.PageQuery;
import com.harrison.domain.result.ResponseResult;
import com.harrison.domain.vo.CommentVo;
import com.harrison.domain.vo.PageVo;
import com.harrison.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 评论表(Comment)表控制层
 *
 * @author Harrison
 * @date 2023-02-17 17:02:18
 * @Description:
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @GetMapping("/commentList")
    @SystemLog(businessName = "查询评论列表")
    public ResponseResult<PageVo<CommentVo>> commentList(CommentQuery query) {
        return commentService.getCommentList(SystemConstants.ARTICLE_COMMENT, query.getArticleId(), query.getPageSize(), query.getPageNum());
    }

    @PostMapping
    @SystemLog(businessName = "用户添加评论")
    public ResponseResult addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    @GetMapping("/linkCommentList")
    @SystemLog(businessName = "获取友链评论")
    public ResponseResult getLinkCommentList(PageQuery query) {
        return commentService.getCommentList(SystemConstants.LINK_COMMENT, null, query.getPageSize(), query.getPageNum());
    }

}

