package com.harrison.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.harrison.domain.entity.Tag;

/**
 * 标签(Tag)表数据库访问层
 *
 * @author Harrison
 * @date 2023-02-21 23:23:17
 * @Description:
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

}

