package com.harrison.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.harrison.domain.entity.User;

/**
 * 用户表(User)表数据库访问层
 *
 * @author Harrison
 * @date 2023-02-17 01:57:40
 * @Description:
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

