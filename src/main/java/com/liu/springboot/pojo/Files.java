package com.liu.springboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @author 86183
 * @TableName sys_file
 */
@TableName(value ="sys_file")
@Data
public class Files implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 文件大小
     */
    private Long size;

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

    /**
     * 下载链接
     */
    private String url;

    /**
     * 是否删除
     */
    private Boolean isDelete;

    /**
     * 是否禁用链接
     */
    private Boolean enable;

    /**
     * 文件的md5
     */
    private String md5;

    /**
     * 文件分类id
     */
    private Integer classifierId;

    @TableField(exist = false)
    private Classifier classifier;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}