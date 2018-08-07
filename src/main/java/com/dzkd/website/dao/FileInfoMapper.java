package com.dzkd.website.dao;

import com.dzkd.website.pojo.FileInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileInfoMapper {
    int deleteByPrimaryKey(Integer fileId);

    int insert(FileInfo record);

    int insertSelective(FileInfo record);

    FileInfo selectByPrimaryKey(Integer fileId);

    List<FileInfo> selectByArticle(@Param("articleCate") Integer articleCate, @Param("articleId") Integer articleId);

    int deleteBatch(List<FileInfo> fileInfoList);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKey(FileInfo record);
}