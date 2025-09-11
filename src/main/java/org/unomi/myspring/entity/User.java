package org.unomi.myspring.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class User {


        private Integer id;             // 主键ID
        private String username;            // 用户名
        private String email;           // 邮箱
        private String password;        // 密码
        private Short gender;           // 性别（0=女,1=男 等）
        private String code;            // 验证码

        private LocalDateTime register_time; // 注册时间

        private LocalDateTime last_login;    // 最后登录时间
        private String user_image;
        private Short activated;




    }
