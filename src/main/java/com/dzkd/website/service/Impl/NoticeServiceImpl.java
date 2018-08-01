package com.dzkd.website.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.dzkd.website.dao.NoticeMapper;
import com.dzkd.website.pojo.Notice;
import com.dzkd.website.service.ArticleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements ArticleService<Notice> {

    private static final Logger logger = LogManager.getLogger(NoticeServiceImpl.class);

    private NoticeMapper noticeMapper;

    @Autowired
    public NoticeServiceImpl(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    @Override
    public JSONObject addArticle(Notice notice) {
        return null;
    }

    @Override
    public JSONObject updateArticle(Notice notice) {
        return null;
    }

    @Override
    public JSONObject delArticle(Notice notice) {
        return null;
    }

    @Override
    public JSONObject searchArticle(Notice notice) {
        return null;
    }
}
