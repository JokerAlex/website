package com.dzkd.website.service;

import com.alibaba.fastjson.JSONObject;

public interface LoginService {

    JSONObject login(String userName, String password);
}
