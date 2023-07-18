package com.liu.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.liu.springboot.exception.ServiceException;
import com.liu.springboot.pojo.Course;
import com.liu.springboot.pojo.Menu;
import com.liu.springboot.pojo.User;
import com.liu.springboot.pojo.dto.UserLoginDto;
import com.liu.springboot.pojo.dto.UserPasswordDto;
import com.liu.springboot.pojo.dto.UserQueryDto;
import com.liu.springboot.service.UserService;
import com.liu.springboot.utils.Constants;
import com.liu.springboot.utils.Result;
import com.liu.springboot.utils.TokenUtils;
import com.liu.springboot.utils.VerifyUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-06-12  12:11
 * @Description: userController接口
 * @Author: LiuHaoYu
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result<?> login(@RequestBody UserLoginDto userLoginDto) {
        return userService.login(userLoginDto);
    }

    @GetMapping("/limit/{role}")
    public Result<?> findAllByRole(@PathVariable String role) {
        List<Menu> menuList = userService.getAllMenuByRole(role);
        return Result.success(menuList);
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody UserLoginDto userLoginDto){
        return userService.saveRegister(userLoginDto);
    }

    @GetMapping("/nickname")
    public Result<?> findAllNickname() {
        return Result.success(userService.list());
    }

    @PostMapping("/password")
    public void updatePassword(@RequestBody UserPasswordDto passwordDto) {
        if (!userService.updatePassword(passwordDto)) {
            throw new ServiceException(Constants.CODE_500,"密码错误");
        }
    }

    @GetMapping("/find")
    public Result<IPage<User>> findAll(UserQueryDto userQueryDto) {
        TokenUtils.getCurrentUser();
        return userService.findPage(userQueryDto);
    }

    @GetMapping("/course")
    public Result<?> getCourseByRole(UserQueryDto userQueryDto) {
        IPage<User> courseByRole = userService.getCourseByRole(userQueryDto);
        return Result.success(courseByRole);
    }

    @GetMapping("/role/{role}")
    public Result<?> findUsersByRole(@PathVariable String role) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role",role);
        return Result.success(userService.list(queryWrapper));
    }


    @PostMapping("/save")
    public Result<?> save(@RequestBody User user) {
        return userService.saveAndUpd(user);
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        if (!VerifyUtil.verifyNum(id)){
            return Result.error(Constants.CODE_400,"未收到参数");
        }
        return userService.removeById(id) ? Result.success() : Result.error(Constants.CODE_0,"删除失败");
    }

    @PostMapping("/del/batch")
    public Result<?> delete(@RequestBody List<Integer> ids) {
        if (ids.size() == 0){
            return Result.error(Constants.CODE_400,"未收到参数");
        }
        return userService.removeBatchByIds(ids) ? Result.success() : Result.error(Constants.CODE_0,"删除失败");
    }

    @GetMapping("/username/{username}")
    public Result<?> findOne(@PathVariable String username){
        return userService.findOne(username);
    }

    /**
     * 导出
     * @return {@link Result}<{@link ?}>
     */
    @GetMapping("/export")
    public Result<?> export(UserQueryDto queryDto) {
        return userService.export(queryDto);
    }

    /**
     * 打开导出文件
     * @param exportPath 绝对路径
     * @return {@link Result}<{@link ?}>
     */
    @GetMapping("/exportPath")
    public Result<?> exportPath(String exportPath) throws IOException {
        FileReader fileReader = new FileReader(exportPath);
        int bytes = 0;
        bytes = fileReader.read();
        StringBuilder content = new StringBuilder();
        while (bytes != (-1)){
            content.append((char) bytes);
            bytes = fileReader.read();
        }
        fileReader.close();
        return Result.success();
    }
    /**
     * 导入
     * @param file file
     */
    @PostMapping("/import")
    public Result<?> imp(MultipartFile file) {
        return userService.imp(file);
    }
}
