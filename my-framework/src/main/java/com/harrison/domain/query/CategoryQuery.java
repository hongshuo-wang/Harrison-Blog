package com.harrison.domain.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.ehcache.shadow.org.terracotta.offheapstore.paging.Page;

/**
 * @author: Harrison
 * @date: 2023/2/14 21:50
 * @Description: TODO
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryQuery extends PageQuery {
    /**
     * 分类id
     */
    Long categoryId;
}
