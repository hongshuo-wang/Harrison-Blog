package com.harrison.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harrison.constants.SystemConstants;
import com.harrison.domain.entity.Article;
import com.harrison.domain.result.ResponseResult;
import com.harrison.domain.vo.CategoryVo;
import com.harrison.mapper.CategoryMapper;
import com.harrison.domain.entity.Category;
import com.harrison.service.ArticleService;
import com.harrison.service.CategoryService;
import com.harrison.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author Harrison
 * @date 2023-02-14 17:37:57
 * @Description:
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;
    @Override
    public ResponseResult<List<CategoryVo>> getCategoryList() {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        // 文章非草稿
        wrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // 查询所有正文
        List<Article> articles = articleService.list(wrapper);
        // 获取文章的分类id，并进行去重
        List<Long> categoryId = articles.stream()
                .map(Article::getCategoryId)
                // 对id进行去重
                .distinct()
                .collect(Collectors.toList());
        // 再根据正文的所有id查分类表拿到有正文的分类
        List<Category> categories = listByIds(categoryId);
        // 找到状态正常的分类
        categories = categories.stream()
                .filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        // 拷贝对象
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
        return ResponseResult.okResult(categoryVos);
    }
}

