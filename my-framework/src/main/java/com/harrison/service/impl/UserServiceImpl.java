package com.harrison.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harrison.domain.entity.LoginUser;
import com.harrison.domain.result.ResponseResult;
import com.harrison.domain.vo.UserInfoVo;
import com.harrison.mapper.UserMapper;
import com.harrison.domain.entity.User;
import com.harrison.service.UserService;
import com.harrison.utils.BeanCopyUtils;
import com.harrison.utils.RedisCache;
import com.harrison.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author Harrison
 * @date 2023-02-17 19:12:56
 * @Description:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult getUserInfo() {
        // 解析token拿到userId
        Long userId = SecurityUtils.getUserId();
        // 利用userId从redis中获取用户信息
        User user = getById(userId);
        // 封装成UserVo对象
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        // 返回
        return ResponseResult.okResult(userInfoVo);
    }
}

