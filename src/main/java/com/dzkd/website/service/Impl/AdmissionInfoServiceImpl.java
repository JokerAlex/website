package com.dzkd.website.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.dzkd.website.dao.AdmissionInfoMapper;
import com.dzkd.website.pojo.AdmissionInfo;
import com.dzkd.website.pojo.Article;
import com.dzkd.website.pojo.R;
import com.dzkd.website.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public R addArticle(Article article) {
        if (article == null) {
            return R.isFail(new Exception("添加招生信息失败"));
        }

        try {
            //重置信息
            article.setArticleId(null);
            article.setUpdateTime(new Date().toString());
            article.setPageViews(0);

            AdmissionInfo admissionInfo = transform(article);
            int insert = admissionInfoMapper.insertSelective(admissionInfo);
            logger.info("AdmissionInfoServiceImpl->insert:" + insert);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("添加招生信息失败"));
        }
    }

    /**
     * 更新招生信息
     * @param article
     * @return
     */
    @Override
    public R updateArticle(Article article) {
        if (article == null || article.getArticleId() == null) {
            return R.isFail(new Exception("更新招生信息失败"));
        }

        try {
            //重置信息
            article.setUpdateTime(new Date().toString());
            article.setPageViews(null);//访问量不在此处更新

            AdmissionInfo admissionInfo = transform(article);
            int update = admissionInfoMapper.updateByPrimaryKeySelective(admissionInfo);
            logger.info("AdmissionInfoServiceImpl->update:" + update);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("更新招生信息失败"));
        }
    }

    /**
     * 删除招生信息
     * @param article
     * @return
     */
    @Override
    public R delArticle(Article article) {
        if (article == null || article.getArticleId() == null) {
            return R.isFail(new Exception("删除招生信息失败"));
        }

        try {
            int del = admissionInfoMapper.deleteByPrimaryKey(article.getArticleId());
            logger.info("AdmissionInfoServiceImpl->add->delDepartment:" + del);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("删除招生信息失败"));
        }
    }

    /**
     * 查看招生信息
     * @param article
     * @return
     */
    @Override
    public R searchArticle(Article article) {
        if (article == null || article.getArticleId() == null) {
            return R.isFail(new Exception("获取招生信息失败"));
        }

        try {
            AdmissionInfo admissionInfo = admissionInfoMapper.selectByPrimaryKey(article.getArticleId());
            if (admissionInfo == null) {
                return R.isFail(new Exception("获取招生信息失败"));
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

                return R.isOk().data(articleResult);
            }
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("获取招生信息失败"));
        }
    }

    @Override
    public R showAll(int pageNum, int pageSize) {
        if (pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }

        PageHelper.startPage(pageNum, pageSize);
        List<AdmissionInfo> admissionInfoList = admissionInfoMapper.selectAll();
        PageInfo<AdmissionInfo> pageInfo = new PageInfo<>(admissionInfoList);

        List<Article> articleList = new ArrayList<>();
        for (int i=0;i<admissionInfoList.size();i++) {
            Article article = new Article();
            article.setArticleId(admissionInfoList.get(i).getAdmInfoId());
            article.setUpdateTime(admissionInfoList.get(i).getAdmInfoTime());
            article.setArticleTitle(admissionInfoList.get(i).getAdmInfoTitle());
            article.setAdminId(admissionInfoList.get(i).getAdminAdminId());
            articleList.add(i,article);
        }

        JSONObject data = new JSONObject();
        data.put("data", articleList);
        data.put("pageInfo", pageInfo);

        return R.isOk().data(data);
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
