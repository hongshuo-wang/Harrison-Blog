package com.harrison.controller;


import com.harrison.domain.entity.User;
import com.harrison.domain.result.ResponseResult;
import com.harrison.domain.vo.BlogUserLoginVo;
import com.harrison.enums.AppHttpCodeEnum;
import com.harrison.exception.SystemException;
import com.harrison.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 用户表(SysUser)表控制层
 *
 * @author Harrison
 * @date 2023-02-17 00:22:42
 * @Description:
 */
@RestController
public class BlogLoginController {

    @Autowired
    private BlogLoginService loginService;
    @PostMapping("/login")
    public ResponseResult<BlogUserLoginVo> login(@RequestBody User user) {
        // 判断用户名是否为空
        if(!StringUtils.hasText(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }
    @PostMapping("/logout")
    public ResponseResult logout() {
        return loginService.logout();
    }
}

