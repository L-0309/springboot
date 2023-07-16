package com.liu.springboot.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.springboot.pojo.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liu.springboot.pojo.dto.CourseQueryDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 86183
* @description 针对表【course】的数据库操作Mapper
* @createDate 2023-07-07 15:14:39
* @Entity com.liu.springboot.pojo.Course
*/
public interface CourseMapper extends BaseMapper<Course> {

    /**
     * 关联查询
     * @param coursePage 分页支持
     * @param courseQueryDto 参数
     * @return {@link Page}<{@link Course}>
     */
    IPage<Course> findPage(Page<Course> coursePage, @Param("courseDto") CourseQueryDto courseQueryDto);

    /**
     * 查询学生课程
     * @param course 课程id
     * @return {@link List}<{@link Course}>
     */
    List<Course> findCourseStudent(@Param("courseId") String course);


    /**
     * 查出课程所选的学生
     * @param id 课程id
     * @return {@link List}<{@link Course}>
     */
    List<Course> studentsOfCourse(@Param("id") Integer id);
}




