package com.dzkd.website.service;


import com.alibaba.fastjson.JSONObject;
import com.dzkd.website.pojo.AdminInfo;
import com.dzkd.website.pojo.Student;
import com.dzkd.website.pojo.UserInfo;
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
    public void getAllAdminInfo(){
        List<AdminInfo> adminInfoList = userService.getAllAdminInfo(2,10);

        for (AdminInfo a : adminInfoList){
            System.out.println(a.toString());
        }
    }

    @Test
    public void insertUser(){
        UserInfo userInfo = new UserInfo();
        userInfo.setRealName("haha");
        int result = userService.addUserInfo(userInfo);
        System.out.println("result:" + result + "\n主键:" + userInfo.getUserInfoId());
    }

    @Test
    public void addAdmin(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserInfoId(19);
        userInfo.setRealName("addAdmin");

        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setUserInfo(userInfo);
        adminInfo.setAdminBlockId(3);
        adminInfo.setUserUserInfoId(19);

        JSONObject jsonObject = userService.addAdminInfo(adminInfo);
        System.out.println(jsonObject.toJSONString());

    }

    @Test
    public void updateAdmin(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserInfoId(19);
        userInfo.setRealName("addAdmin");
        userInfo.setAddress("cd");
        userInfo.setTelephone("1234567890");
        userInfo.setEmail("111@111.com");
        userInfo.setUserName("hehehe");

        AdminInfo adminInfo = new AdminInfo(15,3,19);
        adminInfo.setUserInfo(userInfo);

        JSONObject jsonObject = userService.updateAdminInfo(adminInfo);
        System.out.println(jsonObject.toJSONString());

    }

    @Test
    public void delAdmin(){
        AdminInfo adminInfo = new AdminInfo(1,3,1);
        System.out.println(userService.delAdminInfo(adminInfo).toJSONString());
    }

    @Test
    public void addStudent(){

        UserInfo userInfo = new UserInfo();
        userInfo.setRealName("addAdmin");
        userInfo.setAddress("cd");
        userInfo.setTelephone("1234567890");
        userInfo.setEmail("111@111.com");
        userInfo.setUserName("haha");

        Student student = new Student();
        student.setStuId("2016220401016");
        student.setStuCollege("xr");
        student.setStuMajor("hlw");
        student.setStuGrade("2016");
        student.setUserInfo(userInfo);

        System.out.println(userService.addStudent(student));
    }

    @Test
    public void updateStudent(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserInfoId(21);
        userInfo.setRealName("addAdmin");
        userInfo.setAddress("cd");
        userInfo.setTelephone("0987654321");
        userInfo.setEmail("111@111.com");
        userInfo.setUserName("test");

        Student student = new Student();
        student.setStuId("2016220401016");
        student.setStuCollege("xr");
        student.setStuMajor("hlw");
        student.setStuGrade("2016");
        student.setUserInfo(userInfo);

        System.out.println(userService.updateStudent(student));
    }

    @Test
    public void changePassword(){
        System.out.println(userService.changePassword("test","222","111"));
        System.out.println(userService.changePassword("test","111","111"));
        System.out.println(userService.changePassword("test","111","222"));
        System.out.println(userService.changePassword("test","222","111"));
    }

}
