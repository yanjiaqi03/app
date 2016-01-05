package com.ihealth.aijiakang.database.data;

public class Data_Device {

    private String mac;
    private int isConnected;
    private long times;
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public int getIsConnected() {
		return isConnected;
	}
	public void setIsConnected(int isConnected) {
		this.isConnected = isConnected;
	}
	public long getTimes() {
		return times;
	}
	public void setTimes(long times) {
		this.times = times;
	}
    
}
