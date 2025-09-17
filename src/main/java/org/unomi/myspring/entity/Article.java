package org.unomi.myspring.entity;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Article {

    private Integer id;              // 主键ID
    private String title;            // 标题
    private String content;          // 正文内容desc
    private Integer author_id;        // 作者ID（外键 -> User.id）
    private String cover_url;         // 封面图URL
    private Integer view_count;       // 浏览量
    private Integer like_count;       // 点赞数
    private LocalDateTime create_time;   // 发布时间
    private LocalDateTime update_time;    // 更新时间
    private String author_name ="not exist";// 需要查找作为结果返回
    private Integer comment_count ;


}
