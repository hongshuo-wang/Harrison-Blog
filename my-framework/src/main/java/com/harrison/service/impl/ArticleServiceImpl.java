package com.harrison.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harrison.mapper.ArticleMapper;
import com.harrison.domain.entity.Article;
import com.harrison.service.ArticleService;
import org.springframework.stereotype.Service;

/**
 * 文章表(Article)表服务实现类
 *
 * @author Harrison
 * @date 2023-02-12 21:05:37
 * @Description:
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}

