package com.harrison.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harrison.domain.result.ResponseResult;
import com.harrison.mapper.ArticleMapper;
import com.harrison.domain.entity.Article;
import com.harrison.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.harrison.domain.result.ResponseResult.okResult;

/**
 * 文章表(Article)表服务实现类
 *
 * @author Harrison
 * @date 2023-02-12 21:05:37
 * @Description:
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public ResponseResult<List<Article>> hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 必须是正式文章，不能是草稿
        queryWrapper.eq(Article::getStatus, 0);
        // 根据浏览量降序排列
        queryWrapper.orderByDesc(Article::getViewCount);
        // 分页取前10条
        Page<Article> page = new Page<>(1, 10);
        page(page, queryWrapper);

        // 获取分页结果
        List<Article> articles = page.getRecords();
        return ResponseResult.okResult(articles);
    }
}

