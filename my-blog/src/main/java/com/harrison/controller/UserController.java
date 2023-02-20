package com.harrison.controller;


import com.harrison.annotation.SystemLog;
import com.harrison.domain.entity.User;
import com.harrison.domain.result.ResponseResult;
import com.harrison.service.UserService;
import com.harrison.validate.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户表(User)表控制层
 *
 * @author Harrison
 * @date 2023-02-17 23:43:17
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("userInfo")
    @SystemLog(businessName = "获取用户信息")
    public ResponseResult getUserInfo() {
        return userService.getUserInfo();
    }

    @PutMapping("/userInfo")
    @SystemLog(businessName = "更新用户信息")
    public ResponseResult updateUserInfo(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }

    @PostMapping("/register")
    @SystemLog(businessName = "用户注册")
    public ResponseResult register(@Validated(value = ValidGroup.Crud.Create.class) @RequestBody User user) {
        return userService.register(user);
    }
}

