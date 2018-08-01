package com.dzkd.website.controller;


import com.alibaba.fastjson.JSONObject;
import com.dzkd.website.pojo.AdminInfo;
import com.dzkd.website.pojo.R;
import com.dzkd.website.pojo.Student;
import com.dzkd.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class UserAdminController {

    private UserService userService;

    @Autowired
    public UserAdminController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/userAdmin/stu", method = RequestMethod.GET)
    public R showStudents(int pageNum, int pageSize) {
        return userService.getAllStudent(pageNum, pageSize);
    }

    @RequestMapping(value = "/userAdmin/stu", method = RequestMethod.POST)
    public R addStudent(@RequestBody Student student) {
        return userService.addStudent(student);
    }

    @RequestMapping(value = "/stu", method = RequestMethod.PUT)
    public R updateStudent(@RequestBody Student student) {
        return userService.updateStudent(student);
    }


    @RequestMapping(value = "/userAdmin/admin", method = RequestMethod.GET)
    public R showAdmins(int pageNum, int pageSize) {
        return userService.getAllAdminInfo(pageNum, pageSize);
    }

    @RequestMapping(value = "/userAdmin/admin", method = RequestMethod.POST)
    public R addAdmin(@RequestBody AdminInfo adminInfo) {
        return userService.addAdminInfo(adminInfo);
    }

    @RequestMapping(value = "/userAdmin/admin", method = RequestMethod.PUT)
    public R updateAdmin(@RequestBody AdminInfo adminInfo) {
        return userService.updateAdminInfo(adminInfo);
    }

    @RequestMapping(value = "/userAdmin/admin", method = RequestMethod.DELETE)
    public R delAdmin(@RequestBody AdminInfo adminInfo) {
        return userService.delAdminInfo(adminInfo);
    }


    @RequestMapping(value = "/changePass", method = RequestMethod.PUT)
    public R changePassword(HttpServletRequest request, @RequestBody Map<String, String> map) {
        HttpSession session = request.getSession(false);
        JSONObject data = (JSONObject) session.getAttribute(session.getId() + "userData");
        int isAdmin = (int) data.get("isAdmin");

        String userName;
        if (isAdmin == 1) {
            userName = ((AdminInfo) data.get("admin")).getUserInfo().getUserName();
        } else {
            userName = ((Student) data.get("stu")).getUserInfo().getUserName();
        }
        return userService.changePassword(userName, map.get("oldPass"), map.get("newPass"));
    }
}
