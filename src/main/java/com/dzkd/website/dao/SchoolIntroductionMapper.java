package com.dzkd.website.dao;

import com.dzkd.website.pojo.SchoolIntroduction;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolIntroductionMapper {
    int deleteByPrimaryKey(Integer schoolId);

    int insert(SchoolIntroduction record);

    int insertSelective(SchoolIntroduction record);

    SchoolIntroduction selectByPrimaryKey(Integer schoolId);

    int updateByPrimaryKeySelective(SchoolIntroduction record);

    int updateByPrimaryKey(SchoolIntroduction record);
}