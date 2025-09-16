package org.unomi.myspring.service;

import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unomi.myspring.entity.User;
import org.unomi.myspring.mapper.UserMapper;

import java.sql.SQLException;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    MailService mailService;
    public User loginById(Integer id, String password) {
        return userMapper.getUserById(id, password);
    }

    public User loginByUsername(String username, String password) {
        return userMapper.getUserByUsername(username, password);
    }

    public String register(String username, String password, String email) {



        if (userMapper.isExist(username,email)!=0) {

            return "username or email already exists";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setGender((short) 1);
        user.setUser_image("");
        Random random =new Random();
        Integer code= 100000+random.nextInt(900000);
        user.setCode(code.toString());
        Integer flag = 0;
        try {

            flag=userMapper.insertUser(user);

        }
        catch (Exception e) {

            return e.getMessage();
        }

        if (flag != 0) {
            mailService.sendSimpleMail(user.getEmail(),"test",user.getCode());
            return "success";

        } else {
            return "unknown error";
        }

    }



    public boolean activateUser(String username, String code){

       if( userMapper.updateActivated(username,code) !=0){

           return true;
       }else {
           return false;
       }


    }


    public User getUserById(Integer id) {
      return   userMapper.findById(id);

    }

    public boolean sendCode(String email) {
        Random random =new Random();
        Integer code= 100000+random.nextInt(900000);
       try{ mailService.sendSimpleMail(email,"Forget Account",code.toString());
           userMapper.updateCode(email,code.toString());
           return true;}
       catch (Exception e){
           return false;
       }


    }

    public User resetPassword(String email, String password , String code) {
       User user= userMapper.findByEmail(email);
       if(user.getCode().equals(code)){
           user.setPassword(password);
           userMapper.updateUser(user);
           return user;
       }else {
           return null;

       }



    }



}