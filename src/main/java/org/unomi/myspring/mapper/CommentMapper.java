package org.unomi.myspring.mapper;

import org.apache.ibatis.annotations.*;
import org.unomi.myspring.entity.Comment;

import java.util.List;

@Mapper
public interface CommentMapper {

        // ===== 插入评论 =====
        @Insert("INSERT INTO comment(article_id, user_id, content, parent_id, create_time, like_count) " +
                "VALUES(#{articleId}, #{userId}, #{content}, #{parentId}, NOW(), #{likeCount})")
        @Options(useGeneratedKeys = true, keyProperty = "id") // 回填自增ID
        int insertComment(Comment comment);

        // ===== 查询单条评论 =====
        @Select("SELECT * FROM comment WHERE id=#{id}")
        Comment findById(@Param("id") Integer id);

        // ===== 查询某篇文章的所有评论 =====
        @Select("SELECT * FROM comment WHERE article_id=#{articleId} ORDER BY create_time ASC")
        List<Comment> findByArticleId(@Param("articleId") Integer articleId);

        // ===== 查询某个用户的所有评论 =====
        @Select("SELECT * FROM comment WHERE user_id=#{userId} ORDER BY create_time DESC")
        List<Comment> findByUserId(@Param("userId") Integer userId);

        // ===== 查询子评论（回复某条评论） =====
        @Select("SELECT * FROM comment WHERE parent_id=#{parentId} ORDER BY create_time ASC")
        List<Comment> findByParentId(@Param("parentId") Integer parentId);

        // ===== 更新评论内容 =====
        @Update("UPDATE comment SET content=#{content} WHERE id=#{id}")
        int updateContent(Comment comment);

        // ===== 删除评论 =====
        @Delete("DELETE FROM comment WHERE id=#{id}")
        int deleteById(@Param("id") Integer id);

        // ===== 更新点赞数 =====
        @Update("UPDATE comment SET like_count=#{likeCount} WHERE id=#{id}")
        int updateLikeCount(@Param("id") Integer id, @Param("likeCount") Integer likeCount);
    }


