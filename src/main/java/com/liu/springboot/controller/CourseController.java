package com.liu.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.springboot.exception.ServiceException;
import com.liu.springboot.mapper.StudentCourseMapper;
import com.liu.springboot.pojo.Course;
import com.liu.springboot.pojo.User;
import com.liu.springboot.pojo.dto.CourseQueryDto;
import com.liu.springboot.service.CourseService;
import com.liu.springboot.service.UserService;
import com.liu.springboot.utils.Constants;
import com.liu.springboot.utils.Result;
import com.liu.springboot.utils.VerifyUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-07-07  15:52
 * @Description:
 * @Author: LiuHaoYu
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
    private CourseService courseService;

    @Resource
    private UserService userService;

    @Resource
    private StudentCourseMapper studentCourseMapper;

    @GetMapping("/find")
    public Result<?> findPage(CourseQueryDto courseQueryDto) {
        return Result.success(courseService.findPage(courseQueryDto));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        if (!VerifyUtil.verifyNum(id)) {
            return Result.error(Constants.CODE_400, "未收到参数");
        }
        return courseService.removeById(id) ? Result.success() : Result.error(Constants.CODE_0, "删除失败");
    }

    @GetMapping("/teacherList")
    public Result<?> teacherList(@RequestParam("id") Integer id) {
        if (!VerifyUtil.verifyNum(id)) {
            return Result.error();
        }
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id", id);
        List<Course> list = courseService.list(queryWrapper);
        return Result.success(list);
    }

    @GetMapping("/studentList")
    public Result<?> studentList(@RequestParam("id") Integer id) {
        List<Integer> courseId = studentCourseMapper.listAllByCourseIdIntegers(id);
        String course = courseId.toString().replace("[","").replace("]","");
        if (courseId.size() > 0) {
            List<Course> courses = courseService.findCourseStudent(course);
            return Result.success(courses);
        }else {
            return Result.error(Constants.CODE_0,"还没有选择任何课程");
        }
    }

    @GetMapping("/studentsOfCourse")
    public Result<?> studentsOfCourse(Integer id) {
        return Result.success(courseService.studentsOfCourse(id));
    }


    @PostMapping("/save")
    public Result<?> save(@RequestBody Course course) {
        if (!VerifyUtil.verifyNum(course.getId())) {
            course.setState(false);
        }
        courseService.saveOrUpdate(course);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result<?> delete(@RequestBody List<Integer> ids) {
        if (ids.size() == 0) {
            return Result.error(Constants.CODE_400, "未收到参数");
        }
        List<Course> courses = courseService.listByIds(ids);
        if (courses.size() > 0) {
            return courseService.removeBatchByIds(courses) ? Result.success() : Result.error(Constants.CODE_0, "删除失败");
        } else {
            throw new ServiceException(Constants.CODE_0, "未找到信息");
        }
    }

    @PostMapping("/update")
    public Result<?> update(@RequestBody Course course) {
        try {
            courseService.updateById(course);
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500, "禁用失败");
        }
    }


    /**
     * 批量启用接口
     *
     * @param enables 启用的id
     * @return {@link Result}<{@link ?}>
     */
    @PostMapping("/enable")
    public Result<?> enable(@RequestBody List<Integer> enables) {
        if (enables.size() > 0) {
            List<Course> courses = courseService.listByIds(enables);
            courses.forEach(file -> file.setState(true));
            return courseService.updateBatchById(courses) ? Result.success() : Result.error();
        } else {
            return Result.error(Constants.CODE_0, "你还没有选择");
        }
    }

    /**
     * 批量禁用接口
     *
     * @param disables 禁用的id
     * @return {@link Result}<{@link ?}>
     */
    @PostMapping("/disable")
    public Result<?> disable(@RequestBody List<Integer> disables) {
        if (disables.size() > 0) {
            List<Course> courses = courseService.listByIds(disables);
            courses.forEach(file -> file.setState(false));
            return courseService.updateBatchById(courses) ? Result.success() : Result.error();
        } else {
            return Result.error(Constants.CODE_0, "你还没有选择");
        }
    }
}
