package com.dzkd.website.dao;

import com.dzkd.website.pojo.Picture;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureMapper {
    int deleteByPrimaryKey(Integer pictureId);

    int insert(Picture record);

    int insertSelective(Picture record);

    Picture selectByPrimaryKey(Integer pictureId);

    List<Picture> selectByArticle(@Param("articleCate") Integer articleCate, @Param("articleId") Integer articleId);

    int deleteBatch(List<Picture> pictureList);

    int updateByPrimaryKeySelective(Picture record);

    int updateByPrimaryKey(Picture record);
}