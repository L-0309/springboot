package com.liu.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liu.springboot.pojo.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* @author 86183
* @description 针对表【sys_role】的数据库操作Mapper
* @createDate 2023-07-01 20:46:58
* @Entity com.liu.springboot.pojo.Role
*/
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 查出角色id
     * @param role 唯一标识
     * @return {@link Integer}
     */
    @Select("select id from liu.sys_role where sole_key = #{role}")
    Integer selectByRole(@Param("role") String role);
}




