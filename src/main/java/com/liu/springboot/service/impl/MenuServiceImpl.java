package com.liu.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.springboot.mapper.MenuMapper;
import com.liu.springboot.pojo.Menu;
import com.liu.springboot.service.MenuService;
import com.liu.springboot.utils.VerifyUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author 86183
* @description 针对表【sys_menu】的数据库操作Service实现
* @createDate 2023-07-01 21:40:13
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

    @Override
    public List<Menu> findMenus(String name) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        if (VerifyUtil.verifyStr(name)){
            queryWrapper.like("name",name);
        }
        return menuList(queryWrapper);
    }

    @Override
    public List<Menu> getCurrentRoleMenu(List<Integer> list) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",list);
        return menuList(queryWrapper);
    }

    /**
     * 封装查询多级菜单
     * @param queryWrapper 查询条件
     * @return {@link List}<{@link Menu}>
     */
    public List<Menu> menuList(QueryWrapper<Menu> queryWrapper) {
        //查询所有数据
        List<Menu> list = list(queryWrapper);
        //找出pid为null的一级菜单
        List<Menu> parentNode = list.stream().filter(menu -> menu.getPid() == null).collect(Collectors.toList());
        //找出一级菜单的子菜单
        for (Menu menu : parentNode) {
            //找二级菜单
            menu.setChildren(list.stream().filter(m -> menu.getId().equals(m.getPid())).collect(Collectors.toList()));
            for (Menu child : menu.getChildren()) {
                child.setChildren(list.stream().filter(menu1 -> child.getId().equals(menu1.getPid())).collect(Collectors.toList()));
                for (Menu childChild : child.getChildren()) {
                    childChild.setChildren(list.stream().filter(menu1 -> childChild.getId().equals(menu1.getPid())).collect(Collectors.toList()));
                }
            }
        }
        return parentNode;
    }
}




