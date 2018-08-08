package com.dzkd.website.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.dzkd.website.dao.AdminInfoMapper;
import com.dzkd.website.dao.StudentMapper;
import com.dzkd.website.dao.UserInfoMapper;
import com.dzkd.website.pojo.AdminInfo;
import com.dzkd.website.pojo.R;
import com.dzkd.website.pojo.Student;
import com.dzkd.website.pojo.UserInfo;
import com.dzkd.website.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserInfoMapper userInfoMapper;
    private AdminInfoMapper adminInfoMapper;
    private StudentMapper studentMapper;

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);


    @Autowired
    public UserServiceImpl(UserInfoMapper userInfoMapper, AdminInfoMapper adminInfoMapper, StudentMapper studentMapper) {
        this.userInfoMapper = userInfoMapper;
        this.adminInfoMapper = adminInfoMapper;
        this.studentMapper = studentMapper;
    }

    /**
     * 获取所有管理员信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public R getAllAdminInfo(int pageNum, int pageSize) {
        if (pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<AdminInfo> adminInfoList = adminInfoMapper.selectAll();
        PageInfo<AdminInfo> pageInfo = new PageInfo<>(adminInfoList);

        JSONObject data = new JSONObject();
        data.put("data", adminInfoList);
        data.put("pageInfo", pageInfo);

        return R.isOk().data(data);
    }

    /**
     * 获取单个管理员信息
     * @param adminId
     * @return
     */
    @Override
    public R getAdminInfo(Integer adminId) {
        if (adminId == null) {
            return R.isFail(new Exception("参数错误"));
        }

        try {
            AdminInfo adminInfo = adminInfoMapper.selectByAdminId(adminId);
            if (adminInfo == null) {
                return R.isFail(new Exception("Not Found"));
            }
            return R.isOk().data(adminInfo);
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("获取信息失败"));
        }
    }

    /**
     * 添加管理员
     * 版块1.动态信息发布2.网站运营管理3.信息展示4.用户信息管理5.在线交互
     *
     * @param adminInfo
     * @return
     */
    @Override
    public R addAdminInfo(AdminInfo adminInfo) {

        if (adminInfo == null) {
            return R.isFail(new Exception("添加管理员失败"));
        }

        try {
            //取出用户基本信息
            UserInfo userInfo = adminInfo.getUserInfo();
            //判断用户名是否存在
            UserInfo userInfoTemp = userInfoMapper.selectUserName(userInfo.getUserName());
            if (userInfoTemp != null && userInfoTemp.getUserInfoId() != userInfo.getUserInfoId()) {
                return R.isFail(new Exception("该用户名已存在"));
            }
            int insertUserInfo = userInfoMapper.insertSelective(userInfo);
            logger.info("UserServiceImpl->addAdmin->insertUserInfo:" + insertUserInfo);

            adminInfo.setUserUserInfoId(userInfo.getUserInfoId());
            int insertAdmin = adminInfoMapper.insertSelective(adminInfo);
            logger.info("UserServiceImpl->addAdmin->insertAdmin:" + insertAdmin);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("添加管理员失败"));
        }
    }

    /**
     * 更新管理员信息
     *
     * @param adminInfo
     * @return
     */
    @Override
    public R updateAdminInfo(AdminInfo adminInfo) {
        if (adminInfo == null) {
            return R.isFail(new Exception("更新管理员信息失败"));
        }

        try {
            UserInfo userInfo = adminInfo.getUserInfo();
            UserInfo userInfoTemp = userInfoMapper.selectUserName(userInfo.getUserName());
            //判断用户名是否已经存在
            if (userInfoTemp != null && userInfoTemp.getUserInfoId() != userInfo.getUserInfoId()) {
                return R.isFail(new Exception("该用户名已存在"));
            }
            //更新用户基本信息
            int updateUserInfo = userInfoMapper.updateByPrimaryKeySelective(userInfo);
            logger.info("updateAdminInfo->updateUserInfo:" + updateUserInfo);
            //更新管理员信息
            int updateAdmin = adminInfoMapper.updateByPrimaryKeySelective(adminInfo);
            logger.info("updateAdminInfo->updateAdmin:" + updateAdmin);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("更新管理员信息失败"));
        }
    }

    /**
     * 删除管理员
     *
     * @param adminInfo
     * @return
     */
    @Override
    public R delAdminInfo(AdminInfo adminInfo) {
        if (adminInfo == null) {
            return R.isFail(new Exception("删除管理员失败"));
        }

        try {
            //删除管理员信息
            int delAdmin = adminInfoMapper.deleteByPrimaryKey(adminInfo.getAdminId());
            logger.info("delAdminInfo->delAdmin:" + delAdmin);
            //删除基本信息
            int delUser = userInfoMapper.deleteByPrimaryKey(adminInfo.getUserUserInfoId());
            logger.info("delAdminInfo->delUser:" + delUser);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("删除管理员失败"));
        }
    }

    /**
     * 获取所有学生信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public R getAllStudent(int pageNum, int pageSize) {
        if (pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Student> studentList = studentMapper.selectAll();
        PageInfo<Student> pageInfo = new PageInfo<>(studentList);

        JSONObject data = new JSONObject();
        data.put("data", studentList);
        data.put("pageInfo", pageInfo);

        return R.isOk().data(data);
    }

    /**
     * 获取单个学生信息
     * @param stuId
     * @return
     */
    @Override
    public R getStudent(Integer stuId) {
        if (stuId == null) {
            return R.isFail(new Exception("参数错误"));
        }

        try {
            Student student = studentMapper.selectByStuId(stuId);
            if (student == null) {
                return R.isFail(new Exception("Not Found"));
            }
            return R.isOk().data(student);
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("获取信息失败"));
        }
    }

    /**
     * 添加学生
     *
     * @param student
     * @return
     */
    @Override
    public R addStudent(Student student) {
        if (student == null) {
            return R.isFail(new Exception("添加学生失败"));
        }

        try {
            UserInfo userInfo = student.getUserInfo();
            UserInfo userInfoTemp = userInfoMapper.selectUserName(userInfo.getUserName());
            //判断用户名是否存在
            if (userInfoTemp != null && userInfoTemp.getUserInfoId() != userInfo.getUserInfoId()) {
                return R.isFail(new Exception("该用户名已存在"));
            }
            //插入用户基本信息
            int insertUser = userInfoMapper.insertSelective(userInfo);
            logger.info("addStudent->insertUser:" + insertUser);
            //插入学生信息
            student.setUserUserInfoId(userInfo.getUserInfoId());
            int insertStu = studentMapper.insertSelective(student);
            logger.info("addStudent->insertStu:" + insertStu);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("添加学生失败"));
        }
    }


    /**
     * 更新学生信息
     *
     * @param student
     * @return
     */
    @Override
    public R updateStudent(Student student) {
        if (student == null) {
            return R.isFail(new Exception("更新学生信息失败"));
        }

        try {
            UserInfo userInfo = student.getUserInfo();
            UserInfo userInfoTemp = userInfoMapper.selectUserName(userInfo.getUserName());
            //判断用户名是否存在
            if (userInfoTemp != null && userInfoTemp.getUserInfoId() != userInfo.getUserInfoId()) {
                return R.isFail(new Exception("该用户名已存在"));
            }
            //更新用户基本信息
            int updateUserInfo = userInfoMapper.updateByPrimaryKeySelective(userInfo);
            logger.info("updateStudent->updateUserInfo:" + updateUserInfo);
            //更行学生信息
            int updateStu = studentMapper.updateByPrimaryKeySelective(student);
            logger.info("updateStudent->updateStu:" + updateStu);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("更新学生信息失败"));
        }
    }

    /**
     * 修改密码
     *
     * @param userName
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @Override
    public R changePassword(String userName, String oldPassword, String newPassword) {
        UserInfo userInfo = userInfoMapper.selectByUserName(userName, oldPassword);
        if (userInfo == null) {
            return R.isFail(new Exception("原密码错误"));
        }

        try {
            if (oldPassword.equals(newPassword)) {
                return R.isFail(new Exception("新密码与原密码相同"));
            } else {
                userInfo.setUserPassword(newPassword);
                int updatePassword = userInfoMapper.updateByPrimaryKeySelective(userInfo);
                logger.info("changePassword->updatePassword:" + updatePassword);

                return R.isOk();
            }
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("修改密码失败"));
        }
    }
}
