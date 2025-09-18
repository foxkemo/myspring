package org.unomi.myspring.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.unomi.myspring.entity.User;
import org.unomi.myspring.mapper.UserMapper;
import org.unomi.myspring.service.MailService;
import org.unomi.myspring.service.UserService;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.jsonwebtoken.Jwts.SIG.HS256;

@RequestMapping("/user")
@RestController
public class UserController {



    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailService mailService;


    @RequestMapping("/jwtLogin")
    public String jwtLogin(HttpServletRequest request) {

        Map<String,String> map = new HashMap<>();
        map.put("username",request.getParameter("username"));
     SecretKey key= Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String jwt= Jwts.builder().signWith(SignatureAlgorithm.HS256,key).setClaims(map).setExpiration(new Date(System.currentTimeMillis()+3600*1000)).compact();





        return jwt;

    }




    @RequestMapping("/login")
    public String login(@RequestParam(required = false) String username,
                      @RequestParam String password, @RequestParam(required = false) Integer id, HttpSession session) {

        if (username == null && id == null) {
            return "<script>"
                    + "alert('Please enter your username and password!');"
                    + "window.location.href='/user/login';"
                    +"</script>";

        }
        User user;
        if (id == null) {
            user = userService.loginByUsername(username, password);

        } else {
            user = userService.loginById(id, password);
        }

        if (user != null) {

            userMapper.updateLastLogin(user.getId());
            session.setAttribute("user", user.getId());
            String targetUrl = "/profile.html"; // 想跳转的页面
            return "<script>"
                    + "alert('Welcome " + username + "');"
                    + "window.location.href='" + targetUrl + "';"
                    + "</script>";

        }else{

            return "<script>"
                    + "alert('wrong username or password!');"
                    + "window.location.href='/user/login';"
                    +"</script>";
        }


    }


    @RequestMapping("/register")
    public String register(@RequestParam String username ,@RequestParam String password,@RequestParam String email) {
        String msg=userService.register(username, password, email) ;
        String res;
        if(msg.equals("success")) {
             res = "<script>"
                    + "alert('" +
                    msg +
                    "\\n点击跳转至激活页面" +
                    "');" +
                    "window.location.href='/activate.html'" + "</script>";
        }else{
            res = "<script>"
                    + "alert('" +
                    msg +
                    "\\n点击跳转至注册页面" +
                    "');" +
                    "window.location.href='/register.html'" + "</script>";


        }
        return res;
    }



@RequestMapping("/profile")
public User profile(HttpSession session) {
        Integer id= (Integer) session.getAttribute("user");
        return userService.getUserById(id);



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

    @RequestMapping("/sendCode")
    public String sendCode(@RequestParam String email){
if(userService.sendCode(email)){

    return "Success";
}else {

    return "Failed";
}

    }



    @RequestMapping("/resetPassword")
    public String resetPassword(@RequestBody Map<String,String> form, HttpSession session){
       String email= form.get("email");
        String password=form.get("password");
       String code= form.get("code");
      User user=  userService.resetPassword(email,password,code);
       if(  user!= null)
       {
           session.setAttribute("user", user.getId());
           return "Success 用户名为"+user.getUsername();

       }
       else {

           return "Failed";
       }


    }

    }

