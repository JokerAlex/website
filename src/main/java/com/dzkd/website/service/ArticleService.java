package com.dzkd.website.service;

import com.alibaba.fastjson.JSONObject;

public interface ArticleService<T> {

    JSONObject addArticle(T t);

    JSONObject updateArticle(T t);

    JSONObject delArticle(T t);

    JSONObject searchArticle(T t);
}
