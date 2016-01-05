package com.ihealth.aijiakang.database.table;

import java.util.LinkedHashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.ihealth.aijiakang.database.DataBaseHelper;
import com.ihealth.aijiakang.database.DataBaseTools;
import com.ihealth.aijiakang.database.data.Data_Event;

public class Data_TB_Event extends DataBaseTools {
	
	//活动详情
	public static final String TABLE_TB_EVENT = "TB_EVENT"; //父亲节活动
	public static final String EVENT_TYPE     = "eventType";//活动类型
	public static final String EVENT_IMG1     = "eventImg1";//活动第一张图片
    public static final String EVENT_IMG2     = "eventImg2";//活动第二张图片
    public static final String EVENT_IMG3     = "eventImg3";//活动第三张图片
    public static final String EVENT_IMG4     = "eventImg4";//活动第四张图片
    public static final String EVENT_URL1     = "eventUrl1";//活动第一个URL
    public static final String EVENT_URL2     = "eventUrl2";//活动第二个URL
    public static final String EVENT_URL3     = "eventUrl3";//活动第三个URL
    public static final String EVENT_URL4     = "eventUrl4";//活动第四个URL

   
    private static final String TAG = "Data_TB_Event";
   
    
    public Data_TB_Event(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
    
	
	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_TB_EVENT;
	}


	@Override
	protected LinkedHashMap<String, String> getEachKey() {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> keyMap = new LinkedHashMap<String, String>();
		keyMap.put(EVENT_IMG1, "varchar(128,0)");
		keyMap.put(EVENT_IMG2, "varchar(128,0)");
		keyMap.put(EVENT_IMG3, "varchar(128,0)");
		keyMap.put(EVENT_IMG4, "varchar(128,0)");
		keyMap.put(EVENT_TYPE, "varchar(128,0)");
		keyMap.put(EVENT_URL1, "varchar(128,0)");
		keyMap.put(EVENT_URL2, "varchar(128,0)");
		keyMap.put(EVENT_URL3, "varchar(128,0)");
		keyMap.put(EVENT_URL4, "varchar(128,0)");
		
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
		if(column.equals(EVENT_IMG1)) {
			value = "''";
		}else if(column.equals(EVENT_IMG2)) {
			value = "''";
		}else if(column.equals(EVENT_IMG3)) {
			value = "''";
		}else if(column.equals(EVENT_IMG4)) {
			value = "''";
		}else if(column.equals(EVENT_TYPE)) {
			value = "''";
		}else if(column.equals(EVENT_URL1)) {
			value = "''";
		}else if(column.equals(EVENT_URL2)) {
			value = "''";
		}else if(column.equals(EVENT_URL3)) {
			value = "''";
		}else if(column.equals(EVENT_URL4)) {
			value = "''";
		}
		return value;
	}


	@Override
	public <T> boolean addData(T obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Data_Event) {
			Data_Event data = (Data_Event)obj;
			ContentValues values = new ContentValues();
			values.put(EVENT_IMG1, data.getImg1());
			values.put(EVENT_IMG2, data.getImg2());
			values.put(EVENT_IMG3, data.getImg3());
			values.put(EVENT_IMG4, data.getImg4());
			values.put(EVENT_TYPE, data.getType());
			values.put(EVENT_URL1, data.getUrl1());
			values.put(EVENT_URL2, data.getUrl2());
			values.put(EVENT_URL3, data.getUrl3());
			values.put(EVENT_URL4, data.getUrl4());
			return insert(values);
		}
		return false;
	}
   
}
