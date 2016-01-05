package com.ihealth.aijiakang.database.table;

import java.util.LinkedHashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.ihealth.aijiakang.database.DataBaseHelper;
import com.ihealth.aijiakang.database.DataBaseTools;
import com.ihealth.aijiakang.database.data.Data_Chat_Single;

public class Data_TB_Chat_Single extends DataBaseTools {
	
	//叮咛表
	public static final String TABLE_TB_CHAT    = "TB_CHAT";		
	public static final String CHAT_PHONEDATAID = "PhoneDataID";//BPID
	public static final String CHAT_USERID      = "UserId";		//本条BP数据所有者ID
	public static final String CHAT_SPONSORID   = "SponsorID";	//发起者
	public static final String CHAT_RECEIVERID  = "ReceiverID";	//接受者
	public static final String CHAT_CONTENT     = "Content";	//内容
	public static final String CHAT_CID         = "CID";		//本条数据在数据库中的ID(用于删除)	
	public static final String CHAT_TS          = "TS";			//本条叮咛数据TS
	public static final String CHAT_MEASURETS   = "MeasureTS";	//本条BP数据TS

	private static final String TAG = "Data_TB_Chat_Single";
   
    
    public Data_TB_Chat_Single(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
    
	
	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_TB_CHAT;
	}

	
	@Override
	protected LinkedHashMap<String, String> getEachKey() {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> keyMap = new LinkedHashMap<String, String>();
		keyMap.put(CHAT_CID, "varchar(128,0)");
		keyMap.put(CHAT_CONTENT, "varchar(128,0)");
		keyMap.put(CHAT_MEASURETS, "long(25,0)");
		keyMap.put(CHAT_PHONEDATAID, "varchar(128,0)");
		keyMap.put(CHAT_RECEIVERID, "int(10,0)");
		keyMap.put(CHAT_SPONSORID, "int(10,0)");
		keyMap.put(CHAT_TS, "long(25,0)");
		keyMap.put(CHAT_USERID, "int(10,0)");
		
		return keyMap;
	}

	@Override
	protected String getPrimaryKey() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	protected SQLiteOpenHelper getHelperObject() {
		// TODO Auto-generated method stub
		return DataBaseHelper.getInstance(getContext());
	}

	
	@Override
	protected String onUpgradeColumn(String column) {
		// TODO Auto-generated method stub
		String value = "";
		if(column.equals(CHAT_CID)) {
			value = "''";
		}else if(column.equals(CHAT_CONTENT)) {
			value = "''";
		}else if(column.equals(CHAT_MEASURETS)) {
			value = "0";
		}else if(column.equals(CHAT_PHONEDATAID)) {
			value = "''";
		}else if(column.equals(CHAT_RECEIVERID)) {
			value = "0";
		}else if(column.equals(CHAT_SPONSORID)) {
			value = "0";
		}else if(column.equals(CHAT_TS)) {
			value = "0";
		}else if(column.equals(CHAT_USERID)) {
			value = "0";
		}
		return value;
	}

	
	@Override
	public <T> boolean addData(T obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Data_Chat_Single) {
			Data_Chat_Single data = (Data_Chat_Single)obj;
			ContentValues values = new ContentValues();
			values.put(CHAT_CID, data.getCID());
			values.put(CHAT_CONTENT, data.getContent());
			values.put(CHAT_MEASURETS, data.getMeasureTS());
			values.put(CHAT_PHONEDATAID, data.getPhoneDataID());
			values.put(CHAT_RECEIVERID, data.getReceiverID());
			values.put(CHAT_SPONSORID, data.getSponsorID());
			values.put(CHAT_TS, data.getTS());
			values.put(CHAT_USERID, data.getUserId());
			return insert(values);
		}
		return false;
	}
}
