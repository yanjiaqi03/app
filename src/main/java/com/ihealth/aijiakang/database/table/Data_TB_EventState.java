package com.ihealth.aijiakang.database.table;

import java.util.LinkedHashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.ihealth.aijiakang.database.DataBaseHelper;
import com.ihealth.aijiakang.database.DataBaseTools;
import com.ihealth.aijiakang.database.data.Data_EventState;

public class Data_TB_EventState extends DataBaseTools {
	//用户参加活动的状态
	public static final String TABLE_TB_EVENTSTATE  = "TB_EVENT_STATE";  //用户参与的活动状态表
	public static final String EVENT_USER_USERID    = "eventUserId";     //用户ID
	public static final String EVENT_USER_EVENTTYPE = "eventType";       //活动类型
	public static final String EVENT_USER_STATE     = "eventUserState";  //用户进行的状态
	public static final String EVENT_USER_STARTTS   = "eventStartTs";    //用户开始的时间

	private static final String TAG = "Data_TB_EventState";
   
    public Data_TB_EventState(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
    
	
	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_TB_EVENTSTATE;
	}

	
	@Override
	protected LinkedHashMap<String, String> getEachKey() {
		LinkedHashMap<String, String> keyMap = new LinkedHashMap<String, String>();
		keyMap.put(EVENT_USER_EVENTTYPE, "varchar(128,0)");
		keyMap.put(EVENT_USER_STARTTS, "long(25,0)");
		keyMap.put(EVENT_USER_STATE, "varchar(128,0)");
		keyMap.put(EVENT_USER_USERID, "int(10,0)");
		
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
		String value = "";
		if(column.equals(EVENT_USER_EVENTTYPE)) {
			value = "''";
		}else if(column.equals(EVENT_USER_STARTTS)) {
			value = "0";
		}else if(column.equals(EVENT_USER_STATE)) {
			value = "''";
		}else if(column.equals(EVENT_USER_USERID)) {
			value = "0";
		}
		return value;
	}

	
	@Override
	public <T> boolean addData(T obj) {
		if(obj instanceof Data_EventState) {
			Data_EventState data = (Data_EventState)obj;
			ContentValues values = new ContentValues();
			values.put(EVENT_USER_EVENTTYPE, data.getEventType());
			values.put(EVENT_USER_STARTTS, data.getUserStartTs());
			values.put(EVENT_USER_STATE, data.getUserState());
			values.put(EVENT_USER_USERID, data.getUserId());
			
			return insert(values);
		}
		return false;
	}
}
