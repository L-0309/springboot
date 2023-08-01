package com.liu.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.springboot.pojo.Article;
import com.liu.springboot.pojo.dto.ArticleQueryDto;
import com.liu.springboot.service.ArticleService;
import com.liu.springboot.mapper.ArticleMapper;
import com.liu.springboot.utils.VerifyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 86183
* @description 针对表【sys_article】的数据库操作Service实现
* @createDate 2023-07-12 15:16:47
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{
    @Resource
    ArticleMapper articleMapper;

    @Override
    public Page<Article> findPage(ArticleQueryDto articleQueryDto) {
        if (VerifyUtil.verifyStr(articleQueryDto.getName())) {
            articleQueryDto.setName("%" + articleQueryDto.getName() + "%");
        }
        if (!VerifyUtil.verifyNum(articleQueryDto.getUserId())) {
            articleQueryDto.setUserId(0);
        }
        Page<Article> articlePage = new Page<>(articleQueryDto.getCurrent(),articleQueryDto.getPageSize());
        return articleMapper.findPage(articlePage,articleQueryDto);
    }

    @Override
    public List<Integer> findUserIdByArticleId(QueryWrapper<Article> queryWrapper) {
        return articleMapper.findUserIdByArticleId(queryWrapper);
    }

    @Override
    public List<Article> findAllByArticle(QueryWrapper<Article> queryWrapper) {
        return articleMapper.findAllByArticle(queryWrapper);
    }
}




