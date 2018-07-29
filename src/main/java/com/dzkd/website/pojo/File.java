package com.dzkd.website.pojo;

public class File {
    private Integer fileId;

    private String fileName;

    private Integer fileArticleCate;

    private Integer fileArticleId;

    public File(Integer fileId, String fileName, Integer fileArticleCate, Integer fileArticleId) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileArticleCate = fileArticleCate;
        this.fileArticleId = fileArticleId;
    }

    public File() {
        super();
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public Integer getFileArticleCate() {
        return fileArticleCate;
    }

    public void setFileArticleCate(Integer fileArticleCate) {
        this.fileArticleCate = fileArticleCate;
    }

    public Integer getFileArticleId() {
        return fileArticleId;
    }

    public void setFileArticleId(Integer fileArticleId) {
        this.fileArticleId = fileArticleId;
    }
}