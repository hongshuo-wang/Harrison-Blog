package com.harrison.controller;


import com.harrison.annotation.SystemLog;
import com.harrison.domain.entity.Article;
import com.harrison.domain.query.CategoryQuery;
import com.harrison.domain.query.PageQuery;
import com.harrison.domain.result.ResponseResult;
import com.harrison.domain.vo.ArticleDetailVo;
import com.harrison.domain.vo.ArticleListVo;
import com.harrison.domain.vo.HotArticleVo;
import com.harrison.domain.vo.PageVo;
import com.harrison.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章表(Article)表控制层
 *
 * @author Harrison
 * @date 2023-02-12 21:05:52
 * @Description:
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 获取热门文章列表
     * @return
     */
    @GetMapping("/hotArticleList")
    public ResponseResult<List<HotArticleVo>> hotArticleList() {
        return articleService.hotArticleList();
    }

    /**
     * 获取分页结果
     * @param query
     * @return
     */
    @GetMapping("/articleList")
    @SystemLog(businessName = "查询文章列表")
    public ResponseResult<PageVo<ArticleListVo>> getArticleList(CategoryQuery query) {
        return articleService.getArticleList(query.getPageNum(), query.getPageSize(), query.getCategoryId());
    }

    @GetMapping("/{id}")
    @SystemLog(businessName = "查询文章详情")
    public ResponseResult<ArticleDetailVo> getArticleDetail(@PathVariable("id") Long id) {
        return articleService.getArticleDetail(id);
    }
    @PutMapping("/updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable("id") Long id) {
        return articleService.updateViewCount(id);
    }
}

