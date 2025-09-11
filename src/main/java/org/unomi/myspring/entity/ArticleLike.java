package org.unomi.myspring.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleLike {


    private Integer id;              // 主键ID
    private Integer article_id;       // 被点赞的评论ID
    private Integer user_id;          // 点赞的用户ID
    private LocalDateTime create_time; // 点赞时间

}
