package com.ihealth.aijiakang.database.table;

import java.util.LinkedHashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.ihealth.aijiakang.database.DataBaseHelper;
import com.ihealth.aijiakang.database.DataBaseTools;
import com.ihealth.aijiakang.database.data.Data_Family_Member;

/**
 * 用户家人表
 * @author licheng
 */
public class Data_TB_Family_Member extends DataBaseTools {
	//用户家人表 liujun add ver3
	public static final String TABLE_TB_FAMILY     = "TB_FAMILY";
	public static final String FAMILY_FAMILYID     = "FamilyId";
	public static final String FAMILY_USERID       = "UserId";
	public static final String FAMILY_HEADIMG      = "HeadImg";
	public static final String FAMILY_REMARK       = "FamilyRemark";
	public static final String FAMILY_TS           = "FamilyTS";
	public static final String FAMILY_UN           = "FamilyUN";
	public static final String FAMILY_TOKEN        = "FamilyToken";
	public static final String FAMILY_REFRESHTOKEN = "FamilyRefreshToken";
	//2.0新增字段
	public static final String FAMILY_USERNAME     = "FamilyUserName";
	public static final String FAMILY_USERWEIGHT   = "FamilyUserWeight";
	public static final String FAMILY_USERHEIGHT   = "FamilyUserHeight";
	public static final String FAMILY_USERBIRTHDAY = "FamilyUserBirthday";
	public static final String FAMILY_USERGENDER   = "FamilyUserGender";

	private static final String TAG = "Data_TB_Family_Member";
    
    public Data_TB_Family_Member(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
    
	
	
	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_TB_FAMILY;
	}

	@Override
	protected LinkedHashMap<String, String> getEachKey() {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> keyMap = new LinkedHashMap<String, String>();
		keyMap.put(FAMILY_FAMILYID, "int(10,0)");
		keyMap.put(FAMILY_HEADIMG, "varchar(200,0) default ''");
		keyMap.put(FAMILY_REFRESHTOKEN, "varchar(128,0) default ''");
		keyMap.put(FAMILY_REMARK, "varchar(128,0) default ''");
		keyMap.put(FAMILY_TOKEN, "varchar(128,0) default ''");
		keyMap.put(FAMILY_TS, "long(25,0)");
		keyMap.put(FAMILY_UN, "varchar(128,0) default ''");
		keyMap.put(FAMILY_USERID, "int(10,0)");
		keyMap.put(FAMILY_USERNAME, "varchar(128,0) default ''");
		keyMap.put(FAMILY_USERWEIGHT, "float(10,0)");
		keyMap.put(FAMILY_USERGENDER, "int(4,0) default 2");
		keyMap.put(FAMILY_USERHEIGHT, "int(8,0) default 0");
		keyMap.put(FAMILY_USERBIRTHDAY, "long(20,0)");

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
		if(column.equals(FAMILY_FAMILYID)) {
			value = "0";
		}else if(column.equals(FAMILY_HEADIMG)) {
			value = "''";
		}else if(column.equals(FAMILY_REFRESHTOKEN)) {
			value = "''";
		}else if(column.equals(FAMILY_REMARK)) {
			value = "''";
		}else if(column.equals(FAMILY_TOKEN)) {
			value = "''";
		}else if(column.equals(FAMILY_TS)) {
			value = "0";
		}else if(column.equals(FAMILY_UN)) {
			value = "''";
		}else if(column.equals(FAMILY_USERID)) {
			value = "0";
		}else if(column.equals(FAMILY_USERNAME)) {
			value = "''";
		}else if(column.equals(FAMILY_USERWEIGHT)) {
			value = "0";
		}else if(column.equals(FAMILY_USERHEIGHT)) {
			value = "0";
		}else if(column.equals(FAMILY_USERGENDER)) {
			value = "2";
		}else if(column.equals(FAMILY_USERBIRTHDAY)) {
			value = "0";
		}
		return value;
	}

	
	@Override
	public <T> boolean addData(T obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Data_Family_Member) {
			Data_Family_Member data = (Data_Family_Member)obj;
			ContentValues values = new ContentValues();
			values.put(FAMILY_FAMILYID, data.getFamilyId());
			values.put(FAMILY_HEADIMG, data.getHeadImg());
			values.put(FAMILY_REFRESHTOKEN, data.getRefreshToken());
			values.put(FAMILY_REMARK, data.getRemark());
			values.put(FAMILY_TOKEN, data.getToken());
			values.put(FAMILY_TS, data.getTS());
			values.put(FAMILY_UN, data.getUn());
			values.put(FAMILY_USERID, data.getUserId());
			values.put(FAMILY_USERNAME, data.getFamilyUserName());
			values.put(FAMILY_USERWEIGHT, data.getFamilyUserWeight());
			values.put(FAMILY_USERHEIGHT, data.getFamilyUserHeight());
			values.put(FAMILY_USERGENDER, data.getFamilyUserGender());
			values.put(FAMILY_USERBIRTHDAY, data.getFamilyUserBirthday());
			return insert(values);
		}
		return false;
	}
    
}
