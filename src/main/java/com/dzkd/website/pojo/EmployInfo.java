package com.dzkd.website.pojo;

public class EmployInfo {
    private Integer empInfoId;

    private String empInfoTitle;

    private String empInfoContent;

    private String empInfoTime;

    private Integer empAcessNumber;

    private Integer adminAdminId;

    public EmployInfo(Integer empInfoId, String empInfoTitle, String empInfoContent, String empInfoTime, Integer empAcessNumber, Integer adminAdminId) {
        this.empInfoId = empInfoId;
        this.empInfoTitle = empInfoTitle;
        this.empInfoContent = empInfoContent;
        this.empInfoTime = empInfoTime;
        this.empAcessNumber = empAcessNumber;
        this.adminAdminId = adminAdminId;
    }

    public EmployInfo(Integer empInfoId, String empInfoTitle, String empInfoTime, Integer adminAdminId) {
        this.empInfoId = empInfoId;
        this.empInfoTitle = empInfoTitle;
        this.empInfoTime = empInfoTime;
        this.adminAdminId = adminAdminId;
    }

    public EmployInfo() {
        super();
    }

    public Integer getEmpInfoId() {
        return empInfoId;
    }

    public void setEmpInfoId(Integer empInfoId) {
        this.empInfoId = empInfoId;
    }

    public String getEmpInfoTitle() {
        return empInfoTitle;
    }

    public void setEmpInfoTitle(String empInfoTitle) {
        this.empInfoTitle = empInfoTitle == null ? null : empInfoTitle.trim();
    }

    public String getEmpInfoContent() {
        return empInfoContent;
    }

    public void setEmpInfoContent(String empInfoContent) {
        this.empInfoContent = empInfoContent == null ? null : empInfoContent.trim();
    }

    public String getEmpInfoTime() {
        return empInfoTime;
    }

    public void setEmpInfoTime(String empInfoTime) {
        this.empInfoTime = empInfoTime == null ? null : empInfoTime.trim();
    }

    public Integer getEmpAcessNumber() {
        return empAcessNumber;
    }

    public void setEmpAcessNumber(Integer empAcessNumber) {
        this.empAcessNumber = empAcessNumber;
    }

    public Integer getAdminAdminId() {
        return adminAdminId;
    }

    public void setAdminAdminId(Integer adminAdminId) {
        this.adminAdminId = adminAdminId;
    }
}