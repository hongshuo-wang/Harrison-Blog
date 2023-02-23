package com.harrison.domain.vo;

import com.harrison.domain.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: Harrison
 * @date: 2023/2/23 17:27
 * @Description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouterVo {
    private List<Menu> menus;
}
