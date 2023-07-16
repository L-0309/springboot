package com.liu.springboot.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-07-07  14:20
 * @Description:
 * @Author: LiuHaoYu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictQueryDto {
    /**
     * 当前页
     */
    private Integer current;
    /**
     *页面大小
     */
    private Integer pageSize;
}
