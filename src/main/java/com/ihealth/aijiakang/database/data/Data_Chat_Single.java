package com.ihealth.aijiakang.database.data;

public class Data_Chat_Single {

	private String PhoneDataID;
	private int UserId;   //本条BP数据所有者ID
	private int SponsorID;
	private int ReceiverID;
	private String Content;
	private String CID;
	private long TS; //本条叮咛数据TS
	private long MeasureTS;//本条BP数据TS
	
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
	public int getReceiverID() {
		return ReceiverID;
	}
	public void setReceiverID(int receiverID) {
		ReceiverID = receiverID;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getCID() {
		return CID;
	}
	public void setCID(String cID) {
		CID = cID;
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
		return "Data_TB_Chat_Single [PhoneDataID=" + PhoneDataID + ", UserId=" + UserId + ", SponsorID=" + SponsorID + ",ReceiverID=" + ReceiverID + ",Content=" + Content + ",CID=" + CID + ", TS=" + TS + "]";
	}
}
