package com.harrison.domain.vo;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.annotation.JSONField;
import com.alibaba.fastjson2.annotation.JSONType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.harrison.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author: Harrison
 * @date: 2023/2/17 17:27
 * @Description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {
    /**
     * 子评论列表
     */
    List<CommentVo> children;
    /**
     * 头像所在url
     */
    private String avatar;
    private Long id;
    /**
     * 评论类型（0代表文章评论，1代表友链评论）
     */
    private String type;
    /**
     * 文章id
     */
    private Long articleId;
    /**
     * 根评论id
     */
    private Long rootId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 谁的评论
     */
    private String username;
    /**
     * 所回复的目标评论的userid
     */
    private Long toCommentUserId;
    /**
     * 所回复的目标评论的username
     */
    private String toCommentUserName;
    /**
     * 回复目标评论id
     */
    private Long toCommentId;
    private Long createBy;
    private Date createTime;
}
