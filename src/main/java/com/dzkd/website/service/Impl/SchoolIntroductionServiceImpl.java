package com.dzkd.website.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.dzkd.website.dao.SchoolIntroductionMapper;
import com.dzkd.website.pojo.Article;
import com.dzkd.website.pojo.SchoolIntroduction;
import com.dzkd.website.service.ArticleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SchoolIntroductionServiceImpl implements ArticleService {

    private SchoolIntroductionMapper schoolIntroductionMapper;

    private static final Logger logger = LogManager.getLogger(SchoolIntroductionServiceImpl.class);

    @Autowired
    public SchoolIntroductionServiceImpl(SchoolIntroductionMapper schoolIntroductionMapper) {
        this.schoolIntroductionMapper = schoolIntroductionMapper;
    }

    @Override
    public JSONObject addArticle(Article article) {
        return null;
    }

    /**
     * 更新学校简介
     * @param article
     * @return
     */
    @Override
    public JSONObject updateArticle(Article article) {
        JSONObject result = new JSONObject();
        int resultCode;
        String msg;

        if (article == null) {
            resultCode = 0;
            msg = "学校简介更新失败";
        } else {
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

            int update = schoolIntroductionMapper.updateByPrimaryKeySelective(schoolIntroduction);
            logger.info("SchoolIntroductionServiceImpl->updateArticle->update:" + update);

            if (update == 1) {
                resultCode = 1;
                msg = "学校简介更新成功";
            } else {
                resultCode = 0;
                msg = "学校简介更新失败";
            }
        }

        result.put("resultCode", resultCode);
        result.put("msg", msg);

        return result;
    }

    @Override
    public JSONObject delArticle(Article article) {
        return null;
    }

    /**
     * 查看学校简介
     * @param article
     * @return
     */
    @Override
    public JSONObject searchArticle(Article article) {
        JSONObject result = new JSONObject();
        int resultCode;
        String msg;
        JSONObject data = new JSONObject();

        if (article == null) {
            article = new Article();
            article.setArticleId(1);
        }

        SchoolIntroduction schoolIntroduction = schoolIntroductionMapper.selectByPrimaryKey(article.getArticleId());
        if (schoolIntroduction == null) {
            resultCode = 0;
            msg = "学校简介获取失败";
        } else {
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

            resultCode = 1;
            msg = "学校简介获取成功";
            data.put("article", school);
        }

        result.put("resultCode", resultCode);
        result.put("msg", msg);
        result.put("data", data);

        return result;
    }
}
