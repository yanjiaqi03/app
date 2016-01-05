
package com.ihealth.aijiakang.database.table;

import java.util.LinkedHashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.ihealth.aijiakang.database.DataBaseHelper;
import com.ihealth.aijiakang.database.DataBaseTools;
import com.ihealth.aijiakang.database.data.Data_Remind_Single;

public class Data_TB_Remind_Single extends DataBaseTools {
	//提醒表
    public static final String TABLE_TB_REMIND    = "TB_REMIND";       
    public static final String REMIND_PHONEDATAID = "PhoneDataID";//BPID
    public static final String REMIND_USERID      = "UserId";     //本条BP数据所有者ID
    public static final String REMIND_SPONSORID   = "SponsorID";  //发起者
    public static final String REMIND_TS          = "TS";         //本条提醒TS
    public static final String REMIND_MEASURETS   = "MeasureTS";  //本条BP数据TS
    public static final String REMIND_ISEXP       = "ISEXP";      //是否过期
	
    public static final String TAG = "Data_TB_Remind_Single";
    
    public Data_TB_Remind_Single(Context context) {
    	super(context);
    }

	
	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_TB_REMIND;
	}

	
	@Override
	protected LinkedHashMap<String, String> getEachKey() {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> keyMap = new LinkedHashMap<String, String>();
		keyMap.put(REMIND_ISEXP, "int(10,0)");
		keyMap.put(REMIND_MEASURETS, "long(25,0)");
		keyMap.put(REMIND_PHONEDATAID, "varchar(128,0)");
		keyMap.put(REMIND_SPONSORID, "int(10,0)");
		keyMap.put(REMIND_TS, "long(25,0)");
		keyMap.put(REMIND_USERID, "int(10,0)");
		
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
		if(column.equals(REMIND_ISEXP)) {
			value = "0";
		}else if(column.equals(REMIND_MEASURETS)) {
			value = "0";
		}else if(column.equals(REMIND_PHONEDATAID)) {
			value = "''";
		}else if(column.equals(REMIND_SPONSORID)) {
			value = "0";
		}else if(column.equals(REMIND_TS)) {
			value = "0";
		}else if(column.equals(REMIND_USERID)) {
			value = "0";
		}
		return value;
	}

	
	@Override
	public <T> boolean addData(T obj) {
		if(obj instanceof Data_Remind_Single) {
			Data_Remind_Single data = (Data_Remind_Single)obj;
			ContentValues values = new ContentValues();
			values.put(REMIND_ISEXP, data.getIsExp());
			values.put(REMIND_MEASURETS, data.getMeasureTS());
			values.put(REMIND_PHONEDATAID, data.getPhoneDataID());
			values.put(REMIND_SPONSORID, data.getSponsorID());
			values.put(REMIND_TS, data.getTS());
			values.put(REMIND_USERID, data.getUserId());
			
			return insert(values);
		}
		return false;
	}
}
