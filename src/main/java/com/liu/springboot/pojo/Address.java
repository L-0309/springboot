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
 * @TableName sys_address
 */
@TableName(value ="sys_address")
@Data
public class Address implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 地址
     */
    private String address;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}