package com.liu.springboot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @author 86183
 * @TableName sys_dict
 */
@TableName(value ="sys_dict")
@Data
public class Dict implements Serializable {
    /**
     * 名称
     */
    private String name;

    /**
     * 内容
     */
    private String value;

    /**
     * 类型
     */
    private String type;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}