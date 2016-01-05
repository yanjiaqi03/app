package com.ihealth.aijiakang.database.data;

public class Data_Care_Single {

	private String PhoneDataID;
	private int UserId;   //本条BP数据所有者ID
	private int SponsorID;
	private long TS;//关心时的TS
	private long MeasureTS;//BP数据测量时间
	
	public long getMeasureTS() {
		return MeasureTS;
	}
	public void setMeasureTS(long measureTS) {
		MeasureTS = measureTS;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public String getPhoneDataID() {
		return PhoneDataID;
	}
	public void setPhoneDataID(String phoneDataID) {
		PhoneDataID = phoneDataID;
	}
	public int getSponsorID() {
		return SponsorID;
	}
	public void setSponsorID(int sponsorID) {
		SponsorID = sponsorID;
	}
	public long getTS() {
		return TS;
	}
	public void setTS(long tS) {
		TS = tS;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Data_TB_Care_Single [PhoneDataID=" + PhoneDataID + ", UserId=" + UserId + ", SponsorID=" + SponsorID + ", TS=" + TS + "]";
	}
}
