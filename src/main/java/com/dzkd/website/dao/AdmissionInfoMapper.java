package com.dzkd.website.dao;

import com.dzkd.website.pojo.AdmissionInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdmissionInfoMapper {
    int deleteByPrimaryKey(Integer admInfoId);

    int deleteBatch(List<AdmissionInfo> admissionInfoList);

    int insert(AdmissionInfo record);

    int insertSelective(AdmissionInfo record);

    AdmissionInfo selectByPrimaryKey(Integer admInfoId);

    List<AdmissionInfo> selectAll(String admissionTitle);

    int updateByPrimaryKeySelective(AdmissionInfo record);

    int updateByPrimaryKey(AdmissionInfo record);
}