package com.dzkd.website;

import com.dzkd.website.pojo.AdminInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebsiteApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void test(){
        AdminInfo admin = new AdminInfo();
        System.out.println(admin.toString());
    }

}
