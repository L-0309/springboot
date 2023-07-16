package com.liu.springboot.mapper;

import com.liu.springboot.pojo.StudentCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author 86183
* @description 针对表【sys_student_course】的数据库操作Mapper
* @createDate 2023-07-08 21:13:21
* @Entity com.liu.springboot.pojo.StudentCourse
*/
public interface StudentCourseMapper extends BaseMapper<StudentCourse> {

    /**
     * 查出当前学生的课程id集合
     * @param studentId 学生id
     * @return {@link List}<{@link Integer}>
     */
    @Select("select course_id from liu.sys_student_course where student_id = #{student_id}")
    List<Integer> listAllByCourseIdIntegers(@Param("student_id") Integer studentId);
}




