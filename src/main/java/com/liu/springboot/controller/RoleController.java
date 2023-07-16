package com.liu.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.springboot.pojo.Role;
import com.liu.springboot.pojo.dto.RoleQueryDto;
import com.liu.springboot.service.RoleService;
import com.liu.springboot.utils.Constants;
import com.liu.springboot.utils.Result;
import com.liu.springboot.utils.VerifyUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-07-01  20:54
 * @Description: 角色管理
 * @Author: LiuHaoYu
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @GetMapping("/find")
    public Result<?> find(RoleQueryDto roleQueryDto) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (VerifyUtil.verifyStr(roleQueryDto.getName())){
            queryWrapper.like("name",roleQueryDto.getName());
        }
        return Result.success(roleService.page(new Page<>(roleQueryDto.getCurrent(),roleQueryDto.getPageSize()), queryWrapper));
    }

    @GetMapping
    public Result<?> find() {
        return Result.success(roleService.list());
    }

    @PostMapping("/save")
    public Result<?> save(@RequestBody Role role) {
        return roleService.saveOrUpdate(role) ? Result.success() : Result.error();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        if (!VerifyUtil.verifyNum(id)){
            return Result.error(Constants.CODE_400,"未收到参数");
        }
        return roleService.removeById(id) ? Result.success() : Result.error(Constants.CODE_0,"删除失败");
    }

    @PostMapping("/del/batch")
    public Result<?> delete(@RequestBody List<Integer> ids) {
        if (ids.size() == 0){
            return Result.error(Constants.CODE_400,"未收到参数");
        }
        return roleService.removeBatchByIds(ids) ? Result.success() : Result.error(Constants.CODE_0,"删除失败");
    }

    /**
     * 绑定角色和菜单的关系
     * @param roleId 角色id
     * @param menuIds 菜单id数组
     * @return {@link Result}<{@link ?}>
     */
    @PostMapping("/roleMenu/{roleId}")
    public Result<?> roleMenu(@PathVariable Integer roleId, @RequestBody List<Integer> menuIds) {
        return roleService.setRoleMenu(roleId, menuIds);
    }

    @GetMapping("/roleMenu/{roleId}")
    public Result<?> roleMenu(@PathVariable Integer roleId) {
        return roleService.getRoleMenu(roleId);
    }
}
