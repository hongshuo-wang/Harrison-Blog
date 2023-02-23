package com.harrison.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harrison.domain.entity.Role;

import java.util.List;

/**
 * 角色信息表(Role)表服务接口
 *
 * @author Harrison
 * @date 2023-02-22 01:40:55
 * @Description:
 */
public interface RoleService extends IService<Role> {

    List<String> selectRolesByUserId(Long userId);
}

