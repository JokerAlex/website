package com.dzkd.website.service;

import com.dzkd.website.pojo.R;

import java.util.List;

public interface ArticleService<T> {

    R addArticle(T t);

    R updateArticle(T t);

    R delArticle(Integer articleId);

    R delBatch(List<T> tList);

    R searchArticle(Integer articleId);

    R showAll(Integer pageNum, Integer pageSize);
}
