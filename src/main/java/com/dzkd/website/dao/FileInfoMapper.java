package com.dzkd.website.dao;

import com.dzkd.website.pojo.FileInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface FileInfoMapper {
    int deleteByPrimaryKey(Integer fileId);

    int insert(FileInfo record);

    int insertSelective(FileInfo record);

    FileInfo selectByPrimaryKey(Integer fileId);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKey(FileInfo record);
}