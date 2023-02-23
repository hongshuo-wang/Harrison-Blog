package com.harrison.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harrison.constants.SystemConstants;
import com.harrison.mapper.MenuMapper;
import com.harrison.domain.entity.Menu;
import com.harrison.service.MenuService;
import com.harrison.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author Harrison
 * @date 2023-02-22 01:40:39
 * @Description:
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<String> selectPermsByUserId(Long userId) {
        // 如果是管理员，返回所有的权限
        if (SecurityUtils.isAdmin()) {
            LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(Menu::getMenuType, SystemConstants.MENU, SystemConstants.BUTTON);
            wrapper.eq(Menu::getStatus, SystemConstants.STATUS_NORMAL);
            List<Menu> menuList = list(wrapper);
            return menuList.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
        }
        // 否则，查询这个用户具有的权限信息
        return getBaseMapper().selectPermsByUserId(userId);
    }

    @Override
    public List<Menu> selectRouterMenuTreeByUserId(Long userId) {
        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menuList = null;
        // 判断是否为管理员
        if (SecurityUtils.isAdmin()) {
            menuList = menuMapper.selectAllRouterMenu();
        } else {
            menuList = menuMapper.selectRouterMenuTreeByUserId(userId);
        }
        // 构建menuTree
        List<Menu> menuTree = buildMenuTree(menuList, 0L);
        return menuTree;
    }

    /**
     * 拿到所有菜单后，将其构建成menuTree
     *
     * @param menuList
     * @param parentId
     * @return
     */
    private List<Menu> buildMenuTree(List<Menu> menuList, Long parentId) {
        // 先找到第一层的菜单，再找他们的子菜单赋值到父菜单的children上去
        return menuList.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(gerChildren(menu, menuList)))
                .collect(Collectors.toList());
    }

    /**
     * 获取传入参数的子Menu
     *
     * @param parentMenu 上一级菜单
     * @param menuList   所有菜单
     * @return
     */
    private List<Menu> gerChildren(Menu parentMenu, List<Menu> menuList) {
        return menuList.stream()
                .filter(menu -> menu.getParentId().equals(parentMenu.getId()))
                // 递归对子菜单再进行设置
                .map(childMenu -> childMenu.setChildren(gerChildren(childMenu, menuList)))
                .collect(Collectors.toList());
    }
}

