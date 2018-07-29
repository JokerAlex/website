package com.dzkd.website.pojo;

public class AdmissionInfo {
    private Integer admInfoId;

    private String admInfoTitle;

    private String admInfoContent;

    private String admInfoTime;

    private Integer admAcessNumber;

    private Integer adminAdminId;

    public AdmissionInfo(Integer admInfoId, String admInfoTitle, String admInfoContent, String admInfoTime, Integer admAcessNumber, Integer adminAdminId) {
        this.admInfoId = admInfoId;
        this.admInfoTitle = admInfoTitle;
        this.admInfoContent = admInfoContent;
        this.admInfoTime = admInfoTime;
        this.admAcessNumber = admAcessNumber;
        this.adminAdminId = adminAdminId;
    }

    public AdmissionInfo() {
        super();
    }

    public Integer getAdmInfoId() {
        return admInfoId;
    }

    public void setAdmInfoId(Integer admInfoId) {
        this.admInfoId = admInfoId;
    }

    public String getAdmInfoTitle() {
        return admInfoTitle;
    }

    public void setAdmInfoTitle(String admInfoTitle) {
        this.admInfoTitle = admInfoTitle == null ? null : admInfoTitle.trim();
    }

    public String getAdmInfoContent() {
        return admInfoContent;
    }

    public void setAdmInfoContent(String admInfoContent) {
        this.admInfoContent = admInfoContent == null ? null : admInfoContent.trim();
    }

    public String getAdmInfoTime() {
        return admInfoTime;
    }

    public void setAdmInfoTime(String admInfoTime) {
        this.admInfoTime = admInfoTime == null ? null : admInfoTime.trim();
    }

    public Integer getAdmAcessNumber() {
        return admAcessNumber;
    }

    public void setAdmAcessNumber(Integer admAcessNumber) {
        this.admAcessNumber = admAcessNumber;
    }

    public Integer getAdminAdminId() {
        return adminAdminId;
    }

    public void setAdminAdminId(Integer adminAdminId) {
        this.adminAdminId = adminAdminId;
    }
}