package com.dzkd.website.service;

import com.alibaba.fastjson.JSONObject;
import com.dzkd.website.pojo.AdminInfo;
import com.dzkd.website.pojo.Student;
import com.dzkd.website.pojo.UserInfo;

import java.util.List;

public interface UserService {

    List<Student> getAllStudent(int pageNum, int pageSize);

    List<AdminInfo> getAllAdminInfo(int pageNum, int pageSize);

    int addUserInfo(UserInfo userInfo);

    JSONObject addAdminInfo(AdminInfo adminInfo);

    JSONObject updateAdminInfo(AdminInfo adminInfo);

    JSONObject delAdminInfo(AdminInfo adminInfo);

    JSONObject addStudent(Student student);

    JSONObject updateStudent(Student student);

    JSONObject changePassword(String userName, String oldPassword, String newPassword);
}