package com.dzkd.website.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.dzkd.website.dao.DepartmentIntroductionMapper;
import com.dzkd.website.pojo.Article;
import com.dzkd.website.pojo.DepartmentIntroduction;
import com.dzkd.website.service.ArticleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DepartmentIntroductionServiceImpl implements ArticleService<Article> {

    private DepartmentIntroductionMapper departmentIntroductionMapper;

    private static final Logger logger = LogManager.getLogger(DepartmentIntroductionServiceImpl.class);

    @Autowired
    public DepartmentIntroductionServiceImpl(DepartmentIntroductionMapper departmentIntroductionMapper) {
        this.departmentIntroductionMapper = departmentIntroductionMapper;
    }

    /**
     * 添加院系简介
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
            msg = "添加院系简介失败";
        } else {
            //重置信息
            article.setArticleId(null);
            article.setUpdateTime(new Date().toString());
            article.setPageViews(0);

            DepartmentIntroduction departmentIntroduction = transform(article);
            int insertDepartment = departmentIntroductionMapper.insertSelective(departmentIntroduction);
            logger.info("DepartmentIntroductionServiceImpl->add->insertDepartment:" + insertDepartment);

            if (insertDepartment == 1) {
                resultCode = 1;
                msg = "添加院系简介成功";
            } else {
                resultCode = 0;
                msg = "添加院系简介失败";
            }
        }

        result.put("resultCode", resultCode);
        result.put("msg", msg);

        return result;
    }

    /**
     * 更新院系简介
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
            msg = "更新院系简介失败";
        } else {
            //重置信息
            article.setUpdateTime(new Date().toString());
            article.setPageViews(null);//访问量不在此处更新

            DepartmentIntroduction departmentIntroduction = transform(article);
            int updateDepartment = departmentIntroductionMapper.updateByPrimaryKeySelective(departmentIntroduction);
            logger.info("DepartmentIntroductionServiceImpl->add->updateDepartment:" + updateDepartment);

            if (updateDepartment == 1) {
                resultCode = 1;
                msg = "更新院系简介成功";
            } else {
                resultCode = 0;
                msg = "更新院系简介失败";
            }

        }

        result.put("resultCode", resultCode);
        result.put("msg", msg);

        return result;
    }

    /**
     * 删除院系简介
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
            msg = "删除院系简介失败";
        } else {
            int delDepartment = departmentIntroductionMapper.deleteByPrimaryKey(article.getArticleId());
            logger.info("DepartmentIntroductionServiceImpl->add->delDepartment:" + delDepartment);

            if (delDepartment == 1) {
                resultCode = 1;
                msg = "删除院系简介成功";
            } else {
                resultCode = 0;
                msg = "删除院系简介失败";
            }
        }

        result.put("resultCode", resultCode);
        result.put("msg", msg);

        return result;
    }

    /**
     * 查看院系简介
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
            msg = "获取院系简介信息失败";
        } else {
            DepartmentIntroduction departmentIntroduction = departmentIntroductionMapper.selectByPrimaryKey(article.getArticleId());
            if (departmentIntroduction == null) {
                resultCode = 0;
                msg = "获取院系简介信息失败";
            } else {
                Article articleResult = new Article(
                        departmentIntroduction.getDepartmentId(),
                        departmentIntroduction.getAdminAdminId(),
                        departmentIntroduction.getDepartmentAcessNumber() + 1,
                        departmentIntroduction.getDepartmentUpdateTime(),
                        departmentIntroduction.getDepartmentTitle(),
                        departmentIntroduction.getDepartmentContent()
                );
                //更新访问量
                departmentIntroduction.setDepartmentAcessNumber(departmentIntroduction.getDepartmentAcessNumber() + 1);
                int updatePageViews = departmentIntroductionMapper.updateByPrimaryKeySelective(departmentIntroduction);
                logger.info("DepartmentIntroductionServiceImpl->add->updatePageViews:" + updatePageViews);

                resultCode = 1;
                msg = "获取院系简介信息成功";
                data.put("article", articleResult);
            }
        }

        result.put("resultCode", resultCode);
        result.put("msg", msg);
        result.put("data", data);

        return result;
    }

    private DepartmentIntroduction transform(Article article) {
        return new DepartmentIntroduction(
                article.getArticleId(),
                article.getArticleTitle(),
                article.getUpdateTime(),
                article.getArticleContent(),
                article.getPageViews(),
                article.getAdminId()
        );
    }
}
