package com.dzkd.website.service;

import com.alibaba.fastjson.JSONObject;
import com.dzkd.website.pojo.Article;

public interface ArticleService {

    JSONObject addArticle(Article article);

    JSONObject updateArticle(Article article);

    JSONObject delArticle(Article article);

    JSONObject searchArticle(Article article);
}
