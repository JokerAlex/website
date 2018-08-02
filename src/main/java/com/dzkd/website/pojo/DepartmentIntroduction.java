package com.dzkd.website.pojo;

public class DepartmentIntroduction {
    private Integer departmentId;

    private String departmentTitle;

    private String departmentUpdateTime;

    private String departmentContent;

    private Integer departmentAcessNumber;

    private Integer adminAdminId;

    public DepartmentIntroduction(Integer departmentId, String departmentTitle, String departmentUpdateTime, String departmentContent, Integer departmentAcessNumber, Integer adminAdminId) {
        this.departmentId = departmentId;
        this.departmentTitle = departmentTitle;
        this.departmentUpdateTime = departmentUpdateTime;
        this.departmentContent = departmentContent;
        this.departmentAcessNumber = departmentAcessNumber;
        this.adminAdminId = adminAdminId;
    }

    public DepartmentIntroduction(Integer departmentId, String departmentTitle, String departmentUpdateTime, Integer adminAdminId) {
        this.departmentId = departmentId;
        this.departmentTitle = departmentTitle;
        this.departmentUpdateTime = departmentUpdateTime;
        this.adminAdminId = adminAdminId;
    }

    public DepartmentIntroduction() {
        super();
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentTitle() {
        return departmentTitle;
    }

    public void setDepartmentTitle(String departmentTitle) {
        this.departmentTitle = departmentTitle == null ? null : departmentTitle.trim();
    }

    public String getDepartmentUpdateTime() {
        return departmentUpdateTime;
    }

    public void setDepartmentUpdateTime(String departmentUpdateTime) {
        this.departmentUpdateTime = departmentUpdateTime == null ? null : departmentUpdateTime.trim();
    }

    public String getDepartmentContent() {
        return departmentContent;
    }

    public void setDepartmentContent(String departmentContent) {
        this.departmentContent = departmentContent == null ? null : departmentContent.trim();
    }

    public Integer getDepartmentAcessNumber() {
        return departmentAcessNumber;
    }

    public void setDepartmentAcessNumber(Integer departmentAcessNumber) {
        this.departmentAcessNumber = departmentAcessNumber;
    }

    public Integer getAdminAdminId() {
        return adminAdminId;
    }

    public void setAdminAdminId(Integer adminAdminId) {
        this.adminAdminId = adminAdminId;
    }
}