package com.liu.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liu.springboot.pojo.Article;
import com.liu.springboot.pojo.Attention;
import com.liu.springboot.pojo.User;
import com.liu.springboot.service.ArticleService;
import com.liu.springboot.service.AttentionService;
import com.liu.springboot.service.UserService;
import com.liu.springboot.utils.Constants;
import com.liu.springboot.utils.RepeatUtil;
import com.liu.springboot.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-08-01  13:55
 * @Description:
 * @Author: LiuHaoYu
 */

@RestController
@RequestMapping("/attention")
public class AttentionController {
    @Resource
    private AttentionService attentionService;

    @Resource
    private ArticleService articleService;

    @Resource
    private UserService userService;


    @PostMapping("/save")
    public Result<?> save (@RequestBody Attention attention) {
        return attentionService.save(attention) ? Result.success() : Result.error();
    }

    @GetMapping("/findAttention")
    public Result<?> findByAttention (Attention attention) {
        LambdaQueryWrapper<Attention> queryWrapper = new LambdaQueryWrapper<Attention>().eq(Attention::getUserId, attention.getUserId()).eq(Attention::getArticleId, attention.getArticleId());
        Attention one = attentionService.getOne(queryWrapper);
        //不等于空代表已经关注返回false
        if (one != null) {
            return Result.success(false);
        }else {
            return Result.success(true);
        }
    }

    @GetMapping("/del")
    public Result<?> delByAttention(Attention attention) {
        LambdaQueryWrapper<Attention> queryWrapper = new LambdaQueryWrapper<Attention>().eq(Attention::getUserId, attention.getUserId()).eq(Attention::getArticleId, attention.getArticleId());
        Attention one = attentionService.getOne(queryWrapper);
        if (one != null) {
            return attentionService.remove(queryWrapper) ? Result.success() : Result.error();
        }else {
            return Result.error(Constants.CODE_500,"取消失败，你未关注此用户");
        }
    }

    @GetMapping("/findAllByUserId/{userId}")
    public Result<?> findAllByUserId(@PathVariable Integer userId) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        List<Integer> articleId = attentionService.listByUserId(userId);
        if (articleId.size() > 0) {
            queryWrapper.in("id",articleId);
            List<Integer> userIdList = articleService.findUserIdByArticleId(queryWrapper);
            // 文章中的userId会有重复，使用自定义工具类去重
            List<Integer> integers = RepeatUtil.repeatUtil(userIdList);
            //使用去重之后的userId来查询user表，拿到对应的user信息
            List<User> list = userService.list(new LambdaQueryWrapper<User>().in(User::getId, integers));
            return Result.success(list);
        }else {
            return Result.success(null);
        }
    }

    @GetMapping("/findAllByArticleId/{userId}")
    public Result<?> findAllByArticleId(@PathVariable Integer userId) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        List<Integer> articleId = attentionService.listByUserId(userId);
        if (articleId.size() > 0) {
            queryWrapper.in("a.id",articleId);
            List<Article> articleList = articleService.findAllByArticle(queryWrapper);
            return Result.success(articleList);
        }else {
            return Result.success(null);
        }
    }
}
