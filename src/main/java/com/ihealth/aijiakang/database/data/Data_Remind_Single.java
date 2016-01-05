
package com.ihealth.aijiakang.database.data;

public class Data_Remind_Single {

    private String PhoneDataID;// BP数据的ID
    private int UserId; // 本条BP数据所有者ID
    private int SponsorID; // 提醒发起者的ID
    private long TS; // 提醒的TS
    private long MeasureTS; // BP数据测量时间
    private int isExp; // 提醒是否过期

    public String getPhoneDataID() {
        return PhoneDataID;
    }

    public void setPhoneDataID(String phoneDataID) {
        PhoneDataID = phoneDataID;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
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

    public long getMeasureTS() {
        return MeasureTS;
    }

    public void setMeasureTS(long measureTS) {
        MeasureTS = measureTS;
    }

    public int getIsExp() {
        return isExp;
    }

    public void setIsExp(int isExp) {
        this.isExp = isExp;
    }


}
