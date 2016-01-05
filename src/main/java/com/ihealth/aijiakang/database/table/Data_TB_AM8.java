package com.ihealth.aijiakang.database.table;

import java.util.LinkedHashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.ihealth.aijiakang.database.DataBaseHelper;
import com.ihealth.aijiakang.database.DataBaseTools;
import com.ihealth.aijiakang.database.data.Data_AM8;

public class Data_TB_AM8 extends DataBaseTools {
	
	//“早8点”消息
	public static final String TABLE_TB_AM8  = "TB_AM8";       //早8点消息表
	public static final String AM8_ID        = "am8Id";        //早8点的数据ID
	public static final String AM8_USERID    = "am8UserId";    //用户ID（家庭创建者）
	public static final String AM8_TS        = "am8Ts";        //时间戳
	public static final String AM8_PICNAME   = "am8PicName";   //图片名称
	public static final String AM8_TITLE     = "am8Title";     //早8点标题
	public static final String AM8_CONTENT   = "am8Content";   //文字内容
	public static final String AM8_URL       = "am8Url";       //网址
	public static final String AM8_ISDISPLAY = "am8IsDisplay"; //是否显示
	
	private static final String TAG = "Data_TB_AM8";
    
    public Data_TB_AM8(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
   

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_TB_AM8;
	}

	@Override
	protected LinkedHashMap<String, String> getEachKey() {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> keyMap = new LinkedHashMap<String, String>();
		keyMap.put(AM8_CONTENT, "varchar(2048,0)");
		keyMap.put(AM8_URL, "varchar(128,0)");
		keyMap.put(AM8_TITLE, "varchar(128,0)");
		keyMap.put(AM8_ID, "int(10,0)");
		keyMap.put(AM8_USERID, "int(10,0)");
		keyMap.put(AM8_ISDISPLAY, "int(10,0)");
		keyMap.put(AM8_TS, "long(25,0)");
		keyMap.put(AM8_PICNAME, "varchar(128,0)");
		
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
		if(column.equals(AM8_ID)) {
			value = "0";
		}else if(column.equals(AM8_USERID)) {
			value = "0";
		}else if(column.equals(AM8_TS)) {
			value = "0";
		}else if(column.equals(AM8_PICNAME)) {
			value = "''";
		}else if(column.equals(AM8_TITLE)) {
			value = "''";
		}else if(column.equals(AM8_CONTENT)) {
			value = "''";
		}else if(column.equals(AM8_URL)) {
			value = "''";
		}else if(column.equals(AM8_ISDISPLAY)) {
			value = "0";
		}
		return value;
	}

	@Override
	public <T> boolean addData(T obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Data_AM8) {
			Data_AM8 data = (Data_AM8)obj;
			ContentValues values = new ContentValues();
			values.put(AM8_USERID, data.getUserId());
			values.put(AM8_ID, data.getId());
			values.put(AM8_TS, data.getTs());
			values.put(AM8_PICNAME, data.getPicName());
			values.put(AM8_TITLE, data.getTitle());
			values.put(AM8_CONTENT, data.getContent());
			values.put(AM8_URL, data.getUrl());
			values.put(AM8_ISDISPLAY, data.getIsDisplay());
			return insert(values);
		}
		return false;
	}
    
}
