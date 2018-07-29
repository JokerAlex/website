package com.dzkd.website.controller;


import com.alibaba.fastjson.JSONObject;
import com.dzkd.website.dao.AdminInfoMapper;
import com.dzkd.website.dao.StudentMapper;
import com.dzkd.website.dao.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("/userAdmin")
public class UserAdminController {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private AdminInfoMapper adminInfoMapper;

    @RequestMapping(name = "/stu", method = RequestMethod.GET)
    public JSONObject showStudents(){
    return new JSONObject();
    }



}
