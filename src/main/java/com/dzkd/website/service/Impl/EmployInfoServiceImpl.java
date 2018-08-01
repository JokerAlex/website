package com.dzkd.website.service.Impl;

import com.dzkd.website.dao.EmployInfoMapper;
import com.dzkd.website.pojo.Article;
import com.dzkd.website.pojo.EmployInfo;
import com.dzkd.website.pojo.R;
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
    public R addArticle(Article article) {
        if (article == null) {
            return R.isFail(new Exception("添加就业信息失败"));
        }

        try {
            //重置信息
            article.setArticleId(null);
            article.setUpdateTime(new Date().toString());
            article.setPageViews(0);

            EmployInfo employInfo = transform(article);
            int insert = employInfoMapper.insertSelective(employInfo);
            logger.info("EmployInfoServiceImpl->insert:" + insert);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("添加就业信息失败"));
        }
    }

    /**
     * 更新就业信息
     * @param article
     * @return
     */
    @Override
    public R updateArticle(Article article) {
        if (article == null || article.getArticleId() == null) {
            return R.isFail(new Exception("更新就业信息失败"));
        }

        try {
            //重置信息
            article.setUpdateTime(new Date().toString());
            article.setPageViews(null);//访问量不在此处更新

            EmployInfo employInfo = transform(article);
            int update = employInfoMapper.updateByPrimaryKeySelective(employInfo);
            logger.info("EmployInfoServiceImpl->update:" + update);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("更新就业信息失败"));
        }
    }

    /**
     * 删除就业信息
     * @param article
     * @return
     */
    @Override
    public R delArticle(Article article) {
        if (article == null || article.getArticleId() == null) {
            return R.isFail(new Exception("删除就业信息失败"));
        }

        try {
            int del = employInfoMapper.deleteByPrimaryKey(article.getArticleId());
            logger.info("EmployInfoServiceImpl->del:" + del);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("更新就业信息失败"));
        }
    }

    /**
     * 查看就业信息
     * @param article
     * @return
     */
    @Override
    public R searchArticle(Article article) {
        if (article == null || article.getArticleId() == null) {
            return R.isFail(new Exception("获取就业信息失败"));
        }

        try {
            EmployInfo employInfo = employInfoMapper.selectByPrimaryKey(article.getArticleId());
            if (employInfo == null) {
                return R.isFail(new Exception("获取就业信息失败"));
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

                return  R.isOk().data(articleResult);
            }
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("获取就业信息失败"));
        }
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
