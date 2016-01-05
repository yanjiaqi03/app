package com.ihealth.aijiakang.database.table;

import java.util.LinkedHashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.ihealth.aijiakang.database.DataBaseHelper;
import com.ihealth.aijiakang.database.DataBaseTools;
import com.ihealth.aijiakang.database.data.Data_PHONE;

public class Data_TB_PHONE extends DataBaseTools {
	//通讯录里是否是用户
	public static final String TABLE_TB_PHONE   = "TB_PHONE";       //用户手机通讯录表
	public static final String PHONE_USERID     = "phoneUserId";    //用户ID（当前用户）
	public static final String PHONE_NUM        = "phoneNum";       //电话号码
	public static final String PHONE_CONTACT_ID = "phoneContactId"; //电话本的照片
	public static final String PHONE_NAME       = "phoneName";      //电话本里的名称
	public static final String PHONE_ISUSER     = "phoneIsUser";    //是否是iHealth用户
	public static final String PHONE_ISFRIEND   = "phoneIsFriend";  //是否是好友
	
	private static final String TAG = "Data_TB_PHONE";

    public Data_TB_PHONE(Context context) {
    	super(context);
    }

	
	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_TB_PHONE;
	}

	
	@Override
	protected LinkedHashMap<String, String> getEachKey() {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> keyMap = new LinkedHashMap<String, String>();
		keyMap.put(PHONE_CONTACT_ID, "int(10,0)");
		keyMap.put(PHONE_ISFRIEND, "int(10,0)");
		keyMap.put(PHONE_ISUSER, "int(10,0)");
		keyMap.put(PHONE_NAME, "varchar(128,0)");
		keyMap.put(PHONE_NUM, "varchar(128,0)");
		keyMap.put(PHONE_USERID, "int(10,0)");
		
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
		if(column.equals(PHONE_CONTACT_ID)) {
			value = "0";
		}else if(column.equals(PHONE_ISFRIEND)) {
			value = "0";
		}else if(column.equals(PHONE_ISUSER)) {
			value = "0";
		}else if(column.equals(PHONE_NAME)) {
			value = "''";
		}else if(column.equals(PHONE_NUM)) {
			value = "''";
		}else if(column.equals(PHONE_USERID)) {
			value = "0";
		}
		return value;
	}

	
	@Override
	public <T> boolean addData(T obj) {
		if(obj instanceof Data_PHONE) {
			Data_PHONE data = (Data_PHONE)obj;
			ContentValues values = new ContentValues();
			values.put(PHONE_CONTACT_ID, data.getContactId());
			values.put(PHONE_ISFRIEND, data.getIsFriend());
			values.put(PHONE_ISUSER, data.getIsUser());
			values.put(PHONE_NAME, data.getPhoneName());
			values.put(PHONE_NUM, data.getPhoneNum());
			values.put(PHONE_USERID, data.getUserId());
			
			return insert(values);
		}
		return false;
	}
}
