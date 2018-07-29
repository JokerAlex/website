package com.dzkd.website.pojo;

public class Reply {
    private Integer replyId;

    private String replyContent;

    private String replyTime;

    private String studentStuId;

    private Integer commentsCommentId;

    public Reply(Integer replyId, String replyContent, String replyTime, String studentStuId, Integer commentsCommentId) {
        this.replyId = replyId;
        this.replyContent = replyContent;
        this.replyTime = replyTime;
        this.studentStuId = studentStuId;
        this.commentsCommentId = commentsCommentId;
    }

    public Reply() {
        super();
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime == null ? null : replyTime.trim();
    }

    public String getStudentStuId() {
        return studentStuId;
    }

    public void setStudentStuId(String studentStuId) {
        this.studentStuId = studentStuId == null ? null : studentStuId.trim();
    }

    public Integer getCommentsCommentId() {
        return commentsCommentId;
    }

    public void setCommentsCommentId(Integer commentsCommentId) {
        this.commentsCommentId = commentsCommentId;
    }
}