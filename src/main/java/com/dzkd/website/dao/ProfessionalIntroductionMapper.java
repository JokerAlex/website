package com.dzkd.website.dao;

import com.dzkd.website.pojo.ProfessionalIntroduction;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalIntroductionMapper {
    int deleteByPrimaryKey(Integer professionalId);

    int insert(ProfessionalIntroduction record);

    int insertSelective(ProfessionalIntroduction record);

    ProfessionalIntroduction selectByPrimaryKey(Integer professionalId);

    int updateByPrimaryKeySelective(ProfessionalIntroduction record);

    int updateByPrimaryKey(ProfessionalIntroduction record);
}