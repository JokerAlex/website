package com.dzkd.website.service.Impl;

import com.dzkd.website.dao.NewsMapper;
import com.dzkd.website.dao.NewsTypeMapper;
import com.dzkd.website.pojo.News;
import com.dzkd.website.pojo.R;
import com.dzkd.website.service.ArticleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements ArticleService<News> {

    private static final Logger logger = LogManager.getLogger(NewsServiceImpl.class);

    private NewsMapper newsMapper;
    private NewsTypeMapper newsTypeMapper;

    @Autowired
    public NewsServiceImpl(NewsMapper newsMapper, NewsTypeMapper newsTypeMapper) {
        this.newsMapper = newsMapper;
        this.newsTypeMapper = newsTypeMapper;
    }

    @Override
    public R addArticle(News news) {
        return null;
    }

    @Override
    public R updateArticle(News news) {
        return null;
    }

    @Override
    public R delArticle(News news) {
        return null;
    }

    @Override
    public R searchArticle(News news) {
        return null;
    }
}
