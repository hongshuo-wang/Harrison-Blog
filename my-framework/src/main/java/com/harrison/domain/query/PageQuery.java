package com.harrison.domain.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Harrison
 * @date: 2023/2/14 21:48
 * @Description: 接受前端分页入参
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageQuery {
    /**
     * 当前查询第几页
     */
    private Integer pageNum;
    /**
     * 当前查询的这一页有几条记录
     */
    private Integer pageSize;

}
