package com.ihealth.aijiakang.database.data;

public class Data_AM8 {
	private int id;
    private int userId;
    private long ts;
    private String picName;
    private String title;
    private String content;
    private String url;
    private int isDisplay;

    public Data_AM8() {
    	
    }
    
    public Data_AM8(int id, int userId, long ts, String picName, String title, String content, String url,
			int isDisplay) {
		super();
		this.id = id;
		this.userId = userId;
		this.ts = ts;
		this.picName = picName;
		this.title = title;
		this.content = content;
		this.url = url;
		this.isDisplay = isDisplay;
		
	}
	public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public long getTs() {
        return ts;
    }
    public void setTs(long ts) {
        this.ts = ts;
    }
    public String getPicName() {
        return picName;
    }
    public void setPicName(String picName) {
        this.picName = picName;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public int getIsDisplay() {
        return isDisplay;
    }
    public void setIsDisplay(int isDisplay) {
        this.isDisplay = isDisplay;
    }
}
