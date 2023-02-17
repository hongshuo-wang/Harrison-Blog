package com.harrison.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: Harrison
 * @date: 2023/2/14 23:47
 * @Description: 分页vo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVo<T> {
    private List<T> rows;
    private Long total;

}
