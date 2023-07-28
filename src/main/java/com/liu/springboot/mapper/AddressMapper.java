package com.liu.springboot.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.springboot.pojo.Address;
import com.liu.springboot.utils.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 86183
* @description 针对表【sys_address】的数据库操作Mapper
* @createDate 2023-06-28 15:07:50
* @Entity com.liu.springboot.pojo.Address
*/
public interface AddressMapper extends BaseMapper<Address> {

    IPage<Address> listByWrapper(Page<Address> page, @Param(Constants.WRAPPER) QueryWrapper<Address> queryWrapper);
}




