package com.dzkd.website.service;

import com.alibaba.fastjson.JSONObject;
import com.dzkd.website.pojo.AdminInfo;
import com.dzkd.website.pojo.Student;
import com.dzkd.website.pojo.UserInfo;

public interface UserService {

    JSONObject getAllStudent(int pageNum, int pageSize);

    JSONObject getAllAdminInfo(int pageNum, int pageSize);

    int addUserInfo(UserInfo userInfo);

    JSONObject addAdminInfo(AdminInfo adminInfo);

    JSONObject updateAdminInfo(AdminInfo adminInfo);

    JSONObject delAdminInfo(AdminInfo adminInfo);

    JSONObject addStudent(Student student);

    JSONObject updateStudent(Student student);

    JSONObject changePassword(String userName, String oldPassword, String newPassword);
}