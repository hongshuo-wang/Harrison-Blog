package com.harrison.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harrison.constants.SystemConstants;
import com.harrison.domain.entity.User;
import com.harrison.domain.result.ResponseResult;
import com.harrison.domain.vo.CommentVo;
import com.harrison.domain.vo.PageVo;
import com.harrison.enums.AppHttpCodeEnum;
import com.harrison.exception.SystemException;
import com.harrison.mapper.CommentMapper;
import com.harrison.domain.entity.Comment;
import com.harrison.service.CommentService;
import com.harrison.service.UserService;
import com.harrison.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author Harrison
 * @date 2023-02-17 17:01:58
 * @Description:  
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;
    @Override
    public ResponseResult<PageVo<CommentVo>> getCommentList(String commentType, Long articleId, Integer pageSize, Integer pageNum) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        // 先找对应文章的评论(友链就是全部的，不需要文章Id)
        queryWrapper.eq(SystemConstants.STATUS_NORMAL.equals(commentType), Comment::getArticleId, articleId);
        // 再找根评论, rootId = -1
        queryWrapper.orderByDesc(Comment::getCreateTime);
        queryWrapper.eq(Comment::getRootId, -1);
        // 根据评论类型是友链还是文章评论
        queryWrapper.eq(Comment::getType, commentType);
        // 分页查询
        Page<Comment> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        // 拷贝Comment对象
        List<CommentVo> commentVos = toCommentList(page.getRecords());
        // 查询子评论，看rootId=id的即可
        List<CommentVo> commentWithChildren = getChildren(commentVos);
        return ResponseResult.okResult(new PageVo<>(commentWithChildren, page.getTotal()));
    }


    /**
     * 获取子评论
     * @param commentVos
     * @return
     */
    private List<CommentVo> getChildren(List<CommentVo> commentVos) {
        commentVos
                .forEach(vo -> {
                    LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
                    queryWrapper.eq(Comment::getRootId, vo.getId());
                    queryWrapper.orderByAsc(Comment::getCreateTime);
                    List<Comment> childrenComments = list(queryWrapper);
                    List<CommentVo> childrenCommentsVo = toCommentList(childrenComments);
                    vo.setChildren(childrenCommentsVo);
                });
        return commentVos;
    }

    /**
     * 将查询到的comment封装到commentVo中
     * @param list
     * @return
     */
    private List<CommentVo> toCommentList(List<Comment> list) {
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        commentVos.stream()
                .peek(vo -> {
                    // 通过create by查询nickName
                    User user = userService.getById(vo.getCreateBy());
                    String nickName = user.getNickName();
                    String avatar = user.getAvatar();
                    vo.setUsername(nickName);
                    vo.setAvatar(avatar);
                })
                /*
                 根据toCommentUserId查询根据toCommentUserIdName
                 回复过其他人的评论，不只是跟评，这种才有toCommentUserName
                 只对这种进行更改
                */
                .filter(vo -> vo.getToCommentUserId() != -1)
                .forEach(vo -> vo.setToCommentUserName(userService.getById(vo.getToCommentUserId()).getNickName()));
        return commentVos;
    }


    @Override
    public ResponseResult addComment(Comment comment) {
        if(!StringUtils.hasText(comment.getContent())) {
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }

        save(comment);
        return ResponseResult.okResult();
    }
}

