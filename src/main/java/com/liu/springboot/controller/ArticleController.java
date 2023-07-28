package com.liu.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liu.springboot.exception.ServiceException;
import com.liu.springboot.pojo.Article;
import com.liu.springboot.pojo.User;
import com.liu.springboot.pojo.dto.ArticleQueryDto;
import com.liu.springboot.service.ArticleService;
import com.liu.springboot.service.UserService;
import com.liu.springboot.utils.Constants;
import com.liu.springboot.utils.Result;
import com.liu.springboot.utils.VerifyUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-07-12  15:21
 * @Description:
 * @Author: LiuHaoYu
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @Resource
    private UserService userService;

    @PostMapping("/save")
    public Result<?> save(@RequestBody Article article) {
        if (articleService.saveOrUpdate(article)) {
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @GetMapping("/findAll")
    public Result<?> findAll(ArticleQueryDto articleQueryDto) {
        return Result.success(articleService.findPage(articleQueryDto));
    }

    @GetMapping("/findAll/{id}")
    public Result<?> findAllByUserId(@PathVariable Integer id) {
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("user_id",id);
        List<Article> articleList = articleService.list(articleQueryWrapper);
        return Result.success(articleList);
    }

    @GetMapping("/detail/{id}")
    public Result<?> detailById(@PathVariable Integer id) {
        Article article = articleService.getById(id);
        if (article != null) {
            Integer userId = article.getUserId();
            User user = userService.getById(userId);
            article.setUserNickname(user.getNickname());
            return Result.success(article);
        }else {
            return Result.error();
        }
    }

    @PostMapping("/del/batch")
    public Result<?> delete(@RequestBody List<Integer> ids) {
        if (ids.size() == 0) {
            return Result.error(Constants.CODE_400, "未收到参数");
        }
        List<Article> articles = articleService.listByIds(ids);
        if (articles.size() > 0) {
            return articleService.removeBatchByIds(articles) ? Result.success() : Result.error(Constants.CODE_0, "删除失败");
        } else {
            throw new ServiceException(Constants.CODE_0, "未找到信息");
        }
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        if (!VerifyUtil.verifyNum(id)) {
            return Result.error(Constants.CODE_400, "未收到参数");
        }
        return articleService.removeById(id) ? Result.success() : Result.error(Constants.CODE_0, "删除失败");
    }
}
