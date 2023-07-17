package com.liu.springboot.utils;

import com.liu.springboot.pojo.Files;
import com.liu.springboot.pojo.dto.FileMusicDto;
import com.liu.springboot.pojo.dto.FilesPictureDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-07-01  16:22
 * @Description:
 * @Author: LiuHaoYu
 */
public class ConstantsImpl implements Constants{

    @Override
    public List<FileMusicDto> fileMusicDto(List<Files> list) {
        return Constants.super.fileMusicDto(list);
    }

    @Override
    public List<FilesPictureDto> filesPictureDto(List<Files> list) {
        return Constants.super.filesPictureDto(list);
    }

    /**
     * 图片类型
     * @return {@link List}<{@link String}>
     */
    @Override
    public List<String> pictureType() {
        return Constants.super.pictureType();
    }

    /**
     * 视频类型
     * @return {@link List}<{@link String}>
     */
    @Override
    public List<String> videoType() {
        return Constants.super.videoType();
    }

    /**
     * 音乐类型
     * @return {@link List}<{@link String}>
     */
    @Override
    public List<String> musicType() {
        return Constants.super.musicType();
    }
}
