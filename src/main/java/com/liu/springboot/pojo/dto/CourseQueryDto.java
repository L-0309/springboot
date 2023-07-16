package com.liu.springboot.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-07-07  16:43
 * @Description:
 * @Author: LiuHaoYu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseQueryDto {
    /**
     * 当前页
     */
    private Integer current;
    /**
     *页面大小
     */
    private Integer pageSize;

    /**
     * 课程名
     */
    private String name;

    /**
     * 老师id
     */
    private Integer teacherId;
}
