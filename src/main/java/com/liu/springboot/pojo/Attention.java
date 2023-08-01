package com.liu.springboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @author 86183
 * @TableName sys_attention
 */
@TableName(value ="sys_attention")
@Data
public class Attention implements Serializable {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 文章id
     */
    private Integer articleId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}