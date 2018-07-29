package com.dzkd.website.pojo;

public class LogRecord {
    private Integer logId;

    private String logTime;

    private String operationIp;

    private String logContent;

    public LogRecord(Integer logId, String logTime, String operationIp, String logContent) {
        this.logId = logId;
        this.logTime = logTime;
        this.operationIp = operationIp;
        this.logContent = logContent;
    }

    public LogRecord() {
        super();
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime == null ? null : logTime.trim();
    }

    public String getOperationIp() {
        return operationIp;
    }

    public void setOperationIp(String operationIp) {
        this.operationIp = operationIp == null ? null : operationIp.trim();
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent == null ? null : logContent.trim();
    }
}