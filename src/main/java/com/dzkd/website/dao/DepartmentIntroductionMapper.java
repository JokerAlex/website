package com.dzkd.website.dao;

import com.dzkd.website.pojo.DepartmentIntroduction;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentIntroductionMapper {
    int deleteByPrimaryKey(Integer departmentId);

    int insert(DepartmentIntroduction record);

    int insertSelective(DepartmentIntroduction record);

    DepartmentIntroduction selectByPrimaryKey(Integer departmentId);

    int updateByPrimaryKeySelective(DepartmentIntroduction record);

    int updateByPrimaryKey(DepartmentIntroduction record);
}