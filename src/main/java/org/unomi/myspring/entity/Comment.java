package org.unomi.myspring.entity;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class Comment {


        private Integer id;              // 主键ID
        private Integer article_id;       // 所属文章ID（外键 -> Article.id）
        private Integer user_id;          // 评论者ID（外键 -> User.id）
        private String content;          // 评论内容
        private Integer parent_id;        // 父评论ID（0 表示顶级评论，非0 表示回复某条评论）
        private LocalDateTime create_time;  // 评论时间
        private Integer like_count;       // 点赞数


    }


