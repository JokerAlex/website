package com.dzkd.website.pojo;

public class AdminInfo {
    private Integer adminId;

    private Integer adminBlockId;

    private Integer userUserInfoId;

    private UserInfo userInfo;

    public AdminInfo(Integer adminId, Integer adminBlockId, Integer userUserInfoId, UserInfo userInfo) {
        this.adminId = adminId;
        this.adminBlockId = adminBlockId;
        this.userUserInfoId = userUserInfoId;
        this.userInfo = userInfo;
    }

    public AdminInfo(Integer adminId, Integer adminBlockId, Integer userUserInfoId) {
        this.adminId = adminId;
        this.adminBlockId = adminBlockId;
        this.userUserInfoId = userUserInfoId;
    }

    public AdminInfo() {
        super();
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getAdminBlockId() {
        return adminBlockId;
    }

    public void setAdminBlockId(Integer adminBlockId) {
        this.adminBlockId = adminBlockId;
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
        return "AdminInfo{" +
                "adminId=" + adminId +
                ", adminBlockId=" + adminBlockId +
                ", userUserInfoId=" + userUserInfoId +
                ", userInfo=" + userInfo.toString() +
                '}';
    }
}