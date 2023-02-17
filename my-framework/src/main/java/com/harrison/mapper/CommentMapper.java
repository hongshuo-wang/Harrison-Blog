package com.harrison.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.harrison.domain.entity.Comment;

/**
 * 评论表(Comment)表数据库访问层
 *
 * @author Harrison
 * @date 2023-02-17 17:01:58
 * @Description:  
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}

