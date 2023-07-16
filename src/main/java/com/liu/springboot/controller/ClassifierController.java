package com.liu.springboot.controller;

import com.liu.springboot.service.ClassifierService;
import com.liu.springboot.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-07-01  09:37
 * @Description:
 * @Author: LiuHaoYu
 */
@RestController
@RequestMapping("/classifier")
public class ClassifierController {
    @Resource
    private ClassifierService classifierService;

    @GetMapping
    public Result<?> find(){
        return Result.success(classifierService.list());
    }

}
