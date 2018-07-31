package com.dzkd.website.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.dzkd.website.dao.AdminInfoMapper;
import com.dzkd.website.dao.StudentMapper;
import com.dzkd.website.dao.UserInfoMapper;
import com.dzkd.website.pojo.AdminInfo;
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
     * 获取所有学生信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public JSONObject getAllStudent(int pageNum, int pageSize) {
        if (pageNum <=0 ) {
            pageNum = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Student> studentList = studentMapper.selectAll();
        PageInfo<Student> pageInfo = new PageInfo<>(studentList);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", studentList);
        jsonObject.put("pageInfo", pageInfo);

        return jsonObject;
    }

    /**
     * 获取所有管理员信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public JSONObject getAllAdminInfo(int pageNum, int pageSize) {
        if (pageNum <=0 ) {
            pageNum = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<AdminInfo> adminInfoList = adminInfoMapper.selectAll();
        PageInfo<AdminInfo> pageInfo = new PageInfo<>(adminInfoList);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", adminInfoList);
        jsonObject.put("pageInfo", pageInfo);

        return jsonObject;
    }

    /**
     * 添加用户
     *
     * @param userInfo
     * @return
     */
    @Override
    public int addUserInfo(UserInfo userInfo) {
        if (userInfo == null) {
            return -1;
        }
        return userInfoMapper.insertSelective(userInfo);
    }

    /**
     * 添加管理员
     *版块1.动态信息发布2.网站运营管理3.信息展示4.用户信息管理5.在线交互
     * @param adminInfo
     * @return
     */
    @Override
    public JSONObject addAdminInfo(AdminInfo adminInfo) {
        JSONObject result = new JSONObject();
        int resultCode;
        String msg;

        if (adminInfo == null) {
            resultCode = 0;
            msg = "添加管理员失败";
        } else {
            UserInfo userInfo = adminInfo.getUserInfo();
            //判断用户信息是否已存在
            if (userInfo.getUserInfoId() == null) {
                int insertUser = addUserInfo(userInfo);
                logger.info("addAdminInfo->insertUser:" + insertUser + "\tuserInfoId:" + userInfo.getUserInfoId());
                adminInfo.setUserUserInfoId(userInfo.getUserInfoId());
            }
            int insertAdmin = adminInfoMapper.insertSelective(adminInfo);
            logger.info("addAdminInfo->insertAdmin:" + insertAdmin);
            if (insertAdmin == 1) {
                resultCode = 1;
                msg = "添加管理员成功";
            } else {
                resultCode = 0;
                msg = "添加管理员失败";
            }
        }

        result.put("resultCode", resultCode);
        result.put("msg", msg);
        return result;
    }

    /**
     * 更新管理员信息
     *
     * @param adminInfo
     * @return
     */
    @Override
    public JSONObject updateAdminInfo(AdminInfo adminInfo) {
        JSONObject result = new JSONObject();
        int resultCode;
        String msg;
        if (adminInfo == null) {
            resultCode = 0;
            msg = "更新管理员信息失败";
        } else {
            UserInfo userInfo = adminInfo.getUserInfo();
            UserInfo userInfoTemp = userInfoMapper.selectUserName(userInfo.getUserName());
            //判断用户名是否已经存在
            if (userInfoTemp == null || userInfoTemp.getUserInfoId() == userInfo.getUserInfoId()) {
                int updateUserInfo = userInfoMapper.updateByPrimaryKeySelective(userInfo);
                logger.info("updateAdminInfo->updateUserInfo:" + updateUserInfo);
                int updateAdmin = adminInfoMapper.updateByPrimaryKeySelective(adminInfo);
                logger.info("updateAdminInfo->updateAdmin:" + updateAdmin);
                if (updateAdmin == 1) {
                    resultCode = 1;
                    msg = "更新管理员信息成功";
                } else {
                    resultCode = 0;
                    msg = "更新管理员信息失败";
                }
            } else {
                resultCode = 0;
                msg = "该用户名已存在";
            }
        }

        result.put("resultCode", resultCode);
        result.put("msg", msg);

        return result;
    }

    /**
     * 删除管理员
     *
     * @param adminInfo
     * @return
     */
    @Override
    public JSONObject delAdminInfo(AdminInfo adminInfo) {
        JSONObject result = new JSONObject();
        int resultCode;
        String msg;

        if (adminInfo == null) {
            resultCode = 0;
            msg = "删除管理员失败";
        } else {
            int delAdmin = adminInfoMapper.deleteByPrimaryKey(adminInfo.getAdminId());
            logger.info("delAdminInfo->delAdmin:" + delAdmin);
            if (delAdmin == 1) {
                resultCode = 1;
                msg = "删除管理员成功";
            } else {
                resultCode = 0;
                msg = "删除管理员失败";
            }
            //判断该管理员是否为学生
            Student student = studentMapper.selectByForeignKey(adminInfo.getUserUserInfoId());
            if (student == null) {
                //不是学生用户，则删除基本信息
                int delUser = userInfoMapper.deleteByPrimaryKey(adminInfo.getUserUserInfoId());
                logger.info("delAdminInfo->delUser:" + delUser);
            }
        }

        result.put("resultCode", resultCode);
        result.put("msg", msg);

        return result;
    }

    /**
     * 添加学生
     *
     * @param student
     * @return
     */
    @Override
    public JSONObject addStudent(Student student) {
        JSONObject result = new JSONObject();
        int resultCode;
        String msg;

        if (student == null) {
            resultCode = 0;
            msg = "添加学生失败";
        } else {
            UserInfo userInfo = student.getUserInfo();
            //插入用户基本信息
            int insertUser = addUserInfo(userInfo);
            logger.info("addStudent->insertUser:" + insertUser);
            //插入学生信息
            student.setUserUserInfoId(userInfo.getUserInfoId());
            int insertStu = studentMapper.insertSelective(student);
            logger.info("addStudent->insertStu:" + insertStu);
            if (insertStu == 1) {
                resultCode = 1;
                msg = "添加学生成功";
            } else {
                resultCode = 0;
                msg = "添加学生失败";
            }
        }

        result.put("resultCode", resultCode);
        result.put("msg", msg);

        return result;
    }


    /**
     * 更新学生信息
     *
     * @param student
     * @return
     */
    @Override
    public JSONObject updateStudent(Student student) {
        JSONObject result = new JSONObject();
        int resultCode;
        String msg;

        if (student == null) {
            resultCode = 0;
            msg = "更新学生信息失败";
        } else {
            UserInfo userInfo = student.getUserInfo();
            UserInfo userInfoTemp = userInfoMapper.selectUserName(userInfo.getUserName());
            //判断用户名是否存在
            if (userInfoTemp == null || userInfoTemp.getUserInfoId() == userInfo.getUserInfoId()) {
                int updateUserInfo = userInfoMapper.updateByPrimaryKeySelective(userInfo);
                logger.info("updateStudent->updateUserInfo:" + updateUserInfo);
                int updateStu = studentMapper.updateByPrimaryKeySelective(student);
                logger.info("updateStudent->updateStu:" + updateStu);
                if (updateStu == 1) {
                    resultCode = 1;
                    msg = "更新学生信息成功";
                } else {
                    resultCode = 0;
                    msg = "更新学生信息失败";
                }
            } else {
                resultCode = 0;
                msg = "更新学生信息学失败-该用户名已存在";
            }
        }

        result.put("resultCode", resultCode);
        result.put("msg", msg);

        return result;
    }

    public JSONObject delStudent() {
        return null;
    }

    /**
     * 修改密码
     * @param userName
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @Override
    public JSONObject changePassword(String userName, String oldPassword, String newPassword) {
        JSONObject result = new JSONObject();
        int resultCode;
        String msg;

        UserInfo userInfo = userInfoMapper.selectByUserName(userName, oldPassword);
        if (userInfo != null){
            if (oldPassword.equals(newPassword)) {
                resultCode = 0;
                msg = "新密码与原密码相同";
            } else {
                userInfo.setUserPassword(newPassword);
                int updatePassword = userInfoMapper.updateByPrimaryKeySelective(userInfo);
                logger.info("changePassword->updatePassword:" + updatePassword);
                if (updatePassword == 1) {
                    resultCode = 1;
                    msg = "密码修改成功";
                } else {
                    resultCode = 0;
                    msg = "密码修改失败";
                }
            }
        } else {
            resultCode = 0;
            msg = "原密码输入错误";
        }


        result.put("resultCode", resultCode);
        result.put("msg", msg);

        return result;
    }

}
