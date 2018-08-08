package com.dzkd.website.dao;

import com.dzkd.website.pojo.DepartmentIntroduction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentIntroductionMapper {
    int deleteByPrimaryKey(Integer departmentId);

    int deleteBatch(List<DepartmentIntroduction> departmentIntroductionList);

    int insert(DepartmentIntroduction record);

    int insertSelective(DepartmentIntroduction record);

    DepartmentIntroduction selectByPrimaryKey(Integer departmentId);

    List<DepartmentIntroduction> selectAll();

    int updateByPrimaryKeySelective(DepartmentIntroduction record);

    int updateByPrimaryKey(DepartmentIntroduction record);
}