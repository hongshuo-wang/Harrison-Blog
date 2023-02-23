package com.harrison.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.harrison.domain.entity.Role;

import java.util.List;

/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author Harrison
 * @date 2023-02-22 01:40:55
 * @Description:
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRolesByUserId(Long userId);
}

