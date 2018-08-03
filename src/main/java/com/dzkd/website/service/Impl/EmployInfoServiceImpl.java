package com.dzkd.website.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.dzkd.website.dao.EmployInfoMapper;
import com.dzkd.website.pojo.Article;
import com.dzkd.website.pojo.EmployInfo;
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
        List<EmployInfo> employInfoList = employInfoMapper.selectAll();
        PageInfo<EmployInfo> pageInfo = new PageInfo<>(employInfoList);

        List<Article> articleList = new ArrayList<>();
        for (int i=0;i<employInfoList.size();i++) {
            Article article = new Article();
            article.setArticleId(employInfoList.get(i).getEmpInfoId());
            article.setUpdateTime(employInfoList.get(i).getEmpInfoTime());
            article.setArticleTitle(employInfoList.get(i).getEmpInfoTitle());
            article.setAdminId(employInfoList.get(i).getAdminAdminId());
            articleList.add(i,article);
        }


        JSONObject data = new JSONObject();
        data.put("data", articleList);
        data.put("pageInfo", pageInfo);

        return R.isOk().data(data);
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
