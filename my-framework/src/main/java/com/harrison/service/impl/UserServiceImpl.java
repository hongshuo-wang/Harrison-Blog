package com.harrison.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harrison.domain.result.ResponseResult;
import com.harrison.domain.vo.UserInfoVo;
import com.harrison.enums.AppHttpCodeEnum;
import com.harrison.exception.SystemException;
import com.harrison.mapper.UserMapper;
import com.harrison.domain.entity.User;
import com.harrison.service.UserService;
import com.harrison.utils.BeanCopyUtils;
import com.harrison.utils.RedisCache;
import com.harrison.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;
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

    @Override
    public ResponseResult updateUserInfo(User user) {
        // 获取userId
        Long userId = SecurityUtils.getUserId();
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId, userId);
        updateWrapper.set(User::getAvatar, user.getAvatar());
        updateWrapper.set(User::getEmail, user.getEmail());
        updateWrapper.set(User::getNickName, user.getNickName());
        updateWrapper.set(User::getSex, user.getSex());
        update(updateWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult register(User user) {
        // 对用户名和昵称进行唯一判断
        if(userExist(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if(nickNameExist(user.getNickName())) {
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }
        // 对密码加密处理
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        // 将信息存到数据库
        save(user);
        return ResponseResult.okResult();
    }

    private boolean userExist(String username) {
        LambdaQueryWrapper<User> usernameWrapper = new LambdaQueryWrapper<>();
        usernameWrapper.eq(User::getUserName, username);
        return count(usernameWrapper) > 0;
    }
    private boolean nickNameExist(String nickname) {
        LambdaQueryWrapper<User> nicknameWrapper = new LambdaQueryWrapper<>();
        nicknameWrapper.eq(User::getNickName, nickname);
        return count(nicknameWrapper) > 0;
    }

}

