package com.dzkd.website.pojo;

public class UserInfo {
    private Integer userInfoId;

    private String realName;

    private String address;

    private String telephone;

    private String email;

    private String userName;

    private String userPassword;

    private String regTime;

    public UserInfo(Integer userInfoId, String realName, String address, String telephone, String email, String userName, String userPassword, String regTime) {
        this.userInfoId = userInfoId;
        this.realName = realName;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
        this.userName = userName;
        this.userPassword = userPassword;
        this.regTime = regTime;
    }

    public UserInfo() {
        super();
    }

    public Integer getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Integer userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime == null ? null : regTime.trim();
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userInfoId=" + userInfoId +
                ", realName='" + realName + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", regTime='" + regTime + '\'' +
                '}';
    }
}