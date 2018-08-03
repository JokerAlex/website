package com.dzkd.website.service.Impl;

import com.dzkd.website.dao.SchoolIntroductionMapper;
import com.dzkd.website.pojo.Article;
import com.dzkd.website.pojo.R;
import com.dzkd.website.pojo.SchoolIntroduction;
import com.dzkd.website.service.ArticleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SchoolIntroductionServiceImpl implements ArticleService<Article> {

    private SchoolIntroductionMapper schoolIntroductionMapper;

    private static final Logger logger = LogManager.getLogger(SchoolIntroductionServiceImpl.class);

    @Autowired
    public SchoolIntroductionServiceImpl(SchoolIntroductionMapper schoolIntroductionMapper) {
        this.schoolIntroductionMapper = schoolIntroductionMapper;
    }

    @Override
    public R addArticle(Article article) {
        return null;
    }

    /**
     * 更新学校简介
     *
     * @param article
     * @return
     */
    @Override
    public R updateArticle(Article article) {
        if (article == null) {
            return R.isFail(new Exception("学校简介更新失败"));
        }

        try {
            if (article.getArticleId() != 1) {
                article.setArticleId(1);
            }
            article.setPageViews(null);//访问量不能在这里更新
            article.setUpdateTime(new Date().toString());
            SchoolIntroduction schoolIntroduction = new SchoolIntroduction(
                    article.getArticleId(),
                    article.getArticleTitle(),
                    article.getUpdateTime(),
                    article.getArticleContent(),
                    article.getPageViews(),
                    article.getAdminId()
            );
            //更新学校简介信息
            int update = schoolIntroductionMapper.updateByPrimaryKeySelective(schoolIntroduction);
            logger.info("SchoolIntroductionServiceImpl->updateArticle->update:" + update);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("更新学校简介失败"));
        }
    }

    @Override
    public R delArticle(Article article) {
        return null;
    }

    /**
     * 查看学校简介
     *
     * @param article
     * @return
     */
    @Override
    public R searchArticle(Article article) {
        if (article == null) {
            return R.isFail(new Exception("获取学校简介失败"));
        }
        try {
            article.setArticleId(1);
            SchoolIntroduction schoolIntroduction = schoolIntroductionMapper.selectByPrimaryKey(article.getArticleId());
            Article school = new Article(
                    schoolIntroduction.getSchoolId(),
                    schoolIntroduction.getAdminAdminId(),
                    schoolIntroduction.getSchoolAcessNumber() + 1,
                    schoolIntroduction.getSchoolUpdateTime(),
                    schoolIntroduction.getSchoolTitle(),
                    schoolIntroduction.getSchoolContent()
            );
            //更新访问量
            schoolIntroduction.setSchoolAcessNumber(schoolIntroduction.getSchoolAcessNumber() + 1);
            int updatePageViews = schoolIntroductionMapper.updateByPrimaryKeySelective(schoolIntroduction);
            logger.info("SchoolIntroductionServiceImpl->updateArticle->updatePageViews:" + updatePageViews);

            return R.isOk().data(school);
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("获取学校简介失败"));
        }
    }

    @Override
    public R showAll(Integer pageNum, Integer pageSize) {
        return null;
    }
}
