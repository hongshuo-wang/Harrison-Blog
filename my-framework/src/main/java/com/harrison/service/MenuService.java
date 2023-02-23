package com.harrison.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harrison.domain.entity.Menu;

import java.util.List;

/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author Harrison
 * @date 2023-02-22 01:40:39
 * @Description:
 */
public interface MenuService extends IService<Menu> {

    List<String> selectPermsByUserId(Long userId);

    List<Menu> selectRouterMenuTreeByUserId(Long userId);
}

