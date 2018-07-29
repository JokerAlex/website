package com.dzkd.website.dao;

import com.dzkd.website.pojo.AdmissionInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionInfoMapper {
    int deleteByPrimaryKey(Integer admInfoId);

    int insert(AdmissionInfo record);

    int insertSelective(AdmissionInfo record);

    AdmissionInfo selectByPrimaryKey(Integer admInfoId);

    int updateByPrimaryKeySelective(AdmissionInfo record);

    int updateByPrimaryKey(AdmissionInfo record);
}