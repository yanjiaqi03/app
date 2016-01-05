package com.ihealth.aijiakang.database.table;

import java.util.LinkedHashMap;

import com.ihealth.aijiakang.database.DataBaseHelper;
import com.ihealth.aijiakang.database.DataBaseTools;
import com.ihealth.aijiakang.database.data.Data_UserToken;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * 用户Token表
 * 
 * @author licheng
 */
public class Data_TB_UserToken extends DataBaseTools {
	// 用户Token字段
	public static final String TABLE_TB_USERTOKEN = "TB_USERTOKEN";
	public static final String USERTOKEN_IHEALTHID = "iHealthID";
	public static final String USERTOKEN_REGIONHOST = "RegionHost";
	public static final String USERTOKEN_ACCESSTOKEN = "AccessToken";
	public static final String USERTOKEN_REFRESHTOKEN = "RefreshToken";
	
	private static final String TAG = "Data_TB_UserToken";
	
	public Data_TB_UserToken(Context context) {
		super(context);
	}

	/* (non-Javadoc)
	 * @see com.ihealth.aijiakang.db.tools.DataBaseTools#getTableName()
	 */
	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_TB_USERTOKEN;
	}

	/* (non-Javadoc)
	 * @see com.ihealth.aijiakang.db.tools.DataBaseTools#getEachKey()
	 */
	@Override
	protected LinkedHashMap<String, String> getEachKey() {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> keyMap = new LinkedHashMap<String, String>();
		keyMap.put(USERTOKEN_ACCESSTOKEN, "varchar(128) default ''");
		keyMap.put(USERTOKEN_IHEALTHID, "varchar(128)");
		keyMap.put(USERTOKEN_REFRESHTOKEN, "varchar(128) default ''");
		keyMap.put(USERTOKEN_REGIONHOST, "varchar(128) default ''");
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
		if(column.equals(USERTOKEN_ACCESSTOKEN)) {
			value = "''";
		}else if(column.equals(USERTOKEN_IHEALTHID)) {
			value = "''";
		}else if(column.equals(USERTOKEN_REFRESHTOKEN)) {
			value = "''";
		}else if(column.equals(USERTOKEN_REGIONHOST)) {
			value = "''";
		}
		return value;
	}

	
	@Override
	public <T> boolean addData(T obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Data_UserToken) {
			Data_UserToken data = (Data_UserToken)obj;
			ContentValues values = new ContentValues();
			values.put(USERTOKEN_ACCESSTOKEN, data.getAccessToken());
			values.put(USERTOKEN_IHEALTHID, data.getiHealthID());
			values.put(USERTOKEN_REFRESHTOKEN, data.getRefreshToken());
			values.put(USERTOKEN_REGIONHOST, data.getRegionHost());
			
			return insert(values);
		}
		return false;

	}
	
}
