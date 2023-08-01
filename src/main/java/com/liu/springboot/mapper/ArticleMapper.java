package com.liu.springboot.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.springboot.pojo.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liu.springboot.pojo.dto.ArticleQueryDto;
import com.liu.springboot.utils.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author 86183
* @description 针对表【sys_article】的数据库操作Mapper
* @createDate 2023-07-12 15:16:47
* @Entity com.liu.springboot.pojo.Article
*/
public interface ArticleMapper extends BaseMapper<Article> {


    /**
     * 多表分页
     * @param page 分页
     * @param articleQueryDto 查询条件
     * @return {@link Page}<{@link Article}>
     */
    Page<Article> findPage(Page<Article> page, @Param("article") ArticleQueryDto articleQueryDto);


    /**
     * 按文章 ID 查找用户 ID
     * @param queryWrapper 条件
     * @return {@link List}<{@link Integer}>
     */
    List<Integer> findUserIdByArticleId(@Param(Constants.WRAPPER) QueryWrapper<Article> queryWrapper);


    /**
     * 查询关注文章所有的业务类
     * @param queryWrapper 条件
     * @return {@link List}<{@link Article}>
     */
    List<Article> findAllByArticle(@Param(Constants.WRAPPER) QueryWrapper<Article> queryWrapper);
}




