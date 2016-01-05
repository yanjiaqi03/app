package com.ihealth.aijiakang.database.data;

public class Data_Task {

    private int taskId;
    private int userId;
    private String type;
    private String content;
    private int taskState;
    private int isRead;//0未读，1已读
    public int getTaskId() {
        return taskId;
    }
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getTaskState() {
        return taskState;
    }
    public void setTaskState(int taskState) {
        this.taskState = taskState;
    }
    public int getIsRead() {
        return isRead;
    }
    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }
    
}
