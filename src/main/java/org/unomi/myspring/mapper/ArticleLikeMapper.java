package org.unomi.myspring.mapper;

import org.apache.ibatis.annotations.*;
import org.unomi.myspring.entity.ArticleLike;

import java.util.List;

@Mapper
public interface ArticleLikeMapper {

    // ===== 插入点赞 =====
    @Insert("INSERT INTO user_like(article_id, user_id, create_time) " +
            "VALUES(#{articleId}, #{userId}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id") // 回填自增ID
    int insertLike(ArticleLike Articlelike);

    // ===== 取消点赞 =====
    @Delete("DELETE FROM user_like WHERE article_id=#{articleId} AND user_id=#{userId}")
    int deleteLike(@Param("articleId") Integer articleId, @Param("userId") Integer userId);

    // ===== 查询用户是否已点赞某篇文章 =====
    @Select("SELECT * FROM user_like WHERE article_id=#{articleId} AND user_id=#{userId}")
    ArticleLike findByUserAndArticle(@Param("articleId") Integer articleId, @Param("userId") Integer userId);

    // ===== 查询某篇文章的所有点赞 =====
    @Select("SELECT * FROM user_like WHERE article_id=#{articleId}")
    List<ArticleLike> findByArticleId(@Param("articleId") Integer articleId);

    // ===== 查询某个用户所有点赞记录 =====
    @Select("SELECT * FROM user_like WHERE user_id=#{userId}")
    List<ArticleLike> findByUserId(@Param("userId") Integer userId);

    // ===== 统计某篇文章的点赞数 =====
    @Select("SELECT COUNT(*) FROM user_like WHERE article_id=#{articleId}")
    int countByArticleId(@Param("articleId") Integer articleId);
}
