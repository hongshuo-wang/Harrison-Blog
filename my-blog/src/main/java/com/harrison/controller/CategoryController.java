package com.harrison.controller;



import com.harrison.annotation.SystemLog;
import com.harrison.domain.result.ResponseResult;
import com.harrison.domain.vo.CategoryVo;
import com.harrison.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类表(Category)表控制层
 *
 * @author Harrison
 * @date 2023-02-14 17:38:29
 * @Description:  
 */
@RestController
@RequestMapping("/category")
public class CategoryController{
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/getCategoryList")
    @SystemLog(businessName = "获取分类列表")
    public ResponseResult<List<CategoryVo>> getCategoryList() {
        return categoryService.getCategoryList();
    }

}

