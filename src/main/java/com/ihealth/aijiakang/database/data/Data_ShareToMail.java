package com.ihealth.aijiakang.database.data;

public class Data_ShareToMail {

	public Data_ShareToMail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Data_ShareToMail(String mailAddress, String iHealthCloud) {
		super();
		this.mailAddress = mailAddress;
		this.iHealthCloud = iHealthCloud;
	}

	private  String mailAddress;
	private  String iHealthCloud;
	

	/*
	 * 邮箱地址
	 */
	public String getMailAddress() {
		return mailAddress;
	}
	/*
	 * 邮箱地址
	 */
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	/*
	 * 设置该邮箱的用户
	 */
	public String getiHealthCloud() {
		return iHealthCloud;
	}
	/*
	 * 设置该邮箱的用户
	 */
	public void setiHealthCloud(String iHealthCloud) {
		this.iHealthCloud = iHealthCloud;
	}


	
}
