package com.harrison.job;

import com.harrison.domain.entity.Article;
import com.harrison.service.ArticleService;
import com.harrison.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: Harrison
 * @date: 2023/2/21 14:15
 * @Description: TODO
 */
@Component
public class UpdateViewCountJob {
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ArticleService articleService;
    @Scheduled(cron = "0/59 * * * * ?")
    public void updateViewCount() {
        //定时从redis中获取浏览量
        Map<String, Integer> viewCountMap = redisCache.getCacheMap("article:viewCount");
        List<Article> articleList = viewCountMap.entrySet()
                .stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());

        // 更新到数据库
        articleService.updateBatchById(articleList);
    }
}
