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
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/stu", method = RequestMethod.PUT)
    public R updateStudent(@RequestBody Student student) {
        return userService.updateStudent(student);
    }

    @RequestMapping(value = "/stu/changePass", method = RequestMethod.PUT)
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
