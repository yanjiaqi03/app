package com.ihealth.aijiakang.database.data;

public class Data_EventState {

    private int userId;
    private String eventType;
    private String userState;
    private long userStartTs;
    public Data_EventState(){
        userId = 0;
        eventType = "";
        userState = "";
        userStartTs = getTS();
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getEventType() {
        return eventType;
    }
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    public String getUserState() {
        return userState;
    }
    public void setUserState(String userState) {
        this.userState = userState;
    }
    public long getUserStartTs() {
        return userStartTs;
    }
    public void setUserStartTs(long userStartTs) {
        this.userStartTs = userStartTs;
    }
    private static long getTS() {
		long val = System.currentTimeMillis() / 1000;
		return val;
	}
}
