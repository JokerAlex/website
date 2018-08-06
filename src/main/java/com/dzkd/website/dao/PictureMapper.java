package com.dzkd.website.dao;

import com.dzkd.website.pojo.Picture;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureMapper {
    int deleteByPrimaryKey(Integer pictureId);

    int insert(Picture record);

    int insertSelective(Picture record);

    Picture selectByPrimaryKey(Integer pictureId);

    int updateByPrimaryKeySelective(Picture record);

    int updateByPrimaryKey(Picture record);
}