package com.harrison.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.harrison.domain.entity.Article;
import com.harrison.domain.result.ResponseResult;
import com.harrison.domain.vo.ArticleDetailVo;
import com.harrison.domain.vo.ArticleListVo;
import com.harrison.domain.vo.HotArticleVo;
import com.harrison.domain.vo.PageVo;

import java.util.List;

/**
 * 文章表(Article)表服务接口
 *
 * @author Harrison
 * @date 2023-02-13 22:20:07
 * @Description:
 */
public interface ArticleService extends IService<Article> {
    /**
     * 查询前10个热门文章列表
     * @return 热门文章列表
     */
    ResponseResult<List<HotArticleVo>> hotArticleList();

    /**
     * 获取文章列表
     * @param pageNum 第几页
     * @param pageSize 一页几条数据
     * @param categoryId 分类id
     * @return
     */
    ResponseResult<PageVo<ArticleListVo>> getArticleList(Integer pageNum, Integer pageSize, Long categoryId);

    /**
     * 获取文章详情
     * @param id 文章id
     * @return
     */
    ResponseResult<ArticleDetailVo> getArticleDetail(Long id);
}

