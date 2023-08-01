package com.liu.springboot.service;

import com.liu.springboot.pojo.Attention;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 86183
* @description 针对表【sys_attention】的数据库操作Service
* @createDate 2023-08-01 13:55:19
*/
public interface AttentionService extends IService<Attention> {

    /**
     * 查询用户所关注的文章
     * @param userId 用户id
     * @return {@link List}<{@link Integer}>
     */
    List<Integer> listByUserId(Integer userId);
}
