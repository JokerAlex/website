package com.dzkd.website.pojo;

public class Article {
    private Integer articleId;
    private Integer adminId;
    private Integer pageViews;
    private String updateTime;
    private String articleTitle;
    private String articleContent;

    public Article() {
    }

    public Article(Integer articleId, Integer adminId, Integer pageViews, String updateTime, String articleTitle, String articleContent) {
        this.articleId = articleId;
        this.adminId = adminId;
        this.pageViews = pageViews;
        this.updateTime = updateTime;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getPageViews() {
        return pageViews;
    }

    public void setPageViews(Integer pageViews) {
        this.pageViews = pageViews;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", adminId=" + adminId +
                ", pageViews=" + pageViews +
                ", updateTime='" + updateTime + '\'' +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleContent='" + articleContent.length() + '\'' +
                '}';
    }
}
