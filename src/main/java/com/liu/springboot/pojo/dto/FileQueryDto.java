package com.liu.springboot.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-06-29  11:38
 * @Description:
 * @Author: LiuHaoYu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileQueryDto {
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
    private String name;

    /**
     * 文件分类id
     */
    private Integer classifierId;

    /**
     * 歌名
     */
    private String songName;
}
