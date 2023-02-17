package com.harrison.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.harrison.domain.entity.Category;

/**
 * 分类表(Category)表数据库访问层
 *
 * @author Harrison
 * @date 2023-02-14 17:37:58
 * @Description:
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}

