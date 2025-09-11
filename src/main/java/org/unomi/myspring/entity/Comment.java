package org.unomi.myspring.entity;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class Comment {


        private Integer id;              // 主键ID
        private Integer articleId;       // 所属文章ID（外键 -> Article.id）
        private Integer userId;          // 评论者ID（外键 -> User.id）
        private String content;          // 评论内容
        private Integer parentId;        // 父评论ID（0 表示顶级评论，非0 表示回复某条评论）
        private LocalDateTime createTime;  // 评论时间
        private Integer likeCount;       // 点赞数


    }


