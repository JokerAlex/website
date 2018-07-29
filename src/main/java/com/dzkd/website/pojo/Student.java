package com.dzkd.website.pojo;

public class Student {
    private String stuId;

    private String stuCollege;

    private String stuMajor;

    private String stuGrade;

    private Integer userUserInfoId;

    private UserInfo userInfo;

    public Student(String stuId, String stuCollege, String stuMajor, String stuGrade, Integer userUserInfoId, UserInfo userInfo) {
        this.stuId = stuId;
        this.stuCollege = stuCollege;
        this.stuMajor = stuMajor;
        this.stuGrade = stuGrade;
        this.userUserInfoId = userUserInfoId;
        this.userInfo = userInfo;
    }

    public Student(String stuId, String stuCollege, String stuMajor, String stuGrade, Integer userUserInfoId) {
        this.stuId = stuId;
        this.stuCollege = stuCollege;
        this.stuMajor = stuMajor;
        this.stuGrade = stuGrade;
        this.userUserInfoId = userUserInfoId;
    }

    public Student() {
        super();
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId == null ? null : stuId.trim();
    }

    public String getStuCollege() {
        return stuCollege;
    }

    public void setStuCollege(String stuCollege) {
        this.stuCollege = stuCollege == null ? null : stuCollege.trim();
    }

    public String getStuMajor() {
        return stuMajor;
    }

    public void setStuMajor(String stuMajor) {
        this.stuMajor = stuMajor == null ? null : stuMajor.trim();
    }

    public String getStuGrade() {
        return stuGrade;
    }

    public void setStuGrade(String stuGrade) {
        this.stuGrade = stuGrade == null ? null : stuGrade.trim();
    }

    public Integer getUserUserInfoId() {
        return userUserInfoId;
    }

    public void setUserUserInfoId(Integer userUserInfoId) {
        this.userUserInfoId = userUserInfoId;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuId='" + stuId + '\'' +
                ", stuCollege='" + stuCollege + '\'' +
                ", stuMajor='" + stuMajor + '\'' +
                ", stuGrade='" + stuGrade + '\'' +
                ", userUserInfoId=" + userUserInfoId +
                ", userInfo=" + userInfo.toString() +
                '}';
    }
}