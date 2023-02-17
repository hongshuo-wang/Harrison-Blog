package com.harrison.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harrison.domain.entity.Category;
import com.harrison.domain.result.ResponseResult;
import com.harrison.domain.vo.CategoryVo;

import java.util.List;

/**
 * 分类表(Category)表服务接口
 *
 * @author Harrison
 * @date 2023-02-14 17:37:57
 * @Description:
 */
public interface CategoryService extends IService<Category> {

    ResponseResult<List<CategoryVo>> getCategoryList();
}

