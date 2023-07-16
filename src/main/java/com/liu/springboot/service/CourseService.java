package com.liu.springboot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.springboot.pojo.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liu.springboot.pojo.dto.CourseQueryDto;

import java.util.List;

/**
* @author 86183
* @description 针对表【course】的数据库操作Service
* @createDate 2023-07-07 15:14:39
*/
public interface CourseService extends IService<Course> {

    /**
     * 关联查询
     * @param courseQueryDto 参数
     * @return {@link IPage}<{@link Course}>
     */
    IPage<Course> findPage(CourseQueryDto courseQueryDto);

    /**
     * 查询学生所选课程
     * @param course 条件
     * @return {@link List}<{@link Course}>
     */
    List<Course> findCourseStudent(String course);

    /**
     * 查出课程所选的学生
     * @param id 课程id
     * @return {@link List}<{@link Course}>
     */
    List<Course> studentsOfCourse(Integer id);
}
