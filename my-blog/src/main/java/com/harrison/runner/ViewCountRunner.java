package com.harrison.runner;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.harrison.domain.entity.Article;
import com.harrison.mapper.ArticleMapper;
import com.harrison.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: Harrison
 * @date: 2023/2/21 0:51
 * @Description: TODO
 */
@Component
public class ViewCountRunner implements CommandLineRunner {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private RedisCache redisCache;
    @Override
    public void run(String... args) throws Exception {
        // 查询博客信息id, viewCount
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "view_count");
        List<Article> articleList = articleMapper.selectList(queryWrapper);
        Map<String, Integer> map = articleList.stream()
                .collect(Collectors.toMap(article->article.getId().toString(),
                        article->article.getViewCount().intValue()));
        // 存储到redis中
        redisCache.setCacheMap("article:viewCount", map);
    }
}
