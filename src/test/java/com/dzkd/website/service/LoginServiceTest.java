package com.dzkd.website.service;


import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginServiceTest {


    @Autowired
    private LoginService loginService;

    @Test
    public void test() {
        JSONObject adminAndStudent = loginService.login("test", "111");
        System.out.println("Admin And Student\n" + adminAndStudent.toString());

        JSONObject admin = loginService.login("test2", "222");
        System.out.println("Admin\n" + admin.toString());

        JSONObject student = loginService.login("test3", "333");
        System.out.println("Student\n" + student.toString());

        JSONObject wrongPass = loginService.login("test", "222");
        System.out.println("wrongPass\n" + wrongPass.toString());
    }

    @Test
    public void time() {
        System.out.println("Date:" + new Date().toString());
        System.out.println("System:" + System.currentTimeMillis());
    }
}
