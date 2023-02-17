package com.harrison.service;

import com.harrison.domain.entity.User;
import com.harrison.domain.result.ResponseResult;
import com.harrison.domain.vo.BlogUserLoginVo;

/**
 * @author: Harrison
 * @date: 2023/2/17 1:34
 * @Description: TODO
 */
public interface BlogLoginService {
    ResponseResult<BlogUserLoginVo> login(User user);

    ResponseResult logout();
}
