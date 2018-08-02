package com.dzkd.website.dao;

import com.dzkd.website.pojo.News;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsMapper {
    int deleteByPrimaryKey(Integer newsId);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer newsId);

    List<News> selectAll();

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);
}