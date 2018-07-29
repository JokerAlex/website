package com.dzkd.website.service.Impl;

import com.dzkd.website.dao.StudentMapper;
import com.dzkd.website.pojo.Student;
import com.dzkd.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> seelectAll() {
        return studentMapper.selectAll();
    }
}
