package com.dzkd.website.pojo;

public class Comments {
    private Integer commentId;

    private String commentContent;

    private String commentUpTime;

    private String commentDelTime;

    private String studentStuId;

    private Integer postPostId;

    public Comments(Integer commentId, String commentContent, String commentUpTime, String commentDelTime, String studentStuId, Integer postPostId) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.commentUpTime = commentUpTime;
        this.commentDelTime = commentDelTime;
        this.studentStuId = studentStuId;
        this.postPostId = postPostId;
    }

    public Comments() {
        super();
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    public String getCommentUpTime() {
        return commentUpTime;
    }

    public void setCommentUpTime(String commentUpTime) {
        this.commentUpTime = commentUpTime == null ? null : commentUpTime.trim();
    }

    public String getCommentDelTime() {
        return commentDelTime;
    }

    public void setCommentDelTime(String commentDelTime) {
        this.commentDelTime = commentDelTime == null ? null : commentDelTime.trim();
    }

    public String getStudentStuId() {
        return studentStuId;
    }

    public void setStudentStuId(String studentStuId) {
        this.studentStuId = studentStuId == null ? null : studentStuId.trim();
    }

    public Integer getPostPostId() {
        return postPostId;
    }

    public void setPostPostId(Integer postPostId) {
        this.postPostId = postPostId;
    }
}