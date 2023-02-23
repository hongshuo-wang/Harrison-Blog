package com.harrison.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author: Harrison
 * @date: 2023/2/14 23:45
 * @Description: 文章列表vo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserInfoVo {
    private List<String> permissions;
    private List<String> roles;
    private UserInfoVo user;
}
