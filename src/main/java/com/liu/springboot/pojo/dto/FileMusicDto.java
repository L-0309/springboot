package com.liu.springboot.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-07-11  18:34
 * @Description: 前端音乐播放需要如下字段，所以在后台封装好一个此对象
 * @Author: LiuHaoYu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileMusicDto {
    private String title;
    private String artist;
    private String url;
    private String type;
}
