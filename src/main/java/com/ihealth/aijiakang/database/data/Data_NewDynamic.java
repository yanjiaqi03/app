package com.ihealth.aijiakang.database.data;

public class Data_NewDynamic {

	private String sponsorID;
	private String receiverID;
	private String content;
	private long TS;
	private int leftNumber;
	private int type;

	public Data_NewDynamic() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Data_NewDynamic(String sponsorID, String receiverID,
			String content, long tS, int leftNumber, int type) {
		super();
		this.sponsorID = sponsorID;
		this.receiverID = receiverID;
		this.content = content;
		TS = tS;
		this.leftNumber = leftNumber;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Data_TB_NewDynamic [sponsorID=" + sponsorID + ", receiverID="
				+ receiverID + ", content=" + content + ", TS=" + TS
				+ ", leftNumber=" + leftNumber + ", type=" + type + "]";
	}

	public String getSponsorID() {
		return sponsorID;
	}

	public void setSponsorID(String sponsorID) {
		this.sponsorID = sponsorID;
	}

	public String getReceiverID() {
		return receiverID;
	}

	public void setReceiverID(String receiverID) {
		this.receiverID = receiverID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getTS() {
		return TS;
	}

	public void setTS(long tS) {
		TS = tS;
	}

	public int getLeftNumber() {
		return leftNumber;
	}

	public void setLeftNumber(int leftNumber) {
		this.leftNumber = leftNumber;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


}
