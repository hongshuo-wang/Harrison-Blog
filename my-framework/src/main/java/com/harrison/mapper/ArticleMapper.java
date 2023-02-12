package com.harrison.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.harrison.domain.entity.Article;

/**
 * 文章表(Article)表数据库访问层
 *
 * @author Harrison
 * @date 2023-02-12 21:05:37
 * @Description:
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}

