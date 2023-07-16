package com.liu.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.liu.springboot.pojo.User;
import com.liu.springboot.service.UserService;
import com.liu.springboot.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-07-01  19:09
 * @Description: echarts图形
 * @Author: LiuHaoYu
 */
@RestController
@RequestMapping("/echarts")
public class EchartsController {
    @Resource
    private UserService userService;

    @GetMapping("/example")
    public Result<?> get() {
        Map<String,Object> map = new HashMap<>(20);
        map.put("x", CollUtil.newArrayList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
        map.put("y",CollUtil.newArrayList(820, 932, 901, 934, 1290, 1330, 1320));
        return Result.success(map);
    }

    @GetMapping("members")
    public Result<?> members(){
        List<User> list = userService.list();
        //第一季度
        int q1 = 0;
        //第二季度
        int q2 = 0;
        //第三季度
        int q3 = 0;
        //第四季度
        int q4 = 0;
        for (User user : list) {
            Date createTime = user.getCreateTime();
            int quarter = DateUtil.quarter(createTime);
            switch (quarter) {
                //第一季度
                case 1 : q1 += 1; break;
                //第二季度
                case 2 : q2 += 1; break;
                //第三季度
                case 3 : q3 += 1; break;
                //第四季度
                case 4 : q4 += 1; break;
                //默认跳出
                default: break;
            }
        }
        return Result.success(CollUtil.newArrayList(q1, q2, q3, q4));
    }
}
