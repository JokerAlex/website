package com.dzkd.website.interceptor;


import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class StuInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        JSONObject userData =(JSONObject) session.getAttribute(session.getId()+"userData");

        //是否登录
        if (userData == null) {
            throw new Exception("用户未登录");
        }

        return true;
    }
}
