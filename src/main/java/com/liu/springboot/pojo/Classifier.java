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
 * @TableName sys_classifier
 */
@TableName(value ="sys_classifier")
@Data
public class Classifier implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 文件类型
     */
    private String name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}