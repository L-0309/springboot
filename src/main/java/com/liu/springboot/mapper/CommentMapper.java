package com.liu.springboot.mapper;

import com.liu.springboot.pojo.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* @author 86183
* @description 针对表【sys_comment】的数据库操作Mapper
* @createDate 2023-07-13 20:21:38
* @Entity com.liu.springboot.pojo.Comment
*/
public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 评论查询
     * @param articleId 文章id
     * @return {@link List}<{@link Comment}>
     */
    @Select("select c.*,u.nickname,u.avatar_url from liu.sys_comment c join liu.sys_user u on c.user_id = u.id and c.article_id = #{articleId}")
    List<Comment> findCommentDetail(@Param("articleId") Integer articleId);

    /**
     * 获取评论的总数
     * @return {@link List}<{@link Comment}>
     */
    @Select("select article_id, count(1) as count from liu.sys_comment GROUP BY article_id")
    List<Comment> findCountArticleComment();
}




