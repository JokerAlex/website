package com.dzkd.website.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.dzkd.website.dao.EmployInfoMapper;
import com.dzkd.website.pojo.Article;
import com.dzkd.website.pojo.EmployInfo;
import com.dzkd.website.service.ArticleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmployInfoServiceImpl implements ArticleService<Article> {

    private static final Logger logger = LogManager.getLogger(EmployInfoServiceImpl.class);

    private EmployInfoMapper employInfoMapper;

    @Autowired
    public EmployInfoServiceImpl(EmployInfoMapper employInfoMapper) {
        this.employInfoMapper = employInfoMapper;
    }

    /**
     * 添加就业信息
     * @param article
     * @return
     */
    @Override
    public JSONObject addArticle(Article article) {
        JSONObject result = new JSONObject();
        int resultCode;
        String msg;

        if (article == null) {
            resultCode = 0;
            msg = "添加就业信息失败";
        } else {
            //重置信息
            article.setArticleId(null);
            article.setUpdateTime(new Date().toString());
            article.setPageViews(0);

            EmployInfo employInfo = transform(article);
            int insert = employInfoMapper.insertSelective(employInfo);
            logger.info("EmployInfoServiceImpl->insert:" + insert);

            if (insert == 1) {
                resultCode = 1;
                msg = "添加就业信息成功";
            } else {
                resultCode = 0;
                msg = "添加就业信息失败";
            }
        }

        result.put("resultCode", resultCode);
        result.put("msg", msg);

        return result;
    }

    /**
     * 更新就业信息
     * @param article
     * @return
     */
    @Override
    public JSONObject updateArticle(Article article) {
        JSONObject result = new JSONObject();
        int resultCode;
        String msg;

        if (article == null || article.getArticleId() == null) {
            resultCode = 0;
            msg = "更新就业信息失败";
        } else {
            //重置信息
            article.setUpdateTime(new Date().toString());
            article.setPageViews(null);//访问量不在此处更新

            EmployInfo employInfo = transform(article);
            int update = employInfoMapper.updateByPrimaryKeySelective(employInfo);
            logger.info("EmployInfoServiceImpl->update:" + update);

            if (update == 1) {
                resultCode = 1;
                msg = "更新就业信息成功";
            } else {
                resultCode = 0;
                msg = "更新就业信息失败";
            }

        }

        result.put("resultCode", resultCode);
        result.put("msg", msg);

        return result;
    }

    /**
     * 删除就业信息
     * @param article
     * @return
     */
    @Override
    public JSONObject delArticle(Article article) {
        JSONObject result = new JSONObject();
        int resultCode;
        String msg;

        if (article == null || article.getArticleId() == null) {
            resultCode = 0;
            msg = "删除就业信息失败";
        } else {
            int del = employInfoMapper.deleteByPrimaryKey(article.getArticleId());
            logger.info("EmployInfoServiceImpl->del:" + del);

            if (del == 1) {
                resultCode = 1;
                msg = "删除就业信息成功";
            } else {
                resultCode = 0;
                msg = "删除就业信息失败";
            }
        }

        result.put("resultCode", resultCode);
        result.put("msg", msg);

        return result;
    }

    /**
     * 查看就业信息
     * @param article
     * @return
     */
    @Override
    public JSONObject searchArticle(Article article) {
        JSONObject result = new JSONObject();
        int resultCode;
        String msg;
        JSONObject data = new JSONObject();

        if (article == null || article.getArticleId() == null) {
            resultCode = 0;
            msg = "获取就业信息失败";
        } else {
            EmployInfo employInfo = employInfoMapper.selectByPrimaryKey(article.getArticleId());
            if (employInfo == null) {
                resultCode = 0;
                msg = "获取就业信息失败";
            } else {
                Article articleResult = new Article(
                        employInfo.getEmpInfoId(),
                        employInfo.getAdminAdminId(),
                        employInfo.getEmpAcessNumber()+ 1,
                        employInfo.getEmpInfoTime(),
                        employInfo.getEmpInfoTitle(),
                        employInfo.getEmpInfoContent()
                );
                //更新访问量
                employInfo.setEmpAcessNumber(employInfo.getEmpAcessNumber() + 1);
                int updatePageViews = employInfoMapper.updateByPrimaryKeySelective(employInfo);
                logger.info("EmployInfoServiceImpl->updatePageViews:" + updatePageViews);

                resultCode = 1;
                msg = "获取就业信息成功";
                data.put("article", articleResult);
            }
        }

        result.put("resultCode", resultCode);
        result.put("msg", msg);
        result.put("data", data);

        return result;
    }

    private EmployInfo transform(Article article) {
        return new EmployInfo(
                article.getArticleId(),
                article.getArticleTitle(),
                article.getArticleContent(),
                article.getUpdateTime(),
                article.getPageViews(),
                article.getAdminId()
        );
    }
}
