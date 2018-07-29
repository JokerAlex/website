package com.dzkd.website.service;


import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginServiceTest {


    @Autowired
    private LoginService loginService;

    @Test
    public void test(){
        JSONObject jsonObject = loginService.login("test", "111");
        System.out.println(jsonObject.toString());
    }
}
