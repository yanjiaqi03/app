package com.ihealth.aijiakang.database.table;

import java.util.LinkedHashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.ihealth.aijiakang.database.DataBaseHelper;
import com.ihealth.aijiakang.database.DataBaseTools;
import com.ihealth.aijiakang.database.data.Data_Tip;

public class Data_TB_Tip extends DataBaseTools {
	//健康小贴士
	public static final String TABLE_TB_TIP = "TB_TIP";     // 健康贴士
	public static final String TIP_ID       = "tipId";      // 贴士ID
	public static final String TIP_CONTENT  = "tipContent"; // 贴士内容
	public static final String TIP_TS       = "tipTS";      // 贴士TS

    private static final String TAG = "Data_TB_Tip";
    
    public Data_TB_Tip(Context context) {
    	super(context);
    }

	
	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_TB_TIP;
	}

	
	@Override
	protected LinkedHashMap<String, String> getEachKey() {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> keyMap = new LinkedHashMap<String, String>();
		keyMap.put(TIP_CONTENT, "varchar(128,0)");
		keyMap.put(TIP_ID, "int(10,0)");
		keyMap.put(TIP_TS, "long(25,0)");
		
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
		if(column.equals(TIP_CONTENT)) {
			value = "''";
		}else if(column.equals(TIP_ID)) {
			value = "0";
		}else if(column.equals(TIP_TS)) {
			value = "0";
		}
		return value;
	}

	
	@Override
	public <T> boolean addData(T obj) {
		if(obj instanceof Data_Tip) {
			Data_Tip data = (Data_Tip)obj;
			ContentValues values = new ContentValues();
			values.put(TIP_CONTENT, data.getTipContent());
			values.put(TIP_ID, data.getTipId());
			values.put(TIP_TS, data.getTipTS());
			
			return insert(values);
		}
		return false;
	}
    
}
