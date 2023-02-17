package com.harrison.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: Harrison
 * @date: 2023/2/14 23:45
 * @Description: 文章列表vo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListVo {
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 文章摘要
     */
    private String summary;
    /**
     * 所属分类名称
     */
    private String categoryName;
    /**
     * 缩略图
     */
    private String thumbnail;
    /**
     * 访问量
     */
    private Long viewCount;
    /**
     * 创建时间
     */
    private Date createTime;
}
