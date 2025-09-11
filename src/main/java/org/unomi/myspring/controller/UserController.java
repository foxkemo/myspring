package org.unomi.myspring.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.unomi.myspring.entity.User;
import org.unomi.myspring.mapper.UserMapper;
import org.unomi.myspring.service.MailService;
import org.unomi.myspring.service.UserService;
@RequestMapping("/user")
@RestController
public class UserController {



    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailService mailService;

    @RequestMapping("/login")
    public User login(@RequestParam(required = false) String username,
                        @RequestParam String password, @RequestParam(required = false) Integer id) {

        if (username == null && id == null) {
          return null;
        }
        User user;
        if (id==null){
             user=userService.loginByUsername( username , password);

        }else {
             user=userService.loginById(id, password);
        }

        if(user!=null){

userMapper.updateLastLogin(user.getId());


        }

            return user;

        }


    @RequestMapping("/register")
    public String register(@RequestParam String username ,@RequestParam String password,@RequestParam String email) {
        User user=userService.register(username, password, email) ;
    if(user!=null){
        ObjectMapper om=new ObjectMapper();
       om.registerModule(new JavaTimeModule());
        try {
            return om.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }

    }else {
        return "Failed";
    }



    }
@RequestMapping("/activateUser")
    public String activateUser(@RequestParam String username ,@RequestParam String code) {
        if (username == null || code == null) {
            return "Wrong Input";

        }

        if(userService.activateUser(username,code)){
            return "Activated";


        }else {
            return "Failed";
        }



}



    }

