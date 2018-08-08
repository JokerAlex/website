package com.dzkd.website.dao;

import com.dzkd.website.pojo.ProfessionalIntroduction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessionalIntroductionMapper {
    int deleteByPrimaryKey(Integer professionalId);

    int deleteByDepartmentId(Integer DepartmentId);

    int deleteBatch(List<ProfessionalIntroduction> professionalIntroductionList);

    int insert(ProfessionalIntroduction record);

    int insertSelective(ProfessionalIntroduction record);

    ProfessionalIntroduction selectByPrimaryKey(Integer professionalId);

    List<ProfessionalIntroduction> selectAll(Integer departmentId);

    int updateByPrimaryKeySelective(ProfessionalIntroduction record);

    int updateByPrimaryKey(ProfessionalIntroduction record);
}