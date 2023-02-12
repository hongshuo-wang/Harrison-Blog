package com.harrison.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harrison.domain.entity.Article;
import com.harrison.domain.result.ResponseResult;

import java.util.List;

/**
 * 文章表(Article)表服务接口
 *
 * @author Harrison
 * @date 2023-02-12 21:05:37
 * @Description:
 */
public interface ArticleService extends IService<Article> {

    /**
     * 查询热门文章，封装成ResponseResult返回
     * @return 返回热门文章结果
     */
    ResponseResult<List<Article>> hotArticleList();
}

