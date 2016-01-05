package com.ihealth.aijiakang.adapter.datasource;

/**
 * Created by YanJiaqi on 15/12/10
 */
public class SelectPersonAdapterData extends PersonData{
    private boolean selected = false;

    public SelectPersonAdapterData(){

    }

    public SelectPersonAdapterData(int userId,String logoUrl,String userName,boolean selected){
        this.userId = userId;
        this.logoUrl = logoUrl;
        this.userName = userName;
        this.selected = selected;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUerName(String uerName) {
        this.userName = uerName;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
