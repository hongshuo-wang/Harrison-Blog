package com.harrison.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Harrison
 * @date: 2023/2/14 17:48
 * @Description: TODO
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVo {
    private Long id;
    /**
     * 分类名
     */
    private String name;
}
