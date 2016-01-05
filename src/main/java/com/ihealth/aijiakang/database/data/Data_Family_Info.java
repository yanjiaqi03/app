package com.ihealth.aijiakang.database.data;

/**
 * @author liujun
 * @since 2014-12-24
 * 家庭信息实体类
 */
public class Data_Family_Info {

    private int familyId;      //家庭ID
    private int createrId;     //创建者ID
    private String familyName; //家庭名称
    private String familyLogo; //全家福照片
    private long familyLogoTS; //全家福照片TS
    private String familyVoice;//家庭录音
    private long familyVoiceTS;//家庭录音TS
    private long familyTS;     //家庭TS
    
    public int getFamilyId() {
        return familyId;
    }
    public void setFamilyId(int familyId) {
        this.familyId = familyId;
    }
    public int getCreaterId() {
        return createrId;
    }
    public void setCreaterId(int createrId) {
        this.createrId = createrId;
    }
    public String getFamilyName() {
        return familyName;
    }
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
    public String getFamilyLogo() {
        return familyLogo;
    }
    public void setFamilyLogo(String familyLogo) {
        this.familyLogo = familyLogo;
    }
    public long getFamilyLogoTS() {
        return familyLogoTS;
    }
    public void setFamilyLogoTS(long familyLogoTS) {
        this.familyLogoTS = familyLogoTS;
    }
    public String getFamilyVoice() {
        return familyVoice;
    }
    public void setFamilyVoice(String familyVoice) {
        this.familyVoice = familyVoice;
    }
    public long getFamilyVoiceTS() {
        return familyVoiceTS;
    }
    public void setFamilyVoiceTS(long familyVoiceTS) {
        this.familyVoiceTS = familyVoiceTS;
    }
    public long getFamilyTS() {
        return familyTS;
    }
    public void setFamilyTS(long familyTS) {
        this.familyTS = familyTS;
    }
    
}
