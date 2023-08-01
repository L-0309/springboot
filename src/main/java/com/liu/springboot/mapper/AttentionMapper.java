package com.liu.springboot.mapper;

import com.liu.springboot.pojo.Attention;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author 86183
* @description 针对表【sys_attention】的数据库操作Mapper
* @createDate 2023-08-01 13:55:19
* @Entity com.liu.springboot.pojo.Attention
*/
public interface AttentionMapper extends BaseMapper<Attention> {

    /**
     * 查询用户关注的文章
     * @param userId 用户id
     * @return {@link List}<{@link Integer}>
     */
    @Select("select article_id from liu.sys_attention where user_id = #{userId}")
    List<Integer> listByUserId(@Param("userId") Integer userId);
}




