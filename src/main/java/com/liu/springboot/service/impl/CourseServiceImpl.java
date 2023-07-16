package com.liu.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.springboot.pojo.Course;
import com.liu.springboot.pojo.dto.CourseQueryDto;
import com.liu.springboot.service.CourseService;
import com.liu.springboot.mapper.CourseMapper;
import com.liu.springboot.utils.VerifyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 86183
* @description 针对表【course】的数据库操作Service实现
* @createDate 2023-07-07 15:14:39
*/
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
    implements CourseService{
    @Resource
    private CourseMapper courseMapper;

    @Override
    public IPage<Course> findPage(CourseQueryDto courseQueryDto) {
        if (VerifyUtil.verifyStr(courseQueryDto.getName())) {
            courseQueryDto.setName("%" + courseQueryDto.getName() +"%");
        }
        return courseMapper.findPage(new Page<>(courseQueryDto.getCurrent(), courseQueryDto.getPageSize()), courseQueryDto);
    }

    @Override
    public List<Course> findCourseStudent(String course) {
        return courseMapper.findCourseStudent(course);
    }

    @Override
    public List<Course> studentsOfCourse(Integer id) {
        return courseMapper.studentsOfCourse(id);
    }
}




