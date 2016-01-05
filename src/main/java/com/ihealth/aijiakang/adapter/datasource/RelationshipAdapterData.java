package com.ihealth.aijiakang.adapter.datasource;

/**
 * Created by YanJiaqi on 15/11/27
 */
public class RelationshipAdapterData extends PersonData{
    private boolean isEditing = false;

    public RelationshipAdapterData(){

    }

    public RelationshipAdapterData(int userId,String logoUrl,String userName){
        this.userId = userId;
        this.logoUrl = logoUrl;
        this.userName = userName;
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

    public boolean isEditing() {
        return isEditing;
    }

    public void setIsEditing(boolean isEditing) {
        this.isEditing = isEditing;
    }
}
