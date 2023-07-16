package com.liu.springboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author 86183
 * @TableName sys_user
 */
@Builder
@TableName(value ="sys_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 地址
     */
    private Integer addressId;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8" , pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     *头像地址
     */
    @TableField(value = "avatar_url")
    private String avatar;

    /**
     * 角色
     */
    private String role;

    /**
     * 地址，一对一
     */
    @TableField(exist = false)
    private Address address;

    /**
     * 角色，一对一
     */
    @TableField(exist = false)
    private Role soleKey;

    /**
     * 一对多，老师对课程
     */
    @TableField(exist = false)
    private List<Course> courses;

    /**
     * 多对多，学生对课程
     */
    @TableField(exist = false)
    private List<Course> stuCourses;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}