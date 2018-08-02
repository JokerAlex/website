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

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void getAllAdminInfo(){
        System.out.println(userService.getAllAdminInfo(1,5).toString());
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
        UserInfo userInfo = new UserInfo(null,"qqq","hs","0987654321","333@123.com","test5",
                "111",null);

        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setUserInfo(userInfo);
        adminInfo.setAdminBlockId(3);
        adminInfo.setUserUserInfoId(userInfo.getUserInfoId());
        System.out.println(userService.addAdminInfo(adminInfo).toString());

    }

    @Test
    public void updateAdmin(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserInfoId(29);
        userInfo.setRealName("addAdmin");
        userInfo.setAddress("cd");
        userInfo.setTelephone("1234567890");
        userInfo.setEmail("111@111.com");
        userInfo.setUserName("test5");

        AdminInfo adminInfo = new AdminInfo(17,3,29);
        adminInfo.setUserInfo(userInfo);

        System.out.println(userService.updateAdminInfo(adminInfo).toString());

    }

    @Test
    public void delAdmin(){
        AdminInfo adminInfo = new AdminInfo(20,3,29);
        System.out.println(userService.delAdminInfo(adminInfo).toString());
    }

    @Test
    public void addStudent(){

        UserInfo userInfo = new UserInfo();
        userInfo.setRealName("addAdmin");
        userInfo.setAddress("cd");
        userInfo.setTelephone("1234567890");
        userInfo.setEmail("111@111.com");
        userInfo.setUserName("haha");
        userInfo.setUserPassword("222");

        Student student = new Student();
        student.setStuId("2016220401017");
        student.setStuCollege("xr");
        student.setStuMajor("hlw");
        student.setStuGrade("2016");
        student.setUserInfo(userInfo);

        System.out.println(userService.addStudent(student).toString());
    }

    @Test
    public void updateStudent(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserInfoId(30);
        userInfo.setRealName("addAdmin");
        userInfo.setAddress("cd");
        userInfo.setTelephone("0987654321");
        userInfo.setEmail("111@111.com");
        userInfo.setUserName("testhaha");

        Student student = new Student();
        student.setStuId("2016220401017");
        student.setStuCollege("xr");
        student.setStuMajor("hlw");
        student.setStuGrade("2016");
        student.setUserInfo(userInfo);

        System.out.println(userService.updateStudent(student).toString());
    }

    @Test
    public void changePassword(){
        System.out.println(userService.changePassword("test","222","111").toString());
        System.out.println(userService.changePassword("test","111","111").toString());
        System.out.println(userService.changePassword("test","111","222").toString());
        System.out.println(userService.changePassword("test","222","111").toString());
    }

}
