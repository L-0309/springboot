package com.liu.springboot.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: springboot
 * @CreateTime: 2023-07-17  13:56
 * @Description:
 * @Author: LiuHaoYu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilesPictureDto {
    private String src;
    private String thumbnail;
    private Integer w;
    private Integer h;
    private String title;
    private String type;
}
