package com.liu.springboot.pojo.dto;

import lombok.Data;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-07-15  10:29
 * @Description:
 * @Author: LiuHaoYu
 */
@Data
public class UserPasswordDto {
    private String username;
    private String phone;
    private String password;
    private String newPassword;
}
