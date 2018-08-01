package com.dzkd.website.service;

import com.dzkd.website.pojo.R;

public interface ArticleService<T> {

    R addArticle(T t);

    R updateArticle(T t);

    R delArticle(T t);

    R searchArticle(T t);
}
