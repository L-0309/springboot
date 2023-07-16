package com.liu.springboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * @author 86183
 * @TableName sys_article
 */
@TableName(value ="sys_article")
@Data
public class Article implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String name;

    /**
     * 内容
     */
    private String content;

    /**
     * 发布人
     */
    private Integer userId;

    /**
     * 发布时间
     */
    @JsonFormat(timezone = "GMT+8" , pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    /**
     * 详情页面所用到的参数
     */
    @TableField(exist = false)
    private String userNickname;

    /**
     * 一对一处理
     */
    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}