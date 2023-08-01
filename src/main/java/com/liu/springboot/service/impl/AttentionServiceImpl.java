package com.liu.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.springboot.pojo.Attention;
import com.liu.springboot.service.AttentionService;
import com.liu.springboot.mapper.AttentionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 86183
* @description 针对表【sys_attention】的数据库操作Service实现
* @createDate 2023-08-01 13:55:19
*/
@Service
public class AttentionServiceImpl extends ServiceImpl<AttentionMapper, Attention>
    implements AttentionService{

    @Resource
    private AttentionMapper attentionMapper;

    @Override
    public List<Integer> listByUserId(Integer userId) {
        return attentionMapper.listByUserId(userId);
    }
}




