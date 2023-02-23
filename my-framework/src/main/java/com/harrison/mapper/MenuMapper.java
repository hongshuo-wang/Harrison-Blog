package com.harrison.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.harrison.domain.entity.Menu;

import java.util.List;

/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author Harrison
 * @date 2023-02-22 01:40:39
 * @Description:
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long userId);

    List<Menu> selectAllRouterMenu();

    List<Menu> selectRouterMenuTreeByUserId(Long userId);
}

