package org.unomi.myspring.mapper;


import org.apache.ibatis.annotations.*;
import org.unomi.myspring.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {





    // ===== 登录 / 查询 =====
    @Select("SELECT * FROM user WHERE id=#{id} AND password=#{password}")
    User getUserById(@Param("id") Integer id, @Param("password") String password);

    @Select("SELECT * FROM user WHERE username=#{username} AND password=#{password}")
    User getUserByUsername(@Param("username") String username, @Param("password") String password);

    // ===== 查询单个用户 =====
    @Select("SELECT * FROM user WHERE id=#{id}")
    User findById(@Param("id") Integer id);

    @Select("SELECT * FROM user WHERE username=#{username}")
    User findByUsername(@Param("username") String username);

    @Select("SELECT * FROM user WHERE email=#{email}")
    User findByEmail(@Param("email") String email);

    // ===== 查询所有用户 =====
    @Select("SELECT * FROM user")
    List<User> findAll();
//
//    // ===== 条件查询（可分页） =====
//    @Select("SELECT * FROM user WHERE gender=#{gender} LIMIT #{offset}, #{limit}")
//    List<User> findByGender(@Param("gender") Short gender,
//                            @Param("offset") int offset,
//                            @Param("limit") int limit);

    // ===== 插入用户 =====
    @Insert("INSERT INTO user(username, email, password, gender, register_time, user_image ,code) " +
            "VALUES(#{username}, #{email}, #{password}, #{gender}, NOW(), #{user_image},#{code})")
    @Options(useGeneratedKeys = true, keyProperty = "id")  // 回填自增ID
    int insertUser(User user);

    // ===== 更新用户 =====
    @Update("UPDATE user SET username=#{username}, email=#{email}, password=#{password}, " +
            "gender=#{gender}, user_image=#{user_image}, last_login=#{last_login} " +
            "WHERE id=#{id}")
    int updateUser(User user);


    @Update("UPDATE user SET activated=1 WHERE username=#{username} AND code=#{code}")
    int updateActivated(@Param("username") String username, @Param("code")String code);

    // ===== 删除用户 =====
    @Delete("DELETE FROM user WHERE id=#{id}")
    int deleteById(@Param("id") Integer id);

    // ===== 更新最后登录时间 =====
    @Update("UPDATE user SET last_login=NOW() WHERE id=#{id}")
    int updateLastLogin(@Param("id") Integer id);
}

