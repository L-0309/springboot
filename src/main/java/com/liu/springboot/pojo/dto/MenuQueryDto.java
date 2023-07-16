package com.liu.springboot.pojo.dto;

import lombok.Data;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-07-01  21:06
 * @Description:
 * @Author: LiuHaoYu
 */
@Data
public class MenuQueryDto {
    /**
     * 当前页
     */
    private Integer current;
    /**
     *页面大小
     */
    private Integer pageSize;

    /**
     * 名称
     */
    private String name;
}
