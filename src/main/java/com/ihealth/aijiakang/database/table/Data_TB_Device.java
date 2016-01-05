package com.ihealth.aijiakang.database.table;

import java.util.LinkedHashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.ihealth.aijiakang.database.DataBaseHelper;
import com.ihealth.aijiakang.database.DataBaseTools;
import com.ihealth.aijiakang.database.data.Data_Device;

public class Data_TB_Device extends DataBaseTools {
	
	//用户连接过的BP3L
	public static final String TABLE_TB_BP3LDEVICE = "TB_BP3L";//BP3L设备
	public static final String DEVICE_MAC          = "deviceMac";//设备地址（唯一）
	public static final String DEVICE_ISCONNECTED  = "deviceIsUsed";//设备是否被连接上了
	public static final String DEVICE_FRE          = "deviceUsedTimes";//设备使用频率（次数）
	
	private static final String TAG = "Data_TB_Device";
   
    public Data_TB_Device(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
    

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_TB_BP3LDEVICE;
	}

	
	@Override
	protected LinkedHashMap<String, String> getEachKey() {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> keyMap = new LinkedHashMap<String, String>();
		keyMap.put(DEVICE_FRE, "long(25,0)");
		keyMap.put(DEVICE_ISCONNECTED, "int(10,0)");
		keyMap.put(DEVICE_MAC, "varchar(128,0)");
		
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
		if(column.equals(DEVICE_FRE)) {
			value = "0";
		}else if(column.equals(DEVICE_ISCONNECTED)) {
			value = "0";
		}else if(column.equals(DEVICE_MAC)) {
			value = "''";
		}
		return value;
	}

	
	@Override
	public <T> boolean addData(T obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Data_Device) {
			Data_Device data = (Data_Device)obj;
			ContentValues values = new ContentValues();
			values.put(DEVICE_FRE, data.getTimes());
			values.put(DEVICE_ISCONNECTED, data.getIsConnected());
			values.put(DEVICE_MAC, data.getMac());
			return insert(values);
		}
		return false;
	}
    
}
