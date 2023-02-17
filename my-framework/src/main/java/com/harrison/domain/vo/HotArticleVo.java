package com.harrison.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Harrison
 * @date: 2023/2/13 22:17
 * @Description: TODO
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotArticleVo {
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 访问量
     */
    private Long viewCount;
}
