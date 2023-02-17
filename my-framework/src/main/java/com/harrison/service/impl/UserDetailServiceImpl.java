package com.harrison.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.harrison.domain.entity.LoginUser;
import com.harrison.domain.entity.User;
import com.harrison.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author: Harrison
 * @date: 2023/2/17 1:55
 * @Description: 重写UserDetailsService，改为去数据库访问查询用户
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(wrapper);
        //如果查询不到数据就通过抛出异常来给出提示
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名错误");
        }
        //TODO 根据用户查询权限信息 添加到LoginUser中

        //自定义一个LoginUser对象封装成UserDetails对象返回，用于封装用户信息
        return new LoginUser(user);
    }
}
