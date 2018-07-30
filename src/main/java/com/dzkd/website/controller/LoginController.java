package com.dzkd.website.controller;


import com.alibaba.fastjson.JSONObject;
import com.dzkd.website.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController {


    private LoginService loginService;
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping("/login")
    public JSONObject login(String userName, String password, HttpServletRequest request){
        JSONObject jsonObject = loginService.login(userName, password);
        if ((int) jsonObject.get("resultCode") == 1) {
            JSONObject data =(JSONObject) jsonObject.get("data");
            HttpSession session = request.getSession(true);
            session.setAttribute(session.getId()+"userData",data);
        }
        return jsonObject;
    }

}
