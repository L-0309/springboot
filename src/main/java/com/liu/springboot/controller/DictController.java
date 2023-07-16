package com.liu.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.springboot.exception.ServiceException;
import com.liu.springboot.pojo.Dict;
import com.liu.springboot.pojo.dto.DictQueryDto;
import com.liu.springboot.service.DictService;
import com.liu.springboot.utils.Constants;
import com.liu.springboot.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-07-07  14:17
 * @Description: 图标
 * @Author: LiuHaoYu
 */
@RestController
@RequestMapping("/dict")
public class DictController {
    @Resource
    private DictService dictService;

    @GetMapping("/icon")
    public Result<?> getAllIcons(DictQueryDto dictQueryDto) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", Constants.DICT_TYPE_ICON);
        return Result.success(dictService.page(new Page<>(dictQueryDto.getCurrent(),dictQueryDto.getPageSize()), queryWrapper));
    }

    @GetMapping("/icon/{name}")
    public Result<?> getIconByName(@PathVariable String name) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        Dict dict;
        try {
            dict = dictService.getOne(queryWrapper);
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        if (dict != null) {
            return Result.error(Constants.CODE_0, "图标已存在");
        }else {
            return Result.success();
        }
    }

    @PostMapping("/save")
    public Result<?> save(@RequestBody Dict dict) {
        dict.setType(Constants.DICT_TYPE_ICON);
        if (dictService.save(dict)) {
            return Result.success();
        }else {
            return Result.error();
        }
    }
}
