package com.liu.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liu.springboot.pojo.User;
import com.liu.springboot.pojo.dto.UserLoginDto;
import com.liu.springboot.pojo.dto.UserPasswordDto;
import com.liu.springboot.pojo.dto.UserQueryDto;
import com.liu.springboot.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
* @author 86183
* @description 针对表【sys_user】的数据库操作Service
* @createDate 2023-06-12 12:05:16
*/
public interface UserService extends IService<User> {

    /**
     * 新增和更新
     * @param user 参数
     * @return {@link Integer}
     */
    Result<?> saveAndUpd(User user);

    /**
     * 分页
     * @param userQuery 参数
     * @return {@link Page}<{@link User}>
     */
    Result<IPage<User>> findPage(UserQueryDto userQuery);

    /**
     * 一对多查询，一个老师对应多个课程
     * @param userQueryDto 条件
     * @return {@link IPage}<{@link User}>
     */
    IPage<User> getCourseByRole(UserQueryDto userQueryDto);

    /**
     * 登录接口
     * @param userLoginDto 参数
     * @return {@link User}
     */
    Result<?> login(UserLoginDto userLoginDto);

    /**
     * 导出
     * @param userQuery 参数
     * @return {@link Result}<{@link ?}>
     */
    Result<?> export(UserQueryDto userQuery);

    /**
     * 导入
     * @param file 文件名
     * @return boolean
     */
    Result<?> imp(MultipartFile file);

    /**
     * 注册
     * @param userLoginDto 参数
     * @return boolean
     */
    Result<?> saveRegister(UserLoginDto userLoginDto);

    /**
     * 查询
     * @param username 参数
     * @return {@link Result}<{@link ?}>
     */
    Result<?> findOne(String username);


    /**
     * 修改密码
     * @param passwordDto 前端参数
     * @return boolean
     */
    boolean updatePassword(UserPasswordDto passwordDto);
}
