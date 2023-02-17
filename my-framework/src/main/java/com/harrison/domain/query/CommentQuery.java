package com.harrison.domain.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author: Harrison
 * @date: 2023/2/17 17:04
 * @Description: TODO
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentQuery extends PageQuery{
    /**
     * 评论所在的文章id
     */
    Long articleId;
}
