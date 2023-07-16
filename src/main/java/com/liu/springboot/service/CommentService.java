package com.liu.springboot.service;

import com.liu.springboot.pojo.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 86183
* @description 针对表【sys_comment】的数据库操作Service
* @createDate 2023-07-13 20:21:38
*/
public interface CommentService extends IService<Comment> {

    /**
     * 评论查询
     * @param articleId 文章id
     * @return {@link List}<{@link Comment}>
     */
    List<Comment> findCommentDetail(Integer articleId);
}
