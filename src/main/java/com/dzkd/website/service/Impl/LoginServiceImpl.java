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

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private AdminInfoMapper adminInfoMapper;
    @Autowired
    private StudentMapper studentMapper;

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
            Student student = studentMapper.selectByForeignKey(userInfo.getUserInfoId());
            AdminInfo adminInfo = adminInfoMapper.selectByForeignKey(userInfo.getUserInfoId());

            data.put("userInfo", userInfo);
            data.put("stu", student);
            data.put("admin", adminInfo);
            msg = "登录成功";
        }

        result.put("resultCode", resultCode);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }
}
