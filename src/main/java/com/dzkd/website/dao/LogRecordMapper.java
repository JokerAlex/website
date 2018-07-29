package com.dzkd.website.dao;

import com.dzkd.website.pojo.LogRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRecordMapper {
    int deleteByPrimaryKey(Integer logId);

    int insert(LogRecord record);

    int insertSelective(LogRecord record);

    LogRecord selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(LogRecord record);

    int updateByPrimaryKey(LogRecord record);
}