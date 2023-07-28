package com.liu.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liu.springboot.pojo.Comment;
import com.liu.springboot.pojo.User;
import com.liu.springboot.service.CommentService;
import com.liu.springboot.service.UserService;
import com.liu.springboot.utils.Constants;
import com.liu.springboot.utils.Result;
import com.liu.springboot.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-07-13  20:21
 * @Description:
 * @Author: LiuHaoYu
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private CommentService commentService;

    @Resource
    private UserService userService;

    @GetMapping("/tree/{articleId}")
    public Result<?> findTree(@PathVariable Integer articleId){
        //查询所有的评论数据
        List<Comment> list = commentService.findCommentDetail(articleId);
        //查询评论数据(不包括回复)
        List<Comment> originList = list.stream().filter(comment -> comment.getOriginId() == null).collect(Collectors.toList());
        //设置评论数据的子节点，也就是回复内容
        for (Comment origin : originList) {
            //所有的回复
            List<Comment> collect = list.stream().filter(comment -> origin.getId().equals(comment.getOriginId())).collect(Collectors.toList());
            origin.setChildren(collect);
        }
        return Result.success(originList);
    }

    @PostMapping("/save")
    public Result<?> save(@RequestBody Comment comment) {
        if (comment.getId() == null) {
            comment.setTime(new Date());
            Integer pid = comment.getPid();
            Comment pComment = commentService.getById(pid);
            // 如果是回复评论的话就会有一个pid，接收到的参数中如果没有pid那么他就是评论
            if (comment.getPid() != null) {
                if (pComment.getOriginId() != null) {
                    //如果当前回复的父级有祖宗，那么就设置相同的祖宗
                    comment.setOriginId(pComment.getOriginId());
                }else {
                    //否则就设置父级为当前回复的祖宗
                    comment.setOriginId(comment.getPid());
                }
                if (!comment.getPid().equals(comment.getOriginId())) {
                    Comment one = commentService.getById(comment.getPid());
                    if (one != null) {
                        User user = userService.getById(one.getUserId());
                        if (user != null) {
                            comment.setPname(user.getNickname());
                        }
                    }
                }
            }
        }
        commentService.saveOrUpdate(comment);
        return Result.success();
    }

    @GetMapping("/findCountArticleComment")
    public Result<?> findCountArticleComment() {
        List<Comment> list = commentService.findCountArticleComment();
        return Result.success(list);
    }

    @DeleteMapping("/del/{id}")
    public Result<?> delById(@PathVariable Integer id) {
        Comment comment = commentService.getById(id);
        if (comment == null) {
            return Result.error(Constants.CODE_0,"未找到");
        }
        return commentService.removeById(id) ? Result.success(): Result.error();
    }
}
