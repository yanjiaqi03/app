package com.ihealth.aijiakang.database.data;

public class Data_SyncNetData {

	private String iHealthID;
	private int userID;
	private long BpSyncTime;
	private long CareSyncTime;
	private long CommentSyncTime;
	private long NewsSyncTime;
	private long RemindSyncTime;
	private long AM8Time;
	private long BpSyncTimeMin;
	
	public Data_SyncNetData() {
		super();
		iHealthID = new String();
	}

	public Data_SyncNetData(String iHealthID, int userID, long bpSyncTime, long careSyncTime, long commentSyncTime, long remindSyncTime) {
		super();
		this.iHealthID = iHealthID;
		this.userID = userID;
		this.BpSyncTime = bpSyncTime;
		this.CareSyncTime = careSyncTime;
		this.CommentSyncTime = commentSyncTime;
		this.RemindSyncTime = remindSyncTime;
	}

	public String getiHealthID() {
		return iHealthID;
	}

	public void setiHealthID(String iHealthID) {
		this.iHealthID = iHealthID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public long getBpSyncTime() {
		return BpSyncTime;
	}

	public void setBpSyncTime(long bpSyncTime) {
		BpSyncTime = bpSyncTime;
	}

	public long getCareSyncTime() {
		return CareSyncTime;
	}

	public void setCareSyncTime(long careSyncTime) {
		CareSyncTime = careSyncTime;
	}

	public long getCommentSyncTime() {
		return CommentSyncTime;
	}

	public void setCommentSyncTime(long commentSyncTime) {
		CommentSyncTime = commentSyncTime;
	}

	public long getNewsSyncTime() {
		return NewsSyncTime;
	}

	public void setNewsSyncTime(long newsSyncTime) {
		NewsSyncTime = newsSyncTime;
	}

    public long getRemindSyncTime() {
        return RemindSyncTime;
    }

    public void setRemindSyncTime(long remindSyncTime) {
        RemindSyncTime = remindSyncTime;
    }

    public long getAM8Time() {
        return AM8Time;
    }

    public void setAM8Time(long aM8Time) {
        AM8Time = aM8Time;
    }

    public long getBpSyncTimeMin() {
        return BpSyncTimeMin;
    }

    public void setBpSyncTimeMin(long bpSyncTimeMin) {
        BpSyncTimeMin = bpSyncTimeMin;
    }


}
