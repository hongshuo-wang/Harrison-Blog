package com.harrison.controller;


import com.harrison.domain.entity.Article;
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

    @GetMapping("/list")
    public List<Article> list() {
        return articleService.list();
    }

}

