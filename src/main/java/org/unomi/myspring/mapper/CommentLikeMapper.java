package org.unomi.myspring.mapper;

import org.apache.ibatis.annotations.*;
import org.unomi.myspring.entity.CommentLike;

import java.util.List;

@Mapper
public interface CommentLikeMapper {


    // ===== 插入点赞 =====
    @Insert("INSERT INTO user_like(comment_id, user_id, create_time) " +
            "VALUES(#{commentId}, #{userId}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id") // 回填自增ID
    int insertLike(CommentLike Commentlike);

    // ===== 取消点赞 =====
    @Delete("DELETE FROM user_like WHERE comment_id=#{commentId} AND user_id=#{userId}")
    int deleteLike(@Param("commentId") Integer commentId, @Param("userId") Integer userId);

    // ===== 查询用户是否已点赞某篇文章 =====
    @Select("SELECT * FROM user_like WHERE comment_id=#{commentId} AND user_id=#{userId}")
    CommentLike findByUserAndComment(@Param("commentId") Integer commentId, @Param("userId") Integer userId);

    // ===== 查询某篇文章的所有点赞 =====
    @Select("SELECT * FROM user_like WHERE comment_id=#{commentId}")
    List<CommentLike> findByCommentId(@Param("commentId") Integer commentId);

    // ===== 查询某个用户所有点赞记录 =====
    @Select("SELECT * FROM user_like WHERE user_id=#{userId}")
    List<CommentLike> findByUserId(@Param("userId") Integer userId);

    // ===== 统计某篇文章的点赞数 =====
    @Select("SELECT COUNT(*) FROM user_like WHERE comment_id=#{commentId}")
    int countByCommentId(@Param("commentId") Integer commentId);
}
