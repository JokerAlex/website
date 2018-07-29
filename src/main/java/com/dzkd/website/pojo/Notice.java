package com.dzkd.website.pojo;

public class Notice {
    private Integer noticeId;

    private String noticeContent;

    private String noticeTime;

    private Integer noticeAcessNumber;

    private Integer adminAdminId;

    public Notice(Integer noticeId, String noticeContent, String noticeTime, Integer noticeAcessNumber, Integer adminAdminId) {
        this.noticeId = noticeId;
        this.noticeContent = noticeContent;
        this.noticeTime = noticeTime;
        this.noticeAcessNumber = noticeAcessNumber;
        this.adminAdminId = adminAdminId;
    }

    public Notice() {
        super();
    }

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent == null ? null : noticeContent.trim();
    }

    public String getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(String noticeTime) {
        this.noticeTime = noticeTime == null ? null : noticeTime.trim();
    }

    public Integer getNoticeAcessNumber() {
        return noticeAcessNumber;
    }

    public void setNoticeAcessNumber(Integer noticeAcessNumber) {
        this.noticeAcessNumber = noticeAcessNumber;
    }

    public Integer getAdminAdminId() {
        return adminAdminId;
    }

    public void setAdminAdminId(Integer adminAdminId) {
        this.adminAdminId = adminAdminId;
    }
}