package com.ihealth.aijiakang.database.table;

import java.util.LinkedHashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.ihealth.aijiakang.database.DataBaseHelper;
import com.ihealth.aijiakang.database.DataBaseTools;
import com.ihealth.aijiakang.database.data.Data_NewDynamic_TS;

public class Data_TB_NewDynamic_TS extends DataBaseTools {
	//动态时间
	public static final String TABLE_TB_NEWDYNAMIC_TS = "TB_NEWDYNAMIC_TS";	
	public static final String NEWDYNAMIC_TS_TS = "NewdynamicTS_TS";		
	
	private static final String TAG = "Data_TB_NewDynamic_TS";
   
    public Data_TB_NewDynamic_TS(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
   
	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_TB_NEWDYNAMIC_TS;
	}

	
	@Override
	protected LinkedHashMap<String, String> getEachKey() {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> keyMap = new LinkedHashMap<String, String>();
		keyMap.put(NEWDYNAMIC_TS_TS, "long(25,0)");
		
		
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
		if(column.equals(NEWDYNAMIC_TS_TS)) {
			value = "0";
		}
		return value;
	}

	
	@Override
	public <T> boolean addData(T obj) {
		if(obj instanceof Data_NewDynamic_TS) {
			Data_NewDynamic_TS data = (Data_NewDynamic_TS)obj;
			ContentValues values = new ContentValues();
			values.put(NEWDYNAMIC_TS_TS, data.getTS());
			
			return insert(values);
		}
		return false;
	}

}
