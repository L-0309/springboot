package com.liu.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.springboot.mapper.DictMapper;
import com.liu.springboot.pojo.Dict;
import com.liu.springboot.service.DictService;
import org.springframework.stereotype.Service;

/**
* @author 86183
* @description 针对表【sys_dict】的数据库操作Service实现
* @createDate 2023-07-02 11:58:07
*/
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict>
    implements DictService{

}




