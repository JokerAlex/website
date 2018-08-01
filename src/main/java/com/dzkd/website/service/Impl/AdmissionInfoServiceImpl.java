package com.dzkd.website.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.dzkd.website.dao.AdmissionInfoMapper;
import com.dzkd.website.pojo.AdmissionInfo;
import com.dzkd.website.pojo.Article;
import com.dzkd.website.service.ArticleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdmissionInfoServiceImpl implements ArticleService<Article> {

    private static final Logger logger = LogManager.getLogger(AdmissionInfoServiceImpl.class);

    private AdmissionInfoMapper admissionInfoMapper;

    @Autowired
    public AdmissionInfoServiceImpl(AdmissionInfoMapper admissionInfoMapper) {
        this.admissionInfoMapper = admissionInfoMapper;
    }

    /**
     * 添加招生信息
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
            msg = "添加招生信息失败";
        } else {
            //重置信息
            article.setArticleId(null);
            article.setUpdateTime(new Date().toString());
            article.setPageViews(0);

            AdmissionInfo admissionInfo = transform(article);
            int insert = admissionInfoMapper.insertSelective(admissionInfo);
            logger.info("AdmissionInfoServiceImpl->insert:" + insert);

            if (insert == 1) {
                resultCode = 1;
                msg = "添加招生信息成功";
            } else {
                resultCode = 0;
                msg = "添加招生信息失败";
            }
        }

        result.put("resultCode", resultCode);
        result.put("msg", msg);

        return result;
    }

    /**
     * 更新招生信息
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
            msg = "更新招生信息失败";
        } else {
            //重置信息
            article.setUpdateTime(new Date().toString());
            article.setPageViews(null);//访问量不在此处更新

            AdmissionInfo admissionInfo = transform(article);
            int update = admissionInfoMapper.updateByPrimaryKeySelective(admissionInfo);
            logger.info("AdmissionInfoServiceImpl->update:" + update);

            if (update == 1) {
                resultCode = 1;
                msg = "更新招生信息成功";
            } else {
                resultCode = 0;
                msg = "更新招生信息失败";
            }

        }

        result.put("resultCode", resultCode);
        result.put("msg", msg);

        return result;
    }

    /**
     * 删除招生信息
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
            msg = "删除招生信息失败";
        } else {
            int del = admissionInfoMapper.deleteByPrimaryKey(article.getArticleId());
            logger.info("AdmissionInfoServiceImpl->add->delDepartment:" + del);

            if (del == 1) {
                resultCode = 1;
                msg = "删除招生信息成功";
            } else {
                resultCode = 0;
                msg = "删除招生信息失败";
            }
        }

        result.put("resultCode", resultCode);
        result.put("msg", msg);

        return result;
    }

    /**
     * 查看招生信息
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
            msg = "获取招生信息失败";
        } else {
            AdmissionInfo admissionInfo = admissionInfoMapper.selectByPrimaryKey(article.getArticleId());
            if (admissionInfo == null) {
                resultCode = 0;
                msg = "获取招生信息失败";
            } else {
                Article articleResult = new Article(
                        admissionInfo.getAdmInfoId(),
                        admissionInfo.getAdminAdminId(),
                        admissionInfo.getAdmAcessNumber() + 1,
                        admissionInfo.getAdmInfoTime(),
                        admissionInfo.getAdmInfoTitle(),
                        admissionInfo.getAdmInfoContent()
                );
                //更新访问量
                admissionInfo.setAdmAcessNumber(admissionInfo.getAdmAcessNumber() + 1);
                int updatePageViews = admissionInfoMapper.updateByPrimaryKeySelective(admissionInfo);
                logger.info("AdmissionInfoServiceImpl->updatePageViews:" + updatePageViews);

                resultCode = 1;
                msg = "获取招生信息成功";
                data.put("article", articleResult);
            }
        }

        result.put("resultCode", resultCode);
        result.put("msg", msg);
        result.put("data", data);

        return result;
    }

    private AdmissionInfo transform(Article article) {
        return new AdmissionInfo(
                article.getArticleId(),
                article.getArticleTitle(),
                article.getArticleContent(),
                article.getUpdateTime(),
                article.getPageViews(),
                article.getAdminId()
        );
    }
}
