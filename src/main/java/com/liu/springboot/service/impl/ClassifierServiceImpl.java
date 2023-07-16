package com.liu.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.springboot.mapper.ClassifierMapper;
import com.liu.springboot.pojo.Classifier;
import com.liu.springboot.service.ClassifierService;
import org.springframework.stereotype.Service;

/**
* @author 86183
* @description 针对表【sys_classifier】的数据库操作Service实现
* @createDate 2023-07-01 09:12:24
*/
@Service
public class ClassifierServiceImpl extends ServiceImpl<ClassifierMapper, Classifier>
    implements ClassifierService{

}




