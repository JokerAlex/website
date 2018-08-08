package com.dzkd.website.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.dzkd.website.dao.NewsMapper;
import com.dzkd.website.dao.PictureMapper;
import com.dzkd.website.pojo.News;
import com.dzkd.website.pojo.Picture;
import com.dzkd.website.pojo.R;
import com.dzkd.website.service.ArticleService;
import com.dzkd.website.util.FileUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NewsServiceImpl implements ArticleService<News> {

    private static final Logger logger = LogManager.getLogger(NewsServiceImpl.class);

    private NewsMapper newsMapper;
    private PictureMapper pictureMapper;

    @Autowired
    public NewsServiceImpl(NewsMapper newsMapper, PictureMapper pictureMapper) {
        this.newsMapper = newsMapper;
        this.pictureMapper = pictureMapper;
    }

    /**
     * 添加新闻
     *
     * @param news
     * @return
     */
    @Override
    public R addArticle(News news) {
        if (news == null || news.getNewsTypeTypeId() == null) {
            return R.isFail(new Exception("添加新闻失败"));
        }

        try {
            //重置信息
            news.setNewsId(null);
            news.setNewsTime(new Date().toString());
            news.setNewsAcessNumber(0);

            int insert = newsMapper.insertSelective(news);
            logger.info("NewsServiceImpl->insert:" + insert);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("添加新闻失败"));
        }
    }

    /**
     * 更新新闻
     *
     * @param news
     * @return
     */
    @Override
    public R updateArticle(News news) {
        if (news == null) {
            return R.isFail(new Exception("更新新闻失败"));
        }

        try {
            //重置信息
            news.setNewsAcessNumber(null);//访问量不在此处更新
            news.setNewsTime(new Date().toString());

            int update = newsMapper.updateByPrimaryKeySelective(news);
            logger.info("NewsServiceImpl->update:" + update);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("更新新闻失败"));
        }
    }

    /**
     * 删除
     *
     * @param newsId
     * @return
     */
    @Override
    public R delArticle(Integer newsId) {
        if (newsId == null) {
            return R.isFail(new Exception("删除新闻失败"));
        }

        try {
            int del = newsMapper.deleteByPrimaryKey(newsId);
            logger.info("NewsServiceImpl->del:" + del);

            //删除新闻图片
            List<Picture> pictureList = pictureMapper.selectByArticle(0, newsId);
            if (pictureList.size() != 0) {
                FileUtil.delFile(pictureList, 1);

                int delPictures = pictureMapper.deleteBatch(pictureList);
                logger.info("NewsServiceImpl->delPictures:" + delPictures);
            }

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("删除新闻失败"));
        }
    }

    /**
     * 批量删除
     *
     * @param newsList
     * @return
     */
    @Override
    public R delBatch(List<News> newsList) {
        if (newsList.size() == 0) {
            return R.isFail(new Exception("删除新闻失败"));
        }

        try {
            //删除新闻图片
            for (News news : newsList) {
                List<Picture> pictureList = pictureMapper.selectByArticle(0, news.getNewsId());
                if (pictureList.size() != 0) {
                    FileUtil.delFile(pictureList, 1);

                    int delPictures = pictureMapper.deleteBatch(pictureList);
                    logger.info("NewsServiceImpl->delBatch->delPictures:" + delPictures);
                }
            }
            int delBatch = newsMapper.deleteBatch(newsList);
            logger.info("NewsServiceImpl->delBatch:" + (delBatch == newsList.size()));

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("删除新闻失败"));
        }
    }

    /**
     * 查看新闻
     *
     * @param newsId
     * @return
     */
    @Override
    public R searchArticle(Integer newsId) {
        if (newsId == null) {
            return R.isFail(new Exception("获取新闻失败"));
        }

        try {
            News newsResult = newsMapper.selectByPrimaryKey(newsId);
            if (newsResult == null) {
                return R.isFail(new Exception("获取新闻失败"));
            }
            //更新访问量
            newsResult.setNewsAcessNumber(newsResult.getNewsAcessNumber() + 1);
            int updatePageViews = newsMapper.updateByPrimaryKeySelective(newsResult);
            logger.info("NewsServiceImpl->updatePageViews:" + updatePageViews);

            //获取图片信息
            List<Picture> pictureList = pictureMapper.selectByArticle(0, newsId);
            JSONObject data = new JSONObject();
            data.put("newResult", newsResult);
            data.put("pictures", pictureList);

            return R.isOk().data(data);
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("获取新闻失败"));
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
    public R showAll(Integer pageNum, Integer pageSize, Object newsTypeId) {
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
        List<News> newsList = newsMapper.selectAll((Integer) newsTypeId);
        PageInfo<News> pageInfo = new PageInfo<>(newsList);

        JSONObject data = new JSONObject();
        data.put("data", newsList);
        data.put("pageInfo", pageInfo);

        return R.isOk().data(data);
    }
}
