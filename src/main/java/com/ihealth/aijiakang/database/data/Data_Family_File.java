package com.ihealth.aijiakang.database.data;

public class Data_Family_File {

    private int lineType;     //关联类型
    private int lineID;       //关联ID
    private int fileType;     //文件类型
    private String fileCode;  //文件编码
    private long TS;          //文件的时间戳
    
    public int getFileType() {
        return fileType;
    }
    public void setFileType(int fileType) {
        this.fileType = fileType;
    }
    public int getLineID() {
        return lineID;
    }
    public void setLineID(int lineID) {
        this.lineID = lineID;
    }
    public int getLineType() {
        return lineType;
    }
    public void setLineType(int lineType) {
        this.lineType = lineType;
    }
    public String getFileCode() {
        return fileCode;
    }
    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }
    public long getTS() {
        return TS;
    }
    public void setTS(long tS) {
        TS = tS;
    }
    
}
