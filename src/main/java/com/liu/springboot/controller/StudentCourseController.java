package com.liu.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liu.springboot.pojo.StudentCourse;
import com.liu.springboot.service.StudentCourseService;
import com.liu.springboot.utils.Constants;
import com.liu.springboot.utils.Result;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-07-08  21:19
 * @Description:
 * @Author: LiuHaoYu
 */
@RestController
@RequestMapping("/student")
public class StudentCourseController {
    @Resource
    private StudentCourseService studentCourseService;

    @PostMapping("/course/{courseId}/{studentId}")
    public Result<?> saveStudentCourse(@PathVariable Integer courseId, @PathVariable Integer studentId) {
        QueryWrapper<StudentCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId).eq("student_id", studentId);
        StudentCourse one = studentCourseService.getOne(queryWrapper);
        if (one != null) {
            return Result.error(Constants.CODE_0,"此课程已选择");
        }
        try {
            queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("course_id", courseId).eq("student_id", studentId);
            studentCourseService.remove(queryWrapper);
            StudentCourse studentCourse = new StudentCourse();
            studentCourse.setCourseId(courseId);
            studentCourse.setStudentId(studentId);
            studentCourseService.save(studentCourse);
            return Result.success();
        } catch (Exception e) {
            return Result.error();
        }
    }
}
