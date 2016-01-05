package com.ihealth.aijiakang.database.data;

/**
 * 用户好友表
 * @author licheng
 */
public class Data_Friend {

	private int FriendUserId;// UserId
	private String Remark;// 备注(主人给家人设置的备注)
	private String NickName;// 昵称（家人的姓名）
	private String HeadImg;// 用户头像
	private long TS;//时间戳
	private String ihealthID;// 用户名（主人的iHealthID）
	private int userId;// 主人的userId

	public Data_Friend(int UserId,String Remark,String NickName,String HeadImg,long TS
			,String ihealthID,int userId) {
		super();
		this.FriendUserId = UserId;
		this.Remark = Remark;
		this.NickName = NickName;
		this.HeadImg = HeadImg;
		this.TS = TS;
		this.ihealthID=ihealthID;
		this.userId = userId;
	}
	public Data_Friend() {
		Remark=new String();
		NickName=new String();
		HeadImg=new String();
		ihealthID=new String();
	}
	public int getFriendUserId() {
		return FriendUserId;
	}
	public void setFriendUserId(int friendUserId) {
		FriendUserId = friendUserId;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public String getNickName() {
		return NickName;
	}
	public void setNickName(String nickName) {
		NickName = nickName;
	}
	public String getHeadImg() {
		return HeadImg;
	}
	public void setHeadImg(String headImg) {
		HeadImg = headImg;
	}
	public long getTS() {
		return TS;
	}
	public void setTS(long tS) {
		TS = tS;
	}
	public String getIhealthID() {
		return ihealthID;
	}
	public void setIhealthID(String ihealthID) {
		this.ihealthID = ihealthID;
	}
	public int getUserId(){
	    return userId;
	}
	public void setUserId(int userId){
	    this.userId = userId;
	}
}
