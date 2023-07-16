package com.liu.springboot.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-06-12  15:39
 * @Description: 接收前端查询参数
 * @Author: LiuHaoYu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQueryDto implements Serializable {
    /**
     * 当前页
     */
    private Integer current;
    /**
     *页面大小
     */
    private Integer pageSize;
    /**
     *用户名
     */
    private String username;
    /**
     *邮箱
     */
    private String email;
    /**
     *地址ip
     */
    private Integer addressId;
    /**
     * 角色
     */
    private String role;

    /**
     * 老师id
     */
    private Integer id;
}
