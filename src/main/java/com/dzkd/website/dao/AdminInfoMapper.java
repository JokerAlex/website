package com.dzkd.website.dao;

import com.dzkd.website.pojo.AdminInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminInfoMapper {
    int deleteByPrimaryKey(Integer adminId);

    int deleteBatch(List<AdminInfo> adminInfoList);

    int insert(AdminInfo record);

    int insertSelective(AdminInfo record);

    AdminInfo selectByPrimaryKey(Integer adminId);

    AdminInfo selectByAdminId(Integer adminId);

    AdminInfo selectByUserName(@Param("userName") String userName, @Param("password") String password);

    List<AdminInfo> selectAll();

    int updateByPrimaryKeySelective(AdminInfo record);

    int updateByPrimaryKey(AdminInfo record);
}