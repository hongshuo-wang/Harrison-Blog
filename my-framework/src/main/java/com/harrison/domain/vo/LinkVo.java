package com.harrison.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Harrison
 * @date: 2023/2/16 20:29
 * @Description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkVo {
    private Long id;
    private String name;
    private String logo;
    private String description;
    /**
     * 网站地址
     */
    private String address;
}
