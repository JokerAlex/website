package com.dzkd.website.dao;

import com.dzkd.website.pojo.NewsType;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsTypeMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(NewsType record);

    int insertSelective(NewsType record);

    NewsType selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(NewsType record);

    int updateByPrimaryKey(NewsType record);
}