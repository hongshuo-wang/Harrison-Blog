package com.harrison.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harrison.constants.SystemConstants;
import com.harrison.domain.entity.Article;
import com.harrison.domain.entity.Category;
import com.harrison.domain.result.ResponseResult;
import com.harrison.domain.vo.ArticleDetailVo;
import com.harrison.domain.vo.ArticleListVo;
import com.harrison.domain.vo.HotArticleVo;
import com.harrison.domain.vo.PageVo;
import com.harrison.mapper.ArticleMapper;
import com.harrison.mapper.CategoryMapper;
import com.harrison.service.ArticleService;
import com.harrison.service.CategoryService;
import com.harrison.utils.BeanCopyUtils;
import com.harrison.utils.RedisCache;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * 文章表(Article)表服务实现类
 *
 * @author Harrison
 * @date 2023-02-12 21:05:37
 * @Description:
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult<List<HotArticleVo>> hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 必须是正式文章，不能是草稿
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // 根据浏览量降序排列
        queryWrapper.orderByDesc(Article::getViewCount);
        // 分页取前10条
        IPage<Article> page = new Page<>(1, 10);
        page(page, queryWrapper);

        // 获取分页结果
        List<Article> articles = page.getRecords();
        List<HotArticleVo> hotArticleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
        return ResponseResult.okResult(hotArticleVos);
    }

    @Override
    public ResponseResult<PageVo<ArticleListVo>> getArticleList(Integer pageNum, Integer pageSize, Long categoryId) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 查询是否传入了categoryId
        queryWrapper.eq(Objects.nonNull(categoryId)&&(categoryId>0), Article::getCategoryId, categoryId);
        // 状态是正式发布的
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // 按照isTop倒排
        queryWrapper.orderByDesc(Article::getIsTop);
        // 分页，所有结果都从page中拿
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        List<Article> articles = page.getRecords();
        // 拿到分类id，查询分类名称，赋值
        articles = articles.stream()
                .map(article ->
                        article.setCategoryName(
                                categoryMapper.selectById(article.getCategoryId()).getName())
                ).collect(Collectors.toList());
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articles, ArticleListVo.class);
        // 返回的需要时rows + total，二次封装
        PageVo<ArticleListVo> pageVo = new PageVo<>(articleListVos, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }


    @Override
    public ResponseResult<ArticleDetailVo> getArticleDetail(Long id) {
        // 根据id查询文章
        Article article = getById(id);
        // 从redis中获取viewCount
        Integer viewCount = redisCache.getCacheMapValue("article:viewCount", id.toString());
        article.setViewCount(viewCount.longValue());
        // 拷贝bean
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        // 跟据分类id查询分类名
        Category category = categoryMapper.selectById(articleDetailVo.getCategoryId());
        if(category != null) {
            articleDetailVo.setCategoryName(category.getName());
        }
        // 返回
        return ResponseResult.okResult(articleDetailVo);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        redisCache.incrementCacheMapValue("article:viewCount", id.toString(), 1);
        return ResponseResult.okResult();
    }
}

