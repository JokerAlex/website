package com.dzkd.website.service;

import com.dzkd.website.pojo.AdminInfo;
import com.dzkd.website.pojo.R;
import com.dzkd.website.pojo.Student;

public interface UserService {

    R getAllAdminInfo(int pageNum, int pageSize);

    R getAdminInfo(Integer adminId);

    R addAdminInfo(AdminInfo adminInfo);

    R updateAdminInfo(AdminInfo adminInfo);

    R delAdminInfo(AdminInfo adminInfo);

    R getAllStudent(int pageNum, int pageSize);

    R getStudent(String stuId);

    R addStudent(Student student);

    R updateStudent(Student student);

    R changePassword(String userName, String oldPassword, String newPassword);
}