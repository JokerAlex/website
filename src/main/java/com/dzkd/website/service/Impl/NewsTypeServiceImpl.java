package com.dzkd.website.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.dzkd.website.dao.NewsTypeMapper;
import com.dzkd.website.pojo.NewsType;
import com.dzkd.website.pojo.R;
import com.dzkd.website.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsTypeServiceImpl implements ArticleService<NewsType> {
    private NewsTypeMapper newsTypeMapper;

    private static final Logger logger = LogManager.getLogger(NewsServiceImpl.class);

    @Autowired
    public NewsTypeServiceImpl(NewsTypeMapper newsTypeMapper) {
        this.newsTypeMapper = newsTypeMapper;
    }

    /**
     * 添加新闻分类
     * @param newsType
     * @return
     */
    @Override
    public R addArticle(NewsType newsType) {
        if (newsType == null) {
            return R.isFail(new Exception("添加新闻分类失败"));
        }

        try {
            newsType.setTypeId(null);
            int insert = newsTypeMapper.insertSelective(newsType);
            logger.info("NewsTypeServiceImpl->insert:" + insert);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("添加新闻分类失败"));
        }
    }

    /**
     * "更新新闻分类
     * @param newsType
     * @return
     */
    @Override
    public R updateArticle(NewsType newsType) {
        if (newsType == null || newsType.getTypeId() == null) {
            return R.isFail(new Exception("更新新闻分类失败"));
        }

        try {
            int update = newsTypeMapper.updateByPrimaryKeySelective(newsType);
            logger.info("NewsTypeServiceImpl->update:" + update);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("更新新闻分类失败"));
        }
    }

    /**
     * 删除新闻分类
     * @param newsType
     * @return
     */
    @Override
    public R delArticle(NewsType newsType) {
        if (newsType == null || newsType.getTypeId() == null) {
            return R.isFail(new Exception("删除新闻分类失败"));
        }

        try {
            int del = newsTypeMapper.deleteByPrimaryKey(newsType.getTypeId());
            logger.info("NewsTypeServiceImpl->del:" + del);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("删除新闻分类失败"));
        }
    }

    @Override
    public R searchArticle(NewsType newsType) {
        return null;
    }

    /**
     * 获取所有信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public R showAll(int pageNum, int pageSize) {
        if (pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }

        PageHelper.startPage(pageNum, pageSize);
        List<NewsType> newsTypeList = newsTypeMapper.selectAll();
        PageInfo<NewsType> pageInfo = new PageInfo<>(newsTypeList);

        JSONObject data = new JSONObject();
        data.put("data", data);
        data.put("pageInfo", pageInfo);

        return R.isOk().data(data);
    }
}
