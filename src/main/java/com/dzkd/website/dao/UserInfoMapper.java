package com.dzkd.website.dao;

import com.dzkd.website.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer userInfoId);

    int deleteBatch(List<UserInfo> userInfoList);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer userInfoId);

    UserInfo selectUserName(@Param("userName") String userName);

    UserInfo selectByUserName(@Param("userName") String userName, @Param("password") String password);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}