package com.harrison.controller;


import com.harrison.domain.result.ResponseResult;
import com.harrison.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseResult getUserInfo() {
        return userService.getUserInfo();
    }


}

