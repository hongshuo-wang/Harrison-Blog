package com.harrison.controller;

import com.harrison.domain.result.ResponseResult;
import com.harrison.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Harrison
 * @date: 2023/2/21 23:27
 * @Description: TODO
 */
@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Autowired
    private TagService tagService;
    @GetMapping("/list")
    public ResponseResult getTags() {
        return ResponseResult.okResult(tagService.list());
    }

}
