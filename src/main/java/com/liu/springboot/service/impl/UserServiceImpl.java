package com.liu.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.springboot.exception.ServiceException;
import com.liu.springboot.mapper.RoleMapper;
import com.liu.springboot.mapper.RoleMenuMapper;
import com.liu.springboot.mapper.UserMapper;
import com.liu.springboot.pojo.Menu;
import com.liu.springboot.pojo.User;
import com.liu.springboot.pojo.dto.UserLoginDto;
import com.liu.springboot.pojo.dto.UserPasswordDto;
import com.liu.springboot.pojo.dto.UserQueryDto;
import com.liu.springboot.service.MenuService;
import com.liu.springboot.service.UserService;
import com.liu.springboot.utils.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author 86183
 * @description 针对表【sys_user】的数据库操作Service实现
 * @createDate 2023-06-12 12:05:16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private MenuService menuService;

    /**
     * 修改和添加业务已更新
     *
     * @param user 参数
     * @return {@link Result}<{@link ?}>
     */
    @Override
    public Result<?> saveAndUpd(User user) {
        if (!VerifyUtil.verifyStr(user.getRole())) {
            user.setRole(RoleEnum.ROLE_VISIT.toString());
        }
        return saveOrUpdate(user) ? Result.success() : Result.error();
    }

    /**
     * 分页业务层已更新
     *
     * @param userQuery 参数
     * @return {@link Result}<{@link Page}<{@link User}>>
     */
    @Override
    public Result<IPage<User>> findPage(UserQueryDto userQuery) {
        if (VerifyUtil.verifyStr(userQuery.getUsername())){
            userQuery.setUsername("%" + userQuery.getUsername() + "%");
        }
        if (VerifyUtil.verifyStr(userQuery.getEmail())){
            userQuery.setEmail("%" + userQuery.getEmail() + "%");
        }
        if (!VerifyUtil.verifyNum(userQuery.getAddressId())){
            userQuery.setAddressId(0);
        }
        IPage<User> allUser = userMapper.findAllUser(new Page<>(userQuery.getCurrent(), userQuery.getPageSize()), userQuery);
        return Result.success(allUser);
    }

    @Override
    public IPage<User> getCourseByRole(UserQueryDto userQueryDto) {
        return userMapper.getCourseByRole(new Page<>(userQueryDto.getCurrent(), userQueryDto.getPageSize()), userQueryDto);
    }

    /**
     * 登录业务已更新
     * @param userLoginDto 参数
     * @return {@link Result}<{@link ?}>
     */
    @Override
    public Result<?> login(UserLoginDto userLoginDto) {
        if (!VerifyUtil.verifyStr(userLoginDto.getUsername()) || !VerifyUtil.verifyStr(userLoginDto.getPassword())) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        User user;
        try {
            user = getUserInfo(userLoginDto);
        } catch (Exception e) {
            return Result.error();
        }
        if (user != null) {
            String token = TokenUtils.genToken(user.getId().toString(), user.getPassword());
            BeanUtil.copyProperties(user, userLoginDto, true);
            userLoginDto.setToken(token);
            //当前角色
            String role = user.getRole();
            //调用包装方法
            List<Menu> roleMenus = getRoleMenus(role);
            //设置用户菜单列表
            userLoginDto.setMenus(roleMenus);
            return Result.success(userLoginDto);
        } else {
            throw new ServiceException(Constants.CODE_0, "用户名或密码错误");
        }
    }

    /**
     * 获取当前角色的菜单列表
     * @param role 角色
     * @return {@link List}<{@link Menu}>
     */
    private List<Menu> getRoleMenus(String role) {
        //角色id
        Integer roleId = roleMapper.selectByRole(role);
        // 当前角色的所有菜单id集合
        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);
        //查出当前角色的所有菜单
        return menuService.getCurrentRoleMenu(menuIds);
    }

    /**
     * 注册业务已更新
     *
     * @param userLoginDto 参数
     * @return {@link Result}<{@link ?}>
     */
    @Override
    public Result<?> saveRegister(UserLoginDto userLoginDto) {
        if (!VerifyUtil.verifyStr(userLoginDto.getUsername())
                || !VerifyUtil.verifyStr(userLoginDto.getPassword())
                || !VerifyUtil.verifyStr(userLoginDto.getConfirmPassword())) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        if (!userLoginDto.getPassword().equals(userLoginDto.getConfirmPassword())) {
            return Result.error(Constants.CODE_400, "两次密码不一致");
        }
        User user = getUserInfo(userLoginDto);

        if (user == null) {
            user = new User();
            BeanUtil.copyProperties(userLoginDto, user, true);
            user.setRole(RoleEnum.ROLE_VISIT.toString());
            save(user);
            return Result.success();
        } else {
            throw new ServiceException(Constants.CODE_0, "用户已存在");
        }
    }

    @Override
    public Result<?> findOne(String username) {
        if (!VerifyUtil.verifyStr(username)){
            return Result.error(Constants.CODE_400,"参数错误");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = getOne(queryWrapper);
        if (user != null) {
            return Result.success(user);
        }else {
            throw new ServiceException(Constants.CODE_0, "没有此用户");
        }
    }

    @Override
    public boolean updatePassword(UserPasswordDto passwordDto) {
        return userMapper.updatePassword(passwordDto);
    }

    @Override
    public List<Menu> getAllMenuByRole(String role) {
        return getRoleMenus(role);
    }

    private User getUserInfo(UserLoginDto userLoginDto) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userLoginDto.getUsername()).eq("password", userLoginDto.getPassword());
        return getOne(queryWrapper);
    }


    /**
     * 导出业务已更新
     * @param userQuery 参数
     * @return {@link Result}<{@link ?}>
     */
    @Override
    public Result<?> export(UserQueryDto userQuery) {
        try {
            //从数据库查询出所有数据
            List<User> list = findPage(userQuery).getData().getRecords();
            ExcelWriter writer = ExcelUtil.getWriter(true);
            //自定义标题别名
            writer.addHeaderAlias("username", "用户名");
            writer.addHeaderAlias("password", "密码");
            writer.addHeaderAlias("nickname", "昵称");
            writer.addHeaderAlias("email", "邮箱");
            writer.addHeaderAlias("phone", "电话");
            writer.addHeaderAlias("address", "地址");
            writer.addHeaderAlias("createTime", "创建时间");
            writer.addHeaderAlias("avatarUrl", "头像");
            writer.setColumnWidth(1, 50);
            writer.setColumnWidth(6, 25);
            writer.setColumnWidth(7, 25);
            writer.setColumnWidth(8, 25);
            writer.setColumnWidth(9, 25);
            //一次性写出list内对象到excel
            writer.write(list, true);
            //全路径
            String exportPath = Constants.EXPORT_PATH + "用户信息" + IdUtil.randomUUID() + ".xlsx";
            OutputStream out = new FileOutputStream(exportPath);
            writer.flush(out);
            writer.close();
            out.close();
            return Result.success(exportPath);
        } catch (Exception e) {
            return Result.error();
        }
    }

    /**
     * 导入业务已更新
     * @param file 参数
     * @return {@link Result}<{@link ?}>
     */
    @Override
    public Result<?> imp(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            ExcelReader reader = ExcelUtil.getReader(inputStream);
            reader.addHeaderAlias("用户名", "username").
                    addHeaderAlias("密码", "password").
                    addHeaderAlias("昵称", "nickname").
                    addHeaderAlias("邮箱", "email").
                    addHeaderAlias("电话", "phone").
                    addHeaderAlias("地址", "addressId").
                    addHeaderAlias("创建时间", "createTime").
                    addHeaderAlias("头像", "avatarUrl");
            List<User> users = reader.readAll(User.class);
            saveBatch(users);
            return Result.success();
        } catch (IOException e) {
            return Result.error();
        }
    }


}




