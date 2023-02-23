package com.harrison.filter;

import com.alibaba.fastjson2.JSON;
import com.harrison.domain.entity.LoginUser;
import com.harrison.domain.result.ResponseResult;
import com.harrison.enums.AppHttpCodeEnum;
import com.harrison.utils.JwtUtil;
import com.harrison.utils.RedisCache;
import com.harrison.utils.WebUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author: Harrison
 * @date: 2023/2/17 11:31
 * @Description: TODO
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private RedisCache redisCache;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头中的token
        String token = request.getHeader("token");
        // 没有token说明该接口可以直接放行，不需要登陆
        if(!Strings.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        // 解析获取userId
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            // token超时或token非法
            e.printStackTrace();
            // 告诉前端重新登录
            ResponseResult errorResult = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            // FastJson2管理不到非SpringMVC的地方，需要手动转json
            WebUtils.renderString(response, JSON.toJSONString(errorResult));
            return;
        }
        String userId = claims.getSubject();
        // 从redis中获取用户信息
        LoginUser loginUser = redisCache.getCacheObject("adminLogin:" + userId);
        //如果获取不到
        if(Objects.isNull(loginUser)){
            //说明登录过期  提示重新登录
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
            // 这里不能用throw或者return ResponseResult进行处理
            // 前者是因为统一异常处理只能处理controller层的，这里的filter还没到controller
            // 后者是因为FastJson是对SpringMVC进行的处理，这里的返回值也不是、不能是ResponseResult
//                throw new SystemException(AppHttpCodeEnum.NEED_LOGIN);
        }
        // 保存到SecurityContextHolder中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 放行
        filterChain.doFilter(request, response);
    }
}
