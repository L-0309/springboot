package com.liu.springboot.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.liu.springboot.pojo.Menu;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-06-13  16:00
 * @Description:
 * @Author: LiuHaoYu
 */

@Data
public class UserLoginDto implements Serializable {
    /**
     * 当前用户id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 确认密码
     */
    private String confirmPassword;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * token密钥
     */
    private String token;
    /**
     * 角色
     */
    private String role;
    /**
     * 当前用户所有菜单项
     */
    private List<Menu> menus;

}
