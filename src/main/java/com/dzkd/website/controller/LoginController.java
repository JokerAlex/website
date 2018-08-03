package com.dzkd.website.controller;


import com.alibaba.fastjson.JSONObject;
import com.dzkd.website.pojo.R;
import com.dzkd.website.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class LoginController {


    private LoginService loginService;
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(name = "/login", method = RequestMethod.POST)
    public R login(@RequestBody Map<String, String> map, HttpServletRequest request){

        R r = loginService.login(map.get("userName"), map.get("password"));

        if (r.getStatus() == 0) {
            JSONObject data =(JSONObject) r.getData();
            HttpSession session = request.getSession(true);
            session.setAttribute(session.getId()+"userData",data);
        }
        return r;
    }

    @RequestMapping(name = "/logout", method = RequestMethod.DELETE)
    public R logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null) {
            return R.isFail();
        }
        session.removeAttribute(session.getId()+"userData");
        return R.isOk();
    }

}
