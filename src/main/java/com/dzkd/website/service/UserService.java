package com.dzkd.website.service;

import com.dzkd.website.pojo.AdminInfo;
import com.dzkd.website.pojo.R;
import com.dzkd.website.pojo.Student;
import com.dzkd.website.pojo.UserInfo;

public interface UserService {

    R getAllStudent(int pageNum, int pageSize);

    R getAllAdminInfo(int pageNum, int pageSize);

    int addUserInfo(UserInfo userInfo);

    R addAdminInfo(AdminInfo adminInfo);

    R updateAdminInfo(AdminInfo adminInfo);

    R delAdminInfo(AdminInfo adminInfo);

    R addStudent(Student student);

    R updateStudent(Student student);

    R changePassword(String userName, String oldPassword, String newPassword);
}