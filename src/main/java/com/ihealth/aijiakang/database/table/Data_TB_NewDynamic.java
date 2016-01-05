package com.ihealth.aijiakang.database.table;

import java.util.LinkedHashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.ihealth.aijiakang.database.DataBaseHelper;
import com.ihealth.aijiakang.database.DataBaseTools;
import com.ihealth.aijiakang.database.data.Data_NewDynamic;

public class Data_TB_NewDynamic extends DataBaseTools {
	//动态

	public static final String TABLE_TB_NEWDYNAMIC = "TB_NEWDYNAMIC";		
	public static final String TABLE_TB_SPONSORID = "SponsorID";		
	public static final String TABLE_TB_RECEIVERID = "receiverID";		
	public static final String TABLE_TB_CONTENT = "content";		
	public static final String TABLE_TB_TS = "timestemp";		
	public static final String TABLE_TB_LEFTNUMBER = "leftnumber";		
	public static final String TABLE_TB_TYPE = "type";

	
	private static final String TAG = "Data_TB_NewDynamic";
    
    public Data_TB_NewDynamic(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
    
	
	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_TB_NEWDYNAMIC;
	}

	
	@Override
	protected LinkedHashMap<String, String> getEachKey() {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> keyMap = new LinkedHashMap<String, String>();
		keyMap.put(TABLE_TB_CONTENT, "varchar(128,0)");
		keyMap.put(TABLE_TB_LEFTNUMBER, "int(4,0) default 1");
		keyMap.put(TABLE_TB_RECEIVERID, "varchar(128,0)");
		keyMap.put(TABLE_TB_SPONSORID, "varchar(128,0)");
		keyMap.put(TABLE_TB_TS, "long(25,0)");
		keyMap.put(TABLE_TB_TYPE, "int(4,0)");
		
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
		if(column.equals(TABLE_TB_CONTENT)) {
			value = "''";
		}else if(column.equals(TABLE_TB_LEFTNUMBER)) {
			value = "1";
		}else if(column.equals(TABLE_TB_RECEIVERID)) {
			value = "''";
		}else if(column.equals(TABLE_TB_SPONSORID)) {
			value = "''";
		}else if(column.equals(TABLE_TB_TS)) {
			value = "0";
		}else if(column.equals(TABLE_TB_TYPE)) {
			value = "0";
		}
		return value;
	}

	
	@Override
	public <T> boolean addData(T obj) {
		if(obj instanceof Data_NewDynamic) {
			Data_NewDynamic data = (Data_NewDynamic)obj;
			ContentValues values = new ContentValues();
			values.put(TABLE_TB_CONTENT, data.getContent());
			values.put(TABLE_TB_LEFTNUMBER, data.getLeftNumber());
			values.put(TABLE_TB_RECEIVERID, data.getReceiverID());
			values.put(TABLE_TB_SPONSORID, data.getSponsorID());
			values.put(TABLE_TB_TS, data.getTS());
			values.put(TABLE_TB_TYPE, data.getType());
			
			return insert(values);
		}
		return false;
	}
}
