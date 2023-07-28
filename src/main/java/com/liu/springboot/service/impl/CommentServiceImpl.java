package com.liu.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.springboot.pojo.Comment;
import com.liu.springboot.service.CommentService;
import com.liu.springboot.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 86183
* @description 针对表【sys_comment】的数据库操作Service实现
* @createDate 2023-07-13 20:21:38
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

    @Resource
    private CommentMapper commentMapper;

    @Override
    public List<Comment> findCommentDetail(Integer articleId) {
        return commentMapper.findCommentDetail(articleId);
    }

    @Override
    public List<Comment> findCountArticleComment() {
        return commentMapper.findCountArticleComment();
    }
}




