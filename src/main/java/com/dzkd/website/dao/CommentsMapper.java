package com.dzkd.website.dao;

import com.dzkd.website.pojo.Comments;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(Comments record);

    int insertSelective(Comments record);

    Comments selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(Comments record);

    int updateByPrimaryKey(Comments record);
}