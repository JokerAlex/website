package com.dzkd.website.pojo;

public class SchoolIntroduction {
    private Integer schoolId;

    private String schoolTitle;

    private String schoolUpdateTime;

    private String schoolContent;

    private Integer schoolAcessNumber;

    private Integer adminAdminId;

    public SchoolIntroduction(Integer schoolId, String schoolTitle, String schoolUpdateTime, String schoolContent, Integer schoolAcessNumber, Integer adminAdminId) {
        this.schoolId = schoolId;
        this.schoolTitle = schoolTitle;
        this.schoolUpdateTime = schoolUpdateTime;
        this.schoolContent = schoolContent;
        this.schoolAcessNumber = schoolAcessNumber;
        this.adminAdminId = adminAdminId;
    }

    public SchoolIntroduction() {
        super();
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolTitle() {
        return schoolTitle;
    }

    public void setSchoolTitle(String schoolTitle) {
        this.schoolTitle = schoolTitle == null ? null : schoolTitle.trim();
    }

    public String getSchoolUpdateTime() {
        return schoolUpdateTime;
    }

    public void setSchoolUpdateTime(String schoolUpdateTime) {
        this.schoolUpdateTime = schoolUpdateTime == null ? null : schoolUpdateTime.trim();
    }

    public String getSchoolContent() {
        return schoolContent;
    }

    public void setSchoolContent(String schoolContent) {
        this.schoolContent = schoolContent == null ? null : schoolContent.trim();
    }

    public Integer getSchoolAcessNumber() {
        return schoolAcessNumber;
    }

    public void setSchoolAcessNumber(Integer schoolAcessNumber) {
        this.schoolAcessNumber = schoolAcessNumber;
    }

    public Integer getAdminAdminId() {
        return adminAdminId;
    }

    public void setAdminAdminId(Integer adminAdminId) {
        this.adminAdminId = adminAdminId;
    }
}