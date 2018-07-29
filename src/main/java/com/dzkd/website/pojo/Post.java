package com.dzkd.website.pojo;

public class Post {
    private Integer postId;

    private String postContent;

    private String postUpTime;

    private String postDelTime;

    private String studentStuId;

    public Post(Integer postId, String postContent, String postUpTime, String postDelTime, String studentStuId) {
        this.postId = postId;
        this.postContent = postContent;
        this.postUpTime = postUpTime;
        this.postDelTime = postDelTime;
        this.studentStuId = studentStuId;
    }

    public Post() {
        super();
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent == null ? null : postContent.trim();
    }

    public String getPostUpTime() {
        return postUpTime;
    }

    public void setPostUpTime(String postUpTime) {
        this.postUpTime = postUpTime == null ? null : postUpTime.trim();
    }

    public String getPostDelTime() {
        return postDelTime;
    }

    public void setPostDelTime(String postDelTime) {
        this.postDelTime = postDelTime == null ? null : postDelTime.trim();
    }

    public String getStudentStuId() {
        return studentStuId;
    }

    public void setStudentStuId(String studentStuId) {
        this.studentStuId = studentStuId == null ? null : studentStuId.trim();
    }
}