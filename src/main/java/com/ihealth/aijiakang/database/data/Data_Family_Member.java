package com.ihealth.aijiakang.database.data;

/**
 * 用户家人表
 * @author licheng
 */
public class Data_Family_Member {

    private int familyId;// FaminlyId
    private int userId;// 家人的userId
    private String remark;// 备注(主人给家人设置的备注－－成员角色)
    private String headImg;// 用户头像
    private String un;//家人的un
    private String token;//家人的token
    private long TS;//头像时间戳
    private String refreshToken;//刷新token
    private String familyUserName;
    private int familyUserGender;
    private long familyUserBirthday;
    private int familyUserHeight;
    private float familyUserWeight;

    public Data_Family_Member(int FamilyId,int UserId,String Remark ,String HeadImg,long TS, String familyUserName,
                              int familyUserGender, long familyUserBirthday, int familyUserHeight, float familyUserWeight) {
        super();
        this.familyId = FamilyId;
        this.userId = UserId;
        this.remark = Remark;
        this.headImg = HeadImg;
        this.TS = TS;
        this.familyUserName = familyUserName;
        this.familyUserGender = familyUserGender;
        this.familyUserBirthday = familyUserBirthday;
        this.familyUserHeight = familyUserHeight;
        this.familyUserWeight = familyUserWeight;
    }
    public Data_Family_Member() {
        remark=new String();
        headImg=new String();
        familyUserName = "";
    }
    public int getFamilyId() {
        return familyId;
    }
    public void setFamilyId(int familyId) {
        this.familyId = familyId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getHeadImg() {
        return headImg;
    }
    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
    public String getUn() {
        return un;
    }
    public void setUn(String un) {
        this.un = un;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public long getTS() {
        return TS;
    }
    public void setTS(long tS) {
        TS = tS;
    }
    public String getRefreshToken() {
        return refreshToken;
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    public String getFamilyUserName() {
        return familyUserName;
    }

    public void setFamilyUserName(String familyUserName) {
        this.familyUserName = familyUserName;
    }

    public int getFamilyUserGender() {
        return familyUserGender;
    }

    public void setFamilyUserGender(int familyUserGender) {
        this.familyUserGender = familyUserGender;
    }

    public long getFamilyUserBirthday() {
        return familyUserBirthday;
    }

    public void setFamilyUserBirthday(long familyUserBirthday) {
        this.familyUserBirthday = familyUserBirthday;
    }

    public int getFamilyUserHeight() {
        return familyUserHeight;
    }

    public void setFamilyUserHeight(int familyUserHeight) {
        this.familyUserHeight = familyUserHeight;
    }

    public float getFamilyUserWeight() {
        return familyUserWeight;
    }

    public void setFamilyUserWeight(float familyUserWeight) {
        this.familyUserWeight = familyUserWeight;
    }
    
}
