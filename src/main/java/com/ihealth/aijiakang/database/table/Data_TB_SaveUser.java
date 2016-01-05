package com.ihealth.aijiakang.database.table;

import java.util.LinkedHashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.ihealth.aijiakang.database.DataBaseHelper;
import com.ihealth.aijiakang.database.DataBaseTools;
import com.ihealth.aijiakang.database.data.Data_SaveUser;

public class Data_TB_SaveUser extends DataBaseTools {
	//用户设置
	public static final String TABLE_TB_SAVEUSER = "TB_SAVEUSER"; //保存暂不显示的USERID
	public static final String SAVEUSER_USERID = "saveUserId";//用户的ID（保存者）
	public static final String SAVEUSER_SAVEID = "userSaveId";//被保存者的userId
	
    private static final String TAG = "Data_TB_SaveUser";
    
    public Data_TB_SaveUser(Context context) {
    	super(context);
    }

	
	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_TB_SAVEUSER;
	}

	
	@Override
	protected LinkedHashMap<String, String> getEachKey() {
		LinkedHashMap<String, String> keyMap = new LinkedHashMap<String, String>();
		keyMap.put(SAVEUSER_SAVEID, "int(10,0)");
		keyMap.put(SAVEUSER_USERID, "int(10,0)");
		
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
		if(column.equals(SAVEUSER_SAVEID)) {
			value = "0";
		}else if(column.equals(SAVEUSER_USERID)) {
			value = "0";
		}
		return value;
	}

	
	@Override
	public <T> boolean addData(T obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Data_SaveUser) {
			Data_SaveUser data = (Data_SaveUser)obj;
			ContentValues values = new ContentValues();
			values.put(SAVEUSER_SAVEID, data.getSaveId());
			values.put(SAVEUSER_USERID, data.getUserId());
			
			return insert(values);
		}
		return false;
	}

}
