package com.dzkd.website.dao;

import com.dzkd.website.pojo.Notice;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeMapper {
    int deleteByPrimaryKey(Integer noticeId);

    int deleteBatch(List<Notice> noticeList);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer noticeId);

    List<Notice> selectAll();

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);
}