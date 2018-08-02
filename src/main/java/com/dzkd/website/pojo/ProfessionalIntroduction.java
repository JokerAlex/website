package com.dzkd.website.pojo;

public class ProfessionalIntroduction {
    private Integer professionalId;

    private String professionalTitle;

    private String professionalUpdateTime;

    private String professionalContent;

    private Integer professionalAcessNumber;

    private Integer adminAdminId;

    private Integer departmentIntroductionDepartmentId;

    private String departmentTitle;

    public ProfessionalIntroduction(Integer professionalId, String professionalTitle, String professionalUpdateTime, String professionalContent, Integer professionalAcessNumber, Integer adminAdminId, Integer departmentIntroductionDepartmentId) {
        this.professionalId = professionalId;
        this.professionalTitle = professionalTitle;
        this.professionalUpdateTime = professionalUpdateTime;
        this.professionalContent = professionalContent;
        this.professionalAcessNumber = professionalAcessNumber;
        this.adminAdminId = adminAdminId;
        this.departmentIntroductionDepartmentId = departmentIntroductionDepartmentId;
    }

    public ProfessionalIntroduction(Integer professionalId, String professionalTitle, String professionalUpdateTime, Integer adminAdminId, Integer departmentIntroductionDepartmentId, String departmentTitle) {
        this.professionalId = professionalId;
        this.professionalTitle = professionalTitle;
        this.professionalUpdateTime = professionalUpdateTime;
        this.adminAdminId = adminAdminId;
        this.departmentIntroductionDepartmentId = departmentIntroductionDepartmentId;
        this.departmentTitle = departmentTitle;
    }

    public ProfessionalIntroduction() {
        super();
    }

    public Integer getProfessionalId() {
        return professionalId;
    }

    public void setProfessionalId(Integer professionalId) {
        this.professionalId = professionalId;
    }

    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle == null ? null : professionalTitle.trim();
    }

    public String getProfessionalUpdateTime() {
        return professionalUpdateTime;
    }

    public void setProfessionalUpdateTime(String professionalUpdateTime) {
        this.professionalUpdateTime = professionalUpdateTime == null ? null : professionalUpdateTime.trim();
    }

    public String getProfessionalContent() {
        return professionalContent;
    }

    public void setProfessionalContent(String professionalContent) {
        this.professionalContent = professionalContent == null ? null : professionalContent.trim();
    }

    public Integer getProfessionalAcessNumber() {
        return professionalAcessNumber;
    }

    public void setProfessionalAcessNumber(Integer professionalAcessNumber) {
        this.professionalAcessNumber = professionalAcessNumber;
    }

    public Integer getAdminAdminId() {
        return adminAdminId;
    }

    public void setAdminAdminId(Integer adminAdminId) {
        this.adminAdminId = adminAdminId;
    }

    public Integer getDepartmentIntroductionDepartmentId() {
        return departmentIntroductionDepartmentId;
    }

    public void setDepartmentIntroductionDepartmentId(Integer departmentIntroductionDepartmentId) {
        this.departmentIntroductionDepartmentId = departmentIntroductionDepartmentId;
    }

    public String getDepartmentTitle() {
        return departmentTitle;
    }

    public void setDepartmentTitle(String departmentTitle) {
        this.departmentTitle = departmentTitle == null ? null : departmentTitle.trim();
    }
}