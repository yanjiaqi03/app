package com.ihealth.aijiakang.database.data;

/**
 * 用户Token表
 * 
 * @author licheng
 */
public class Data_UserToken {
	private String iHealthID;// 用户名
	private String RegionHost;// 服务器地址
	private String AccessToken;// 验证token
	private String RefreshToken;// 刷新token

	public Data_UserToken(String iHealthID, String RegionHost, String AccessToken, String RefreshToken
			) {
		super();
		this.iHealthID = iHealthID;
		this.RegionHost = RegionHost;
		this.AccessToken = AccessToken;
		this.RefreshToken = RefreshToken;

	}
	public String getiHealthID() {
		return iHealthID;
	}
	public void setiHealthID(String iHealthID) {
		this.iHealthID = iHealthID;
	}
	public String getRegionHost() {
		return RegionHost;
	}
	public void setRegionHost(String regionHost) {
		RegionHost = regionHost;
	}
	public String getAccessToken() {
		return AccessToken;
	}
	public void setAccessToken(String accessToken) {
		AccessToken = accessToken;
	}
	public String getRefreshToken() {
		return RefreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		RefreshToken = refreshToken;
	}
	public Data_UserToken() {
		iHealthID=new String();
		RegionHost=new String();
		AccessToken=new String();
		RefreshToken=new String();
	}


}
