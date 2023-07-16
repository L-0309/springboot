package com.liu.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liu.springboot.pojo.Role;
import com.liu.springboot.utils.Result;

import java.util.List;

/**
* @author 86183
* @description 针对表【sys_role】的数据库操作Service
* @createDate 2023-07-01 20:46:58
*/
public interface RoleService extends IService<Role> {

    /**
     * 设置权限
     * @param roleId  角色id
     * @param menuIds 菜单id数组
     * @return {@link Result}<{@link ?}>
     */
    Result<?> setRoleMenu(Integer roleId, List<Integer> menuIds);

    /**
     * 获取权限
     * @param roleId 角色id
     * @return {@link Result}<{@link ?}>
     */
    Result<?> getRoleMenu(Integer roleId);
}
