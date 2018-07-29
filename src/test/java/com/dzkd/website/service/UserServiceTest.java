package com.dzkd.website.service;


import com.dzkd.website.pojo.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void selectAll(){
        List<Student> studentList = userService.seelectAll();

        for (Student s : studentList){
            System.out.println(s.toString());
        }
    }
}
