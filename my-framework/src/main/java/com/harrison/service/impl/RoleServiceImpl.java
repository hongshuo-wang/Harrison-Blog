package com.harrison.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harrison.mapper.RoleMapper;
import com.harrison.domain.entity.Role;
import com.harrison.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author Harrison
 * @date 2023-02-22 01:40:55
 * @Description:
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<String> selectRolesByUserId(Long userId) {
        // 查询所有角色信息
        return getBaseMapper().selectRolesByUserId(userId);
    }
}

