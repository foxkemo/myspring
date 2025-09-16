package org.unomi.myspring.mapper;

import org.apache.ibatis.annotations.*;
import org.unomi.myspring.entity.ArticleLike;

import java.util.List;

@Mapper
public interface ArticleLikeMapper {

    // ===== 插入点赞 =====
    @Insert("INSERT INTO article_like (article_id, user_id, create_time) " +
            "VALUES(#{article_id}, #{user_id}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id") // 回填自增ID
    int insertLike(ArticleLike Articlelike);

    // ===== 取消点赞 =====
    @Delete("DELETE FROM article_like  WHERE article_id=#{article_id} AND user_id=#{user_id}")
    int deleteLike(@Param("article_id") Integer article_id, @Param("user_id") Integer user_id);

    // ===== 查询用户是否已点赞某篇文章 =====
    @Select("SELECT * FROM article_like  WHERE article_id=#{article_id} AND user_id=#{user_id}")
    ArticleLike findByUserAndArticle(@Param("article_id") Integer article_id, @Param("user_id") Integer user_id);


    // ===== 统计某篇文章的点赞数 =====
    @Select("SELECT COUNT(*) FROM article_like  WHERE article_id=#{article_id}")
    int countByArticle(@Param("article_id") Integer article_id);

    @Select("SELECT COUNT(*) FROM article_like WHERE article_id=#{article_id} AND user_id=#{user_id} ")
    int isLiked(@Param("article_id") Integer article_id, @Param("user_id") Integer user_id);

}
