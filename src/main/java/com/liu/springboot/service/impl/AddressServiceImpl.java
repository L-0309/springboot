package com.liu.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.springboot.mapper.AddressMapper;
import com.liu.springboot.pojo.Address;
import com.liu.springboot.service.AddressService;
import org.springframework.stereotype.Service;

/**
* @author 86183
* @description 针对表【sys_address】的数据库操作Service实现
* @createDate 2023-06-28 15:07:50
*/
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address>
    implements AddressService{

}




