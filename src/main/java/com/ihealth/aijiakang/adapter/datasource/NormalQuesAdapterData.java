package com.ihealth.aijiakang.adapter.datasource;

/**
 * Created by YanJiaqi on 15/12/5
 */
public class NormalQuesAdapterData {
    private String group = "";
    private String child = "";

    public NormalQuesAdapterData(String group,String child){
        this.group = group;
        this.child = child;
    }
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }
}
