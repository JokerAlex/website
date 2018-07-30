package com.dzkd.website.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.dzkd.website.dao.AdminInfoMapper;
import com.dzkd.website.dao.StudentMapper;
import com.dzkd.website.dao.UserInfoMapper;
import com.dzkd.website.pojo.AdminInfo;
import com.dzkd.website.pojo.Student;
import com.dzkd.website.pojo.UserInfo;
import com.dzkd.website.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public JSONObject login(String userName, String password) {
        JSONObject result = new JSONObject();
        int resultCode;
        String msg;
        JSONObject data = new JSONObject();

        UserInfo userInfo = userInfoMapper.selectByUserName(userName, password);
        if (userInfo == null){
            resultCode = 0;
            msg = "用户名或密码错误";
        } else {
            resultCode = 1;
            Student student = studentMapper.selectByUserName(userName, password);
            AdminInfo adminInfo = adminInfoMapper.selectByUserName(userName, password);

            if (adminInfo == null){
                data.put("isAdmin",0);
                data.put("stu", student);
            } else if (student == null){
                data.put("isAdmin",1);
                data.put("admin", adminInfo);
            } else {
                data.put("isAdmin",1);
                data.put("stu", student);
                data.put("admin", adminInfo);
            }
            msg = "登录成功";

            UserInfo userLoginTime = new UserInfo();
            userLoginTime.setUserInfoId(userInfo.getUserInfoId());
            userLoginTime.setRegTime(new Date().toString());
            userInfoMapper.updateByPrimaryKeySelective(userLoginTime);
        }

        result.put("resultCode", resultCode);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }
}
