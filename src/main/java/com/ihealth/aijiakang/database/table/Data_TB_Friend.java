package com.ihealth.aijiakang.database.table;

import java.util.LinkedHashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.ihealth.aijiakang.database.DataBaseHelper;
import com.ihealth.aijiakang.database.DataBaseTools;
import com.ihealth.aijiakang.database.data.Data_Friend;

/**
 * 用户好友表
 * @author licheng
 */
public class Data_TB_Friend extends DataBaseTools {
	//用户好友表
	public static final String TABLE_TB_USERINFO_FRIEND = "TB_USERINFO_FRIEND";
	public static final String USERINFO_FRIEND_FRIENDUSERID = "FriendUserId";
	public static final String USERINFO_FRIEND_REMARK = "Remark";
	public static final String USERINFO_FRIEND_NICKNAME = "NickName";
	public static final String USERINFO_FRIEND_HEADIMG = "HeadImg";
	public static final String USERINFO_FRIEND_TS = "TS";
	public static final String USERINFO_FRIEND_IHEALTHID = "iHealthID";
	public static final String USERINFO_FRIEND_USERID = "UserID";         //liujun add ver2

	private static final String TAG = "Data_TB_Friend";
   
    public Data_TB_Friend(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
    
	
	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_TB_USERINFO_FRIEND;
	}

	
	@Override
	protected LinkedHashMap<String, String> getEachKey() {
		LinkedHashMap<String, String> keyMap = new LinkedHashMap<String, String>();
		keyMap.put(USERINFO_FRIEND_FRIENDUSERID, "int(10,0) default 0");
		keyMap.put(USERINFO_FRIEND_HEADIMG, "varchar(200,0) default ''");
		keyMap.put(USERINFO_FRIEND_IHEALTHID, "varchar(128,0) default ''");
		keyMap.put(USERINFO_FRIEND_NICKNAME, "varchar(128,0) default ''");
		keyMap.put(USERINFO_FRIEND_REMARK, "varchar(128,0) default ''");
		keyMap.put(USERINFO_FRIEND_TS, "long(25,0)");
		keyMap.put(USERINFO_FRIEND_USERID, "int(10,0) default 0");
		
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
		if(column.equals(USERINFO_FRIEND_FRIENDUSERID)) {
			value = "0";
		}else if(column.equals(USERINFO_FRIEND_HEADIMG)) {
			value = "''";
		}else if(column.equals(USERINFO_FRIEND_IHEALTHID)) {
			value = "''";
		}else if(column.equals(USERINFO_FRIEND_NICKNAME)) {
			value = "''";
		}else if(column.equals(USERINFO_FRIEND_REMARK)) {
			value = "''";
		}else if(column.equals(USERINFO_FRIEND_TS)) {
			value = "0";
		}else if(column.equals(USERINFO_FRIEND_USERID)) {
			value = "0";
		}
		return value;
	}

	
	@Override
	public <T> boolean addData(T obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Data_Friend) {
			Data_Friend data = (Data_Friend)obj;
			ContentValues values = new ContentValues();
			values.put(USERINFO_FRIEND_FRIENDUSERID, data.getFriendUserId());
			values.put(USERINFO_FRIEND_HEADIMG, data.getHeadImg());
			values.put(USERINFO_FRIEND_IHEALTHID, data.getIhealthID());
			values.put(USERINFO_FRIEND_NICKNAME, data.getNickName());
			values.put(USERINFO_FRIEND_REMARK, data.getRemark());
			values.put(USERINFO_FRIEND_TS, data.getTS());
			values.put(USERINFO_FRIEND_USERID, data.getUserId());
			return insert(values);
		}
		return false;
	}
}
