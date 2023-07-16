package com.liu.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.springboot.pojo.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liu.springboot.pojo.dto.ArticleQueryDto;

/**
* @author 86183
* @description 针对表【sys_article】的数据库操作Service
* @createDate 2023-07-12 15:16:47
*/
public interface ArticleService extends IService<Article> {

    /**
     * 多表分页
     * @param articleQueryDto 查询条件
     * @return {@link Page}<{@link Article}>
     */
    Page<Article> findPage(ArticleQueryDto articleQueryDto);
}
