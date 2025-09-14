package org.unomi.myspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unomi.myspring.entity.User;
import org.unomi.myspring.mapper.UserMapper;

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

    public User register(String username, String password, String email) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setGender((short) 1);
        user.setUser_image("");
        Random random =new Random();
        Integer code= 100000+random.nextInt(900000);
        user.setCode(code.toString());
        if (userMapper.insertUser(user) != 0) {
            mailService.sendSimpleMail(user.getEmail(),"test",user.getCode());
            return userMapper.getUserByUsername(username, password);

        } else {
            return null;
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