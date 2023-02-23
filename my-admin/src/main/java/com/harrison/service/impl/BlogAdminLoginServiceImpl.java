package com.harrison.service.impl;

import com.harrison.domain.entity.LoginUser;
import com.harrison.domain.entity.User;
import com.harrison.domain.result.ResponseResult;
import com.harrison.domain.vo.BlogUserLoginVo;
import com.harrison.domain.vo.UserInfoVo;
import com.harrison.service.BlogAdminLoginService;
import com.harrison.service.BlogLoginService;
import com.harrison.utils.BeanCopyUtils;
import com.harrison.utils.JwtUtil;
import com.harrison.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author: Harrison
 * @date: 2023/2/17 1:34
 * @Description: 自己实现登录接口
 */
@Service
public class BlogAdminLoginServiceImpl implements BlogAdminLoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);
        // 判断是否认证通过
        if(Objects.isNull(authenticate)) {
            throw new RuntimeException("密码错误,请检查");
        }
        // 到这里说明用户存在
        // 根据userId生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        //使用userid生成token
        String jwt = JwtUtil.createJWT(userId);
        //用户信息存入redis，以后直接在redis里面用userId就能获取用户信息了
        redisCache.setCacheObject("adminLogin:"+userId, loginUser);
        // 将查询到的User信息拷贝给userInfoVo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        //把token返回给前端
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return ResponseResult.okResult(map);
    }

    @Override
    public ResponseResult logout() {
        //获取token 解析获取userid
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //获取userid
        Long userId = loginUser.getUser().getId();
        //删除redis中的用户信息
        redisCache.deleteObject("adminLogin:"+userId);
        return ResponseResult.okResult();
    }


}
