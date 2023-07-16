package com.liu.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.springboot.pojo.User;
import com.liu.springboot.pojo.dto.UserPasswordDto;
import com.liu.springboot.pojo.dto.UserQueryDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author 86183
 * @description 针对表【sys_user】的数据库操作Mapper
 * @createDate 2023-06-12 12:05:16
 * @Entity com.liu.springboot.pojo.User
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 查询
     *
     * @param page         分页
     * @param userQueryDto 条件
     * @return {@link IPage}<{@link User}>
     */
    IPage<User> findAllUser(Page<User> page, @Param("userDto") UserQueryDto userQueryDto);

    /**
     * 一对多查询，一个老师对应多个课程
     *
     * @param page         分页
     * @param userQueryDto 条件
     * @return {@link IPage}<{@link User}>
     */
    IPage<User> getCourseByRole(Page<User> page, @Param("userDto") UserQueryDto userQueryDto);

    /**
     * 修改密码
     *
     * @param passwordDto 前端参数
     * @return boolean
     */
    @Update("update liu.sys_user set password = #{passwordDto.newPassword} " +
            "where username = #{passwordDto.username} and password = #{passwordDto.password};")
    boolean updatePassword(@Param("passwordDto") UserPasswordDto passwordDto);
}




