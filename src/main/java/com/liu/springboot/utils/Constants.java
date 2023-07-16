package com.liu.springboot.utils;

import com.liu.springboot.pojo.Files;
import com.liu.springboot.pojo.dto.FileMusicDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Liu
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-06-13  22:30
 * @Description: 常量接口
 * @Author: LiuHaoYu
 * @date 2023-07-01
 */
public interface Constants {
    /**
     * 成功
     */
    String CODE_200 = "200";
    /**
     * 系统错误
     */
    String CODE_500 = "500";
    /**
     * 权限不足
     */
    String CODE_401 = "401";

    /**
     * 参数错误
     */
    String CODE_400 = "400";

    /**
     * 未找到数据
     */
    String CODE_0 = "0";

    /**
     * 导出指定路径
     */
    //    部署服务器路径
//    String EXPORT_PATH = "/home/upload/export/";
    String EXPORT_PATH = "F:\\upload\\export\\";

    /**
     * 访问地址
     */
    //    部署服务器路径
//    String IP = "http://8.130.26.71:8111/images/";
    String IP = "http://localhost:8111/images/";

    /**
     * 文件上传路径
     */
    // 部署服务器时路径
//    String UPLOAD_PATH = "/home/upload/img/";
    String UPLOAD_PATH = "F:\\upload\\img\\";

    /**
     * 图标
     */
    String DICT_TYPE_ICON = "icon";

    /**
     * 文件缓存
     */
    String FILES_ITEMS_KEY = "FILES_ITEMS_KEY";

    /**
     * 音乐缓存
     */
    String FILES_MUSIC_KEY = "FILES_MUSIC_KEY";

    /**
     * 返回前端进行封装
     * @param list 查询出的所有文件
     * @return {@link List}<{@link FileMusicDto}>
     */
    default List<FileMusicDto> fileMusicDto(List<Files> list) {
        List<FileMusicDto> musicDtoList = new ArrayList<>();
        list.forEach(files -> {
            musicDtoList.add(new FileMusicDto(files.getSingName(),files.getSongName(),files.getUrl(),files.getType()));
        });
        return musicDtoList;
    }


    /**
     * 图片类型
     * @return {@link List}<{@link String}>
     */
    default List<String> pictureType(){
        List<String> list = new ArrayList<>();
        list.add("jpg");
        list.add("png");
        return list;
    }

    /**
     * 音乐类型
     * @return {@link List}<{@link String}>
     */
    default List<String> musicType(){
        List<String> list = new ArrayList<>();
        list.add("mp3");
        list.add("flac");
        return list;
    }

    /**
     * 视频类型
     * @return {@link List}<{@link String}>
     */
    default List<String> videoType(){
        List<String> list = new ArrayList<>();
        list.add("mp4");
        return list;
    }
}
