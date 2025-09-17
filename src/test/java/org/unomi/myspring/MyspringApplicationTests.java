package org.unomi.myspring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailSender;
import org.unomi.myspring.mapper.UserMapper;
import org.unomi.myspring.service.MailService;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@SpringBootTest
class MyspringApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void userLoads() {



        userMapper.selectById(17);



}


}
