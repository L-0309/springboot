package com.liu.springboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * @author 86183
 * @TableName sys_comment
 */
@TableName(value ="sys_comment")
@Data
public class Comment implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论人id
     */
    private Integer userId;

    /**
     * 评论时间
     */
    @JsonFormat(timezone = "GMT+8" , pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    /**
     * 父id
     */
    private Integer pid;

    /**
     * 回复的谁
     */
    private String pname;

    /**
     * 最上级评论id
     */
    private Integer originId;

    /**
     * 关联文章的id
     */
    private Integer articleId;

    /**
     * 回复的内容
     */
    @TableField(exist = false)
    private List<Comment> children;

    @TableField(exist = false)
    private String nickname;

    @TableField(exist = false)
    private String avatarUrl;

    /**
     * 映射用来接收每个评论总数
     */
    @TableField(exist = false)
    private Integer count;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}