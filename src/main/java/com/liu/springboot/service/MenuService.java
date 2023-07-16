package com.liu.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liu.springboot.pojo.Menu;

import java.util.List;

/**
* @author 86183
* @description 针对表【sys_menu】的数据库操作Service
* @createDate 2023-07-01 21:40:13
*/
public interface MenuService extends IService<Menu> {

    /**
     * 查询所有菜单
     * @param name 参数
     * @return {@link List}<{@link Menu}>
     */
    List<Menu> findMenus(String name);

    /**
     * 获取当前角色菜单列表
     * @param menuIdList 当前角色的菜单id
     * @return {@link List}<{@link Menu}>
     */
    List<Menu> getCurrentRoleMenu(List<Integer> menuIdList);
}
