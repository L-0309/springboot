package com.liu.springboot.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-07-03  14:52
 * @Description:
 * @Author: LiuHaoYu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicUtil {
    /**
     * 歌手名
     */
    private String singName;
    /**
     * 歌名
     */
    private String songName;
    /**
     * 歌曲时长
     */
    private String songLength;
}
