package com.dzkd.website.pojo;

public class News {
    private Integer newsId;

    private String newsTitle;

    private String newsContent;

    private String newsTime;

    private Integer newsAcessNumber;

    private Integer adminAdminId;

    private Integer newsTypeTypeId;

    public News(Integer newsId, String newsTitle, String newsContent, String newsTime, Integer newsAcessNumber, Integer adminAdminId, Integer newsTypeTypeId) {
        this.newsId = newsId;
        this.newsTitle = newsTitle;
        this.newsContent = newsContent;
        this.newsTime = newsTime;
        this.newsAcessNumber = newsAcessNumber;
        this.adminAdminId = adminAdminId;
        this.newsTypeTypeId = newsTypeTypeId;
    }

    public News() {
        super();
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle == null ? null : newsTitle.trim();
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent == null ? null : newsContent.trim();
    }

    public String getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime == null ? null : newsTime.trim();
    }

    public Integer getNewsAcessNumber() {
        return newsAcessNumber;
    }

    public void setNewsAcessNumber(Integer newsAcessNumber) {
        this.newsAcessNumber = newsAcessNumber;
    }

    public Integer getAdminAdminId() {
        return adminAdminId;
    }

    public void setAdminAdminId(Integer adminAdminId) {
        this.adminAdminId = adminAdminId;
    }

    public Integer getNewsTypeTypeId() {
        return newsTypeTypeId;
    }

    public void setNewsTypeTypeId(Integer newsTypeTypeId) {
        this.newsTypeTypeId = newsTypeTypeId;
    }
}