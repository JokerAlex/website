package com.dzkd.website.dao;

import com.dzkd.website.pojo.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public interface StudentMapper {
    int deleteByPrimaryKey(String stuId);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String stuId);

    Student selectByStuId(Integer stuId);

    Student selectByUserNameAndPass(@Param("userName") String userName, @Param("password") String password);

    Student selectByStuIdAndPass(@Param("stuId") String stuId, @Param("password") String password);

    List<Student> selectAll();

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}