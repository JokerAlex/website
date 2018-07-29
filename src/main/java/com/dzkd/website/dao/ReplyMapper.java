package com.dzkd.website.dao;

import com.dzkd.website.pojo.Reply;
import org.springframework.stereotype.Repository;


@Repository
public interface ReplyMapper {
    int deleteByPrimaryKey(Integer replyId);

    int insert(Reply record);

    int insertSelective(Reply record);

    Reply selectByPrimaryKey(Integer replyId);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKey(Reply record);
}