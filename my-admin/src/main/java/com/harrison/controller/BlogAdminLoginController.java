package com.harrison.controller;


import com.harrison.annotation.SystemLog;
import com.harrison.domain.entity.LoginUser;
import com.harrison.domain.entity.Menu;
import com.harrison.domain.entity.User;
import com.harrison.domain.result.ResponseResult;
import com.harrison.domain.vo.AdminUserInfoVo;
import com.harrison.domain.vo.BlogUserLoginVo;
import com.harrison.domain.vo.RouterVo;
import com.harrison.domain.vo.UserInfoVo;
import com.harrison.enums.AppHttpCodeEnum;
import com.harrison.exception.SystemException;
import com.harrison.service.BlogAdminLoginService;
import com.harrison.service.MenuService;
import com.harrison.service.RoleService;
import com.harrison.utils.BeanCopyUtils;
import com.harrison.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 管理员登录
 *
 * @author Harrison
 * @date 2023-02-17 00:22:42
 * @Description:
 */
@RestController
public class BlogAdminLoginController {

    @Autowired
    private BlogAdminLoginService adminLoginService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;

    @PostMapping("/user/login")
    @SystemLog(businessName = "用户登录")
    public ResponseResult login(@RequestBody User user) {
        // 判断用户名是否为空
        if(!StringUtils.hasText(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return adminLoginService.login(user);
    }
    @PostMapping("/logout")
    @SystemLog(businessName = "用户退出登录")
    public ResponseResult logout() {
        return adminLoginService.logout();
    }

    @GetMapping("/getInfo")
    public ResponseResult<AdminUserInfoVo> getUserInfo() {
        // 获取当前用户的id
        Long userId = SecurityUtils.getUserId();
        LoginUser loginUser = SecurityUtils.getLoginUser();
        // 根据用户id查询角色信息
        List<String> perms = menuService.selectPermsByUserId(userId);
        // 根据用户id查询权限信息
        List<String> roles = roleService.selectRolesByUserId(userId);
        // bean拷贝
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        // 封装结果返回
        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms, roles, userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);
    }

    @GetMapping("/getRouters")
    public ResponseResult<RouterVo> getRouters() {
        Long userId = SecurityUtils.getUserId();
        // 查询menu字段 结果是tree形式
        List<Menu> menus = menuService.selectRouterMenuTreeByUserId(userId);
        return ResponseResult.okResult(new RouterVo(menus));
    }
}

