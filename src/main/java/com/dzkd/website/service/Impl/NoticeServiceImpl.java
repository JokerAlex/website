package com.dzkd.website.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.dzkd.website.dao.NoticeMapper;
import com.dzkd.website.pojo.Article;
import com.dzkd.website.pojo.Notice;
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
public class NoticeServiceImpl implements ArticleService<Article> {

    private static final Logger logger = LogManager.getLogger(NoticeServiceImpl.class);

    private NoticeMapper noticeMapper;

    @Autowired
    public NoticeServiceImpl(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    /**
     * 添加公告
     *
     * @param article
     * @return
     */
    @Override
    public R addArticle(Article article) {
        if (article == null) {
            return R.isFail(new Exception("添加公告失败"));
        }

        try {
            //重置信息
            article.setArticleId(null);
            article.setUpdateTime(new Date().toString());
            article.setPageViews(0);

            Notice notice = transform(article);
            int insert = noticeMapper.insertSelective(notice);
            logger.info("NoticeServiceImpl->insert:" + insert);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("添加公告失败"));
        }
    }

    /**
     * 更新公告
     *
     * @param article
     * @return
     */
    @Override
    public R updateArticle(Article article) {
        if (article == null || article.getArticleId() == null) {
            return R.isFail(new Exception("更新公告失败"));
        }

        try {
            //重置信息
            article.setUpdateTime(new Date().toString());
            article.setPageViews(null);//访问量不在此处更新

            Notice notice = transform(article);
            int update = noticeMapper.updateByPrimaryKeySelective(notice);
            logger.info("NoticeServiceImpl->update:" + update);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("更新公告失败"));
        }
    }

    /**
     * 删除公告
     *
     * @param articleId
     * @return
     */
    @Override
    public R delArticle(Integer articleId) {
        if (articleId == null) {
            return R.isFail(new Exception("删除公告失败"));
        }

        try {
            int del = noticeMapper.deleteByPrimaryKey(articleId);
            logger.info("NoticeServiceImpl->del:" + del);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("删除公告失败"));
        }
    }

    /**
     * 批量删除
     *
     * @param articles
     * @return
     */
    @Override
    public R delBatch(List<Article> articles) {
        if (articles.size() == 0) {
            return R.isFail(new Exception("删除公告失败"));
        }

        try {
            List<Notice> noticeList = new ArrayList<>();
            for (Article article : articles) {
                noticeList.add(transform(article));
            }

            int delBatch = noticeMapper.deleteBatch(noticeList);
            logger.info("NoticeServiceImpl->del:" + (delBatch == articles.size()));

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("删除公告失败"));
        }
    }

    /**
     * 查看公告
     *
     * @param articleId
     * @return
     */
    @Override
    public R searchArticle(Integer articleId) {
        if (articleId == null) {
            return R.isFail(new Exception("获取公告失败"));
        }

        try {
            Notice notice = noticeMapper.selectByPrimaryKey(articleId);
            if (notice == null) {
                return R.isFail(new Exception("获取公告失败"));
            } else {
                Article articleResult = new Article();
                articleResult.setArticleId(notice.getNoticeId());
                articleResult.setArticleContent(notice.getNoticeContent());
                articleResult.setUpdateTime(notice.getNoticeTime());
                articleResult.setPageViews(notice.getNoticeAcessNumber());
                articleResult.setAdminId(notice.getAdminAdminId());
                //更新访问量
                notice.setNoticeAcessNumber(notice.getNoticeAcessNumber() + 1);
                int updatePageViews = noticeMapper.updateByPrimaryKeySelective(notice);
                logger.info("NoticeServiceImpl->updatePageViews:" + updatePageViews);

                return R.isOk().data(articleResult);
            }
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("获取公告失败"));
        }
    }

    /**
     * 返回列表
     *
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
        List<Notice> noticeList = noticeMapper.selectAll();
        PageInfo<Notice> pageInfo = new PageInfo<>(noticeList);

        List<Article> articleList = new ArrayList<>();
        for (int i = 0; i < noticeList.size(); i++) {
            Article article = new Article();
            article.setArticleId(noticeList.get(i).getNoticeId());
            article.setAdminId(noticeList.get(i).getAdminAdminId());
            article.setUpdateTime(noticeList.get(i).getNoticeTime());
            articleList.add(i, article);
        }

        JSONObject data = new JSONObject();
        data.put("data", articleList);
        data.put("pageInfo", pageInfo);

        return R.isOk().data(data);
    }

    private Notice transform(Article article) {
        return new Notice(
                article.getArticleId(),
                article.getArticleContent(),
                article.getUpdateTime(),
                article.getPageViews(),
                article.getAdminId()
        );
    }
}
