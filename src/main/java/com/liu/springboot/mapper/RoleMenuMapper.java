package com.liu.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liu.springboot.pojo.RoleMenu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author 86183
* @description 针对表【sys_role_menu】的数据库操作Mapper
* @createDate 2023-07-02 12:56:24
* @Entity com.liu.springboot.pojo.RoleMenu
*/
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     * 返回角色id集合
     * @param roleId 角色id
     * @return {@link List}<{@link Integer}>
     */
    @Select("select menu_id from liu.sys_role_menu where role_id = #{roleId}")
    List<Integer> selectByRoleId(@Param("roleId") Integer roleId);
}




