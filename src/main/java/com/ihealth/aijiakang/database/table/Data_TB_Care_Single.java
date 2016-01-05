package com.ihealth.aijiakang.database.table;

import java.util.LinkedHashMap;

import com.ihealth.aijiakang.database.DataBaseHelper;
import com.ihealth.aijiakang.database.DataBaseTools;
import com.ihealth.aijiakang.database.data.Data_Care_Single;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public class Data_TB_Care_Single extends DataBaseTools {
	
	//关心表
	public static final String TABLE_TB_CARE    = "TB_CARE";		
	public static final String CARE_PHONEDATAID = "PhoneDataID";//BPID
	public static final String CARE_USERID      = "UserId";		//本条BP数据所有者ID
	public static final String CARE_SPONSORID   = "SponsorID";	//发起者
	public static final String CARE_TS          = "TS";			//本条关心数据TS
	public static final String CARE_MEASURETS   = "MeasureTS";	//本条BP数据TS

	private static final String TAG = "Data_TB_Care_Single";
  
    
    public Data_TB_Care_Single(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
    
	
	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_TB_CARE;
	}

	
	@Override
	protected LinkedHashMap<String, String> getEachKey() {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> keyMap = new LinkedHashMap<String, String>();
		keyMap.put(CARE_PHONEDATAID, "varchar(128,0)");
		keyMap.put(CARE_SPONSORID, "int(10,0)");
		keyMap.put(CARE_TS, "long(25,0)");
		keyMap.put(CARE_USERID, "int(10,0)");
		keyMap.put(CARE_MEASURETS, "long(25,0)");
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
		if(column.equals(CARE_MEASURETS)) {
			value = "0";
		}else if(column.equals(CARE_PHONEDATAID)) {
			value = "''";
		}else if(column.equals(CARE_SPONSORID)) {
			value = "0";
		}else if(column.equals(CARE_TS)) {
			value = "0";
		}else if(column.equals(CARE_USERID)) {
			value = "0";
		}
		return value;
	}

	/* (non-Javadoc)
	 * @see com.ihealth.aijiakang.db.tools.DataBaseTools#addData(java.lang.Object)
	 */
	@Override
	public <T> boolean addData(T obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Data_Care_Single) {
			Data_Care_Single data = (Data_Care_Single)obj;
			ContentValues values = new ContentValues();
			values.put(CARE_MEASURETS, data.getMeasureTS());
			values.put(CARE_PHONEDATAID, data.getPhoneDataID());
			values.put(CARE_SPONSORID, data.getSponsorID());
			values.put(CARE_TS, data.getTS());
			values.put(CARE_USERID, data.getUserId());
			return insert(values);
		}
		return false;
	}
}
