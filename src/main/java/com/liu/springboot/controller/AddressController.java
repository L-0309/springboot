package com.liu.springboot.controller;

import com.liu.springboot.pojo.Address;
import com.liu.springboot.service.AddressService;
import com.liu.springboot.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-06-28  16:31
 * @Description:
 * @Author: LiuHaoYu
 */
@RestController
@RequestMapping("/address")
public class AddressController {
    @Resource
    private AddressService addressService;

    @GetMapping
    public Result<List<Address>> find(){

        return Result.success(addressService.list());
    }
}
