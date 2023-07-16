package com.liu.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.springboot.mapper.RoleMapper;
import com.liu.springboot.mapper.RoleMenuMapper;
import com.liu.springboot.pojo.Role;
import com.liu.springboot.pojo.RoleMenu;
import com.liu.springboot.service.RoleService;
import com.liu.springboot.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 86183
* @description 针对表【sys_role】的数据库操作Service实现
* @createDate 2023-07-01 20:46:58
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{
    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Transactional
    @Override
    public Result<?> setRoleMenu(Integer roleId, List<Integer> menuIds) {
        try {
            //先删除当前角色id的所有绑定关系
            QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("role_id", roleId);
            roleMenuMapper.delete(queryWrapper);
            //在把前端传过来的菜单id数组绑定到这个角色id上去
            for (Integer menuId : menuIds) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menuId);
                roleMenuMapper.insert(roleMenu);
            }
            return Result.success();
        } catch (Exception e) {
            //回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.error();
        }
    }

    @Override
    public Result<?> getRoleMenu(Integer roleId) {
        return Result.success(roleMenuMapper.selectByRoleId(roleId));
    }
}




