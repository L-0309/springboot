package com.liu.springboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author 86183
 * @TableName course
 */
@TableName(value ="sys_course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 学分
     */
    private Integer score;

    /**
     * 上课时间
     */
    private String times;

    /**
     * 是否开课
     */
    private Boolean state;

    /**
     * 授课老师id
     */
    private Integer teacherId;

    @TableField(exist = false)
    private String teacher;

    @TableField(exist = false)
    private List<User> students;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}