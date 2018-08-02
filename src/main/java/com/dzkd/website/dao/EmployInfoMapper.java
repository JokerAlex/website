package com.dzkd.website.dao;

import com.dzkd.website.pojo.EmployInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployInfoMapper {
    int deleteByPrimaryKey(Integer empInfoId);

    int insert(EmployInfo record);

    int insertSelective(EmployInfo record);

    EmployInfo selectByPrimaryKey(Integer empInfoId);

    List<EmployInfo> selectAll();

    int updateByPrimaryKeySelective(EmployInfo record);

    int updateByPrimaryKey(EmployInfo record);
}