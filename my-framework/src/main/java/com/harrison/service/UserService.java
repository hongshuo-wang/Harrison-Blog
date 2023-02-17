package com.harrison.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harrison.domain.entity.User;
import com.harrison.domain.result.ResponseResult;

/**
 * 用户表(User)表服务接口
 *
 * @author Harrison
 * @date 2023-02-17 19:12:56
 * @Description:
 */
public interface UserService extends IService<User> {

    ResponseResult getUserInfo();
}

