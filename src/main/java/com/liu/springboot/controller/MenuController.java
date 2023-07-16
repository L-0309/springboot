package com.liu.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.springboot.pojo.Dict;
import com.liu.springboot.pojo.Menu;
import com.liu.springboot.pojo.dto.MenuQueryDto;
import com.liu.springboot.service.DictService;
import com.liu.springboot.service.MenuService;
import com.liu.springboot.utils.Constants;
import com.liu.springboot.utils.Result;
import com.liu.springboot.utils.VerifyUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-07-01  21:40
 * @Description:
 * @Author: LiuHaoYu
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private MenuService menuService;

    @Resource
    private DictService dictService;

    @GetMapping
    public Result<?> findAll(@RequestParam(value = "name",defaultValue = "") String name) {
        return Result.success(menuService.findMenus(name));
    }

    @GetMapping("/find")
    public Result<?> find(MenuQueryDto menuQueryDto) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        if (VerifyUtil.verifyStr(menuQueryDto.getName())){
            queryWrapper.like("name",menuQueryDto.getName());
        }
        return Result.success(menuService.page(new Page<>(menuQueryDto.getCurrent(),menuQueryDto.getPageSize()), queryWrapper));
    }

    @GetMapping("/icons")
    public Result<?> getIcons() {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type",Constants.DICT_TYPE_ICON);
        return Result.success(dictService.list());
    }

    @PostMapping("/save")
    public Result<?> save(@RequestBody Menu menu) {
        return menuService.saveOrUpdate(menu) ? Result.success() : Result.error();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        if (!VerifyUtil.verifyNum(id)){
            return Result.error(Constants.CODE_400,"未收到参数");
        }
        return menuService.removeById(id) ? Result.success() : Result.error(Constants.CODE_0,"删除失败");
    }

    @PostMapping("/del/batch")
    public Result<?> delete(@RequestBody List<Integer> ids) {
        if (ids.size() == 0){
            return Result.error(Constants.CODE_400,"未收到参数");
        }
        return menuService.removeBatchByIds(ids) ? Result.success() : Result.error(Constants.CODE_0,"删除失败");
    }
}
