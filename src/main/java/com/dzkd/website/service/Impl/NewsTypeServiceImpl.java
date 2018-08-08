package com.dzkd.website.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.dzkd.website.dao.NewsMapper;
import com.dzkd.website.dao.NewsTypeMapper;
import com.dzkd.website.dao.PictureMapper;
import com.dzkd.website.pojo.News;
import com.dzkd.website.pojo.NewsType;
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

import java.util.List;

@Service
public class NewsTypeServiceImpl implements ArticleService<NewsType> {

    private static final Logger logger = LogManager.getLogger(NewsServiceImpl.class);

    private NewsTypeMapper newsTypeMapper;
    private NewsMapper newsMapper;
    private PictureMapper pictureMapper;

    @Autowired
    public NewsTypeServiceImpl(NewsTypeMapper newsTypeMapper, NewsMapper newsMapper, PictureMapper pictureMapper) {
        this.newsTypeMapper = newsTypeMapper;
        this.newsMapper = newsMapper;
        this.pictureMapper = pictureMapper;
    }

    /**
     * 添加新闻分类
     *
     * @param newsType
     * @return
     */
    @Override
    public R addArticle(NewsType newsType) {
        if (newsType == null) {
            return R.isFail(new Exception("添加新闻分类失败"));
        }

        try {
            newsType.setTypeId(null);
            int insert = newsTypeMapper.insertSelective(newsType);
            logger.info("NewsTypeServiceImpl->insert:" + insert);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("添加新闻分类失败"));
        }
    }

    /**
     * 更新新闻分类
     *
     * @param newsType
     * @return
     */
    @Override
    public R updateArticle(NewsType newsType) {
        if (newsType == null || newsType.getTypeId() == null) {
            return R.isFail(new Exception("更新新闻分类失败"));
        }

        try {
            int update = newsTypeMapper.updateByPrimaryKeySelective(newsType);
            logger.info("NewsTypeServiceImpl->update:" + update);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("更新新闻分类失败"));
        }
    }

    /**
     * 删除新闻分类
     *
     * @param newsTypeId
     * @return
     */
    @Override
    public R delArticle(Integer newsTypeId) {
        if (newsTypeId == null) {
            return R.isFail(new Exception("删除新闻分类失败"));
        }

        try {
            int del = newsTypeMapper.deleteByPrimaryKey(newsTypeId);
            logger.info("NewsTypeServiceImpl->del:" + del);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("删除新闻分类失败"));
        }
    }

    /**
     * 批量删除
     *
     * @param newsTypeList
     * @return
     */
    @Override
    public R delBatch(List<NewsType> newsTypeList) {
        if (newsTypeList.size() == 0) {
            return R.isFail(new Exception("删除新闻分类失败"));
        }

        try {
            //删除类别下新闻的图片
            for (NewsType newsType : newsTypeList) {
                List<News> newsList = newsMapper.selectByNewsType(newsType.getTypeId());
                for (News news : newsList) {
                    List<Picture> pictureList = pictureMapper.selectByArticle(0, news.getNewsId());
                    if (pictureList.size() != 0) {
                        FileUtil.delFile(pictureList, 1);

                        int delPictures = pictureMapper.deleteBatch(pictureList);
                        logger.info("NewsTypeServiceImpl->delBatch->delPictures:" + delPictures);
                    }
                }
            }

            int delBatch = newsTypeMapper.deleteBatch(newsTypeList);
            logger.info("NewsTypeServiceImpl->delBatch:" + (delBatch == newsTypeList.size()));

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("删除新闻分类失败"));
        }
    }

    /**
     * 获取新闻分类
     *
     * @param newsTypeId
     * @return
     */
    @Override
    public R searchArticle(Integer newsTypeId) {
        if (newsTypeId == null) {
            return R.isFail(new Exception("获取新闻分类失败"));
        }

        try {
            NewsType newsTypeResult = newsTypeMapper.selectByPrimaryKey(newsTypeId);
            if (newsTypeResult == null) {
                return R.isFail(new Exception("404 Not Found"));
            }

            return R.isOk().data(newsTypeResult);
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("获取新闻分类失败"));
        }
    }

    /**
     * 获取所有信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public R showAll(Integer pageNum, Integer pageSize, Object object) {
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
        List<NewsType> newsTypeList = newsTypeMapper.selectAll();
        PageInfo<NewsType> pageInfo = new PageInfo<>(newsTypeList);

        JSONObject data = new JSONObject();
        data.put("data", newsTypeList);
        data.put("pageInfo", pageInfo);

        return R.isOk().data(data);
    }
}
