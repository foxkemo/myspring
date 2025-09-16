package org.unomi.myspring.mapper;
import org.apache.ibatis.annotations.*;
import org.unomi.myspring.entity.Article;

import java.util.List;

    @Mapper
    public interface ArticleMapper {

        // ===== 插入文章 =====
        @Insert("INSERT INTO article(title, content, author_id, cover_url, view_count, like_count, create_time, update_time) " +
                "VALUES(#{title}, #{content}, #{authorId}, #{coverUrl}, #{viewCount}, #{likeCount}, NOW(), NOW())")
        @Options(useGeneratedKeys = true, keyProperty = "id") // 回填自增ID
        int insertArticle(Article article);

        // ===== 查询单篇文章 =====
        @Select("SELECT * FROM article WHERE id=#{id}")
        Article findById(@Param("id") Integer id);



        // ===== 查询某个作者的所有文章 =====
        @Select("SELECT * FROM article WHERE author_id=#{authorId} ORDER BY create_time DESC")
        List<Article> findByAuthorId(@Param("authorId") Integer authorId);

        // ===== 查询所有文章 =====
        @Select("SELECT id,title,author_id,cover_url FROM article ORDER BY create_time DESC limit #{index},#{size}")
        List<Article> findIntoList(@Param("index") int index, @Param("size") int size);

        // ===== 更新文章内容和封面 =====
        @Update("UPDATE article SET title=#{title}, content=#{content}, cover_url=#{coverUrl}, update_time=NOW() " +
                "WHERE id=#{id}")
        int updateArticle(Article article);

        // ===== 更新文章浏览量 =====
        @Update("UPDATE article SET view_count=view_count+1 WHERE id=#{id}")
        int updateViewCountIncrease(@Param("id") Integer id);

        // ===== 更新文章点赞数 =====
        @Update("UPDATE article SET like_count= like_count+1 WHERE id=#{id}")
        int updateLikeCountIncrease(@Param("id") Integer id);
        @Update("UPDATE article SET like_count= like_count-1 WHERE id=#{id}")
        int updateLikeCountDecrease(@Param("id") Integer id);



        // ===== 删除文章 =====
        @Delete("DELETE FROM article WHERE id=#{id}")
        int deleteById(@Param("id") Integer id);

        // ===== 搜索文章（标题模糊搜索） =====
        @Select("select title,id,author_id,cover_url FROM article WHERE title like concat('%',#{str},'%' ) order by create_time desc limit #{index},#{size} ")
        List<Article> findByTitle(@Param("index") int index, @Param("size") int size,@Param("str") String str);


    }


