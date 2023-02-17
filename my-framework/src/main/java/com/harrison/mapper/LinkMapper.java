package com.harrison.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.harrison.domain.entity.Link;

/**
 * 友链(Link)表数据库访问层
 *
 * @author Harrison
 * @date 2023-02-16 19:52:05
 * @Description:
 */
@Mapper
public interface LinkMapper extends BaseMapper<Link> {

}

