package com.dzkd.website.dao;

import com.dzkd.website.pojo.News;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsMapper {
    int deleteByPrimaryKey(Integer newsId);

    int deleteBatch(List<News> newsList);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer newsId);

    List<News> selectByNewsType(Integer newsTypeId);

    List<News> selectAll(Integer newsTypeId);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);
}