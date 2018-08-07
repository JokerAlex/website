package com.dzkd.website.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.dzkd.website.dao.DepartmentIntroductionMapper;
import com.dzkd.website.dao.ProfessionalIntroductionMapper;
import com.dzkd.website.pojo.Article;
import com.dzkd.website.pojo.DepartmentIntroduction;
import com.dzkd.website.pojo.ProfessionalIntroduction;
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
public class DepartmentIntroductionServiceImpl implements ArticleService<Article> {

    private static final Logger logger = LogManager.getLogger(DepartmentIntroductionServiceImpl.class);

    private DepartmentIntroductionMapper departmentIntroductionMapper;
    private ProfessionalIntroductionMapper professionalIntroductionMapper;

    @Autowired
    public DepartmentIntroductionServiceImpl(DepartmentIntroductionMapper departmentIntroductionMapper, ProfessionalIntroductionMapper professionalIntroductionMapper) {
        this.departmentIntroductionMapper = departmentIntroductionMapper;
        this.professionalIntroductionMapper = professionalIntroductionMapper;
    }


    /**
     * 添加院系简介
     * @param article
     * @return
     */
    @Override
    public R addArticle(Article article) {
        if (article == null) {
            return R.isFail(new Exception("添加院系简介失败"));
        }

        try {
            //重置信息
            article.setArticleId(null);
            article.setUpdateTime(new Date().toString());
            article.setPageViews(0);

            DepartmentIntroduction departmentIntroduction = transform(article);
            int insertDepartment = departmentIntroductionMapper.insertSelective(departmentIntroduction);
            logger.info("DepartmentIntroductionServiceImpl->insertDepartment:" + insertDepartment);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("添加院系简介失败"));
        }
    }

    /**
     * 更新院系简介
     * @param article
     * @return
     */
    @Override
    public R updateArticle(Article article) {
        if (article == null || article.getArticleId() == null) {
            return R.isFail(new Exception("更新院系简介失败"));
        }

        try {
            //重置信息
            article.setUpdateTime(new Date().toString());
            article.setPageViews(null);//访问量不在此处更新

            DepartmentIntroduction departmentIntroduction = transform(article);
            int updateDepartment = departmentIntroductionMapper.updateByPrimaryKeySelective(departmentIntroduction);
            logger.info("DepartmentIntroductionServiceImpl->updateDepartment:" + updateDepartment);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("更新院系简介失败"));
        }
    }

    /**
     * 删除院系简介
     * @param article
     * @return
     */
    @Override
    public R delArticle(Article article) {
        if (article == null || article.getArticleId() == null) {
            return R.isFail(new Exception("删除院系简介失败"));
        }

        try {
            //删除院系下的学院
            int delProfession = professionalIntroductionMapper.deleteByDepartmentId(article.getArticleId());
            logger.info("DepartmentIntroductionServiceImpl->delProfession:" + delProfession);

            int delDepartment = departmentIntroductionMapper.deleteByPrimaryKey(article.getArticleId());
            logger.info("DepartmentIntroductionServiceImpl->delDepartment:" + delDepartment);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("删除院系简介失败"));
        }
    }

    /**
     * 查看院系简介
     * @param article
     * @return
     */
    @Override
    public R searchArticle(Article article) {
        if (article == null || article.getArticleId() == null) {
            return R.isFail(new Exception("获取院系简介信息失败"));
        }

        try {
            DepartmentIntroduction departmentIntroduction = departmentIntroductionMapper.selectByPrimaryKey(article.getArticleId());
            if (departmentIntroduction == null) {
                return R.isFail(new Exception("获取院系简介信息失败"));
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
                logger.info("DepartmentIntroductionServiceImpl->updatePageViews:" + updatePageViews);

                return R.isOk().data(articleResult);
            }
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("获取院系简介信息失败"));
        }
    }

    /**
     * 返回列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public R showAll(Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageSize == null) {
            return R.isFail(new Exception("参数错误"));
        }

        if (pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }

        PageHelper.startPage(pageNum, pageSize);
        List<DepartmentIntroduction> departmentIntroductionList = departmentIntroductionMapper.selectAll();
        PageInfo<DepartmentIntroduction> pageInfo = new PageInfo<>(departmentIntroductionList);
        List<Article> articleList = new ArrayList<>();
        for (int i=0;i<departmentIntroductionList.size();i++) {
            Article article = new Article();
            article.setArticleId(departmentIntroductionList.get(i).getDepartmentId());
            article.setUpdateTime(departmentIntroductionList.get(i).getDepartmentUpdateTime());
            article.setArticleTitle(departmentIntroductionList.get(i).getDepartmentTitle());
            article.setAdminId(departmentIntroductionList.get(i).getAdminAdminId());
            articleList.add(i,article);
        }

        JSONObject data = new JSONObject();
        data.put("data", articleList);
        data.put("pageInfo", pageInfo);

        return R.isOk().data(data);
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
