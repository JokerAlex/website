package com.dzkd.website.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.dzkd.website.dao.AdminInfoMapper;
import com.dzkd.website.dao.StudentMapper;
import com.dzkd.website.dao.UserInfoMapper;
import com.dzkd.website.pojo.AdminInfo;
import com.dzkd.website.pojo.R;
import com.dzkd.website.pojo.Student;
import com.dzkd.website.pojo.UserInfo;
import com.dzkd.website.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {

    private UserInfoMapper userInfoMapper;
    private AdminInfoMapper adminInfoMapper;
    private StudentMapper studentMapper;

    @Autowired
    public LoginServiceImpl(UserInfoMapper userInfoMapper, AdminInfoMapper adminInfoMapper, StudentMapper studentMapper) {
        this.userInfoMapper = userInfoMapper;
        this.adminInfoMapper = adminInfoMapper;
        this.studentMapper = studentMapper;
    }

    @Override
    public R login(String userName, String password) {

        UserInfo userInfo = userInfoMapper.selectByUserName(userName, password);
        Student studentInfo = studentMapper.selectByStuId(userName, password);
        if (userInfo == null && studentInfo == null) {
            return R.isFail(new Exception("用户名或密码错误"));
        } else {
            UserInfo userLoginTime = new UserInfo();
            if (studentInfo != null){
                userLoginTime.setUserInfoId(studentInfo.getUserUserInfoId());
            } else {
                userLoginTime.setUserInfoId(userInfo.getUserInfoId());
            }
            userLoginTime.setRegTime(new Date().toString());
            userInfoMapper.updateByPrimaryKeySelective(userLoginTime);

            Student student = studentMapper.selectByUserName(userName, password) == null ?
                    studentMapper.selectByStuId(userName, password) :
                    studentMapper.selectByUserName(userName, password);
            AdminInfo adminInfo = adminInfoMapper.selectByUserName(userName, password);

            JSONObject data = new JSONObject();

            if (student != null) {
                data.put("isAdmin", 0);
                data.put("stu", student);
                return R.isOk().data(data);
            } else {
                data.put("isAdmin", 1);
                data.put("admin", adminInfo);
                return R.isOk().data(data);
            }
        }
    }
}
