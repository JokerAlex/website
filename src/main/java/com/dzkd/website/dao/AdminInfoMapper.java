package com.dzkd.website.dao;

import com.dzkd.website.pojo.AdminInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminInfoMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(AdminInfo record);

    int insertSelective(AdminInfo record);

    AdminInfo selectByPrimaryKey(Integer adminId);

    AdminInfo selectByForeignKey(Integer userId);

    int updateByPrimaryKeySelective(AdminInfo record);

    int updateByPrimaryKey(AdminInfo record);
}