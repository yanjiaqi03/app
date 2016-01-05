package com.ihealth.aijiakang.thirdparty;

/**
 * 小米第三方登录返回数据源
 * @author yanjiaqi
 */
public class ThirdLoginData {
	private String userId = "";
	private String nickName = "";
	private String icon = "";
	private String phone = "";
	private String sex = "";
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}
