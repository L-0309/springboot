package com.liu.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.springboot.pojo.StudentCourse;
import com.liu.springboot.service.StudentCourseService;
import com.liu.springboot.mapper.StudentCourseMapper;
import org.springframework.stereotype.Service;

/**
* @author 86183
* @description 针对表【sys_student_course】的数据库操作Service实现
* @createDate 2023-07-08 21:13:21
*/
@Service
public class StudentCourseServiceImpl extends ServiceImpl<StudentCourseMapper, StudentCourse>
    implements StudentCourseService{

}




