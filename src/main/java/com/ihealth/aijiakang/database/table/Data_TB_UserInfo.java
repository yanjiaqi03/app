package com.ihealth.aijiakang.database.table;

import java.util.LinkedHashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.ihealth.aijiakang.database.DataBaseHelper;
import com.ihealth.aijiakang.database.DataBaseTools;
import com.ihealth.aijiakang.database.data.Data_UserInfo;

/**
 * 用户信息表
 * 
 * @author licheng
 */
public class Data_TB_UserInfo extends DataBaseTools {
	// 用户字段
	public static final String TABLE_TB_USERINFO = "TB_UserInfo";
	public static final String USERINFO_USERNAME = "UserName";
	public static final String USERINFO_PASSWORD = "PassWord";
	public static final String USERINFO_USERID = "UserId";
	public static final String USERINFO_BIRTHDAY = "BirthDay";
	public static final String USERINFO_EMAIL = "Email";
	public static final String USERINFO_GENDER = "Gender";
	public static final String USERINFO_ISSPORTER = "IsSporter";
	public static final String USERINFO_NAME = "Name";
	public static final String USERINFO_HEIGHT = "Height";
	public static final String USERINFO_WEIGHT = "Weight";
	public static final String USERINFO_NATION = "Nation";
	public static final String USERINFO_LANGUAGE = "Language";
	public static final String USERINFO_USERCLOUD = "UserCloud";
	public static final String USERINFO_TS = "TS";
	public static final String USERINFO_LOGO = "Logo";
	public static final String USERINFO_LOGOTS = "LogoTS";
	public static final String USERINFO_ACTIVITYLEVEL = "ActivityLevel";
	public static final String USERINFO_ISREMMBERPASSWORD = "IsRememberPassword";
	public static final String USERINFO_ANTOLOGIN = "AntoLogin";
	public static final String USERINFO_LASTTIME = "LastTime";
	public static final String USERINFO_TIMEZONE = "TimeZone";
	public static final String USERINFO_CLOUDSERIALNUMBER = "cloudSerialNumber";
	public static final String USERINFO_CLOUDUSERMAC = "cloudUserMac";
	public static final String USERINFO_BMR = "bmr";
	
    private static final String TAG = "Data_TB_UserInfo";
    
    public Data_TB_UserInfo(Context context) {
    	super(context);
    }

	
	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_TB_USERINFO;
	}

	
	@Override
	protected LinkedHashMap<String, String> getEachKey() {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> keyMap = new LinkedHashMap<String, String>();
		keyMap.put(USERINFO_ACTIVITYLEVEL, "int(4,0) default 1");
		keyMap.put(USERINFO_ANTOLOGIN, "int(4,0)");
		keyMap.put(USERINFO_BIRTHDAY, "long(20,0)");
		keyMap.put(USERINFO_BMR, "int(32,0) default 2000");
		keyMap.put(USERINFO_CLOUDSERIALNUMBER, "int(16,0)");
		keyMap.put(USERINFO_CLOUDUSERMAC, "varchar(128,0) default '-1'");
		keyMap.put(USERINFO_EMAIL, "varchar(1024,0)");
		keyMap.put(USERINFO_GENDER, "int(4,0) default 2");
		keyMap.put(USERINFO_HEIGHT, "int(8,0) default 0");
		keyMap.put(USERINFO_ISREMMBERPASSWORD, "int(4,0)");
		keyMap.put(USERINFO_ISSPORTER, "int(4,0) default 2");
		keyMap.put(USERINFO_LANGUAGE, "varchar(128,0) default ''");
		keyMap.put(USERINFO_LASTTIME, "varchar(128,0) default ''");
		keyMap.put(USERINFO_LOGO, "varchar(200,0)");
		keyMap.put(USERINFO_LOGOTS, "long(25,0)"); 
		keyMap.put(USERINFO_NAME, "varchar(50,0) default ''");
		keyMap.put(USERINFO_NATION, "varchar(128,0) default ''");
		keyMap.put(USERINFO_PASSWORD, "varchar(20,0)");
		keyMap.put(USERINFO_TIMEZONE, "varchar(128,0) default ''");
		keyMap.put(USERINFO_TS, "long(25,0)"); 
		keyMap.put(USERINFO_USERCLOUD, "int(4,0) default 1");
		keyMap.put(USERINFO_USERID, "int(10,0)"); 
		keyMap.put(USERINFO_USERNAME, "varchar(128,0)"); 
		keyMap.put(USERINFO_WEIGHT, "float(10,0)"); 
		
		return keyMap;
	}

	
	@Override
	protected String getPrimaryKey() {
		// TODO Auto-generated method stub
		return USERINFO_USERID;
	}

	
	@Override
	protected SQLiteOpenHelper getHelperObject() {
		// TODO Auto-generated method stub
		return DataBaseHelper.getInstance(getContext());
	}

	/* (non-Javadoc)
	 * @see com.ihealth.aijiakang.db.tools.DataBaseTools#onUpgradeColumn(java.lang.String)
	 */
	@Override
	protected String onUpgradeColumn(String column) {
		// TODO Auto-generated method stub
		String value = "";
		if(column.equals(USERINFO_ACTIVITYLEVEL)) {
			value = "1";
		}else if(column.equals(USERINFO_ANTOLOGIN)) {
			value = "0";
		}else if(column.equals(USERINFO_BIRTHDAY)) {
			value = "-1362332800";//给一个1930年1月1日之前的值
		}else if(column.equals(USERINFO_BMR)) {
			value = "2000";
		}else if(column.equals(USERINFO_CLOUDSERIALNUMBER)) {
			value = "0";
		}
		else if(column.equals(USERINFO_CLOUDUSERMAC)) {
			value = "'-1'";
		}
		else if(column.equals(USERINFO_EMAIL)) {
			value = "''";
		}
		else if(column.equals(USERINFO_GENDER)) {
			value = "2";
		}
		else if(column.equals(USERINFO_HEIGHT)) {
			value = "0";
		}
		else if(column.equals(USERINFO_ISREMMBERPASSWORD)) {
			value = "0";
		}
		else if(column.equals(USERINFO_ISSPORTER)) {
			value = "2";
		}
		else if(column.equals(USERINFO_LANGUAGE)) {
			value = "''";
		}
		else if(column.equals(USERINFO_LASTTIME)) {
			value = "''";
		}
		else if(column.equals(USERINFO_LOGO)) {
			value = "''";
		}
		else if(column.equals(USERINFO_LOGOTS)) {
			value = "0";
		} 
		else if(column.equals(USERINFO_NAME)) {
			value = "''";
		}
		else if(column.equals(USERINFO_NATION)) {
			value = "''";
		}
		else if(column.equals(USERINFO_PASSWORD)) {
			value = "''";
		}
		else if(column.equals(USERINFO_TIMEZONE)) {
			value = "''";
		}
		else if(column.equals(USERINFO_TS)) {
			value = "0";
		} 
		else if(column.equals(USERINFO_USERCLOUD)) {
			value = "1";
		}
		else if(column.equals(USERINFO_USERID)) {
			value = "0";
		} 
		else if(column.equals(USERINFO_USERNAME)) {
			value = "''";
		} 
		else if(column.equals(USERINFO_WEIGHT)) {
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
		if(obj instanceof Data_UserInfo) {
			Data_UserInfo data = (Data_UserInfo)obj;
			ContentValues values = new ContentValues();
			values.put(USERINFO_ACTIVITYLEVEL, data.getActivityLevel());
			values.put(USERINFO_ANTOLOGIN, data.getAntoLogin());
			values.put(USERINFO_BIRTHDAY, data.getBirthDay());
			values.put(USERINFO_BMR, data.getBmr());
			values.put(USERINFO_CLOUDSERIALNUMBER, data.getCloudSerialNumber());
			values.put(USERINFO_CLOUDUSERMAC, data.getCloudUserMac());
			values.put(USERINFO_EMAIL, data.getEmail());
			values.put(USERINFO_GENDER, data.getGender());
			values.put(USERINFO_HEIGHT, data.getHeight());
			values.put(USERINFO_ISREMMBERPASSWORD, data.getIsRememberPassword());
			values.put(USERINFO_ISSPORTER, data.getIsSporter());
			values.put(USERINFO_LANGUAGE, data.getLanguage());
			values.put(USERINFO_LASTTIME, data.getLastTime());
			values.put(USERINFO_LOGO, data.getLogo());
			values.put(USERINFO_LOGOTS, data.getLogoTS()); 
			values.put(USERINFO_NAME, data.getName());
			values.put(USERINFO_NATION, data.getNation());
			values.put(USERINFO_PASSWORD, data.getPassWord());
			values.put(USERINFO_TIMEZONE, data.getTimeZone());
			values.put(USERINFO_TS, data.getTS()); 
			values.put(USERINFO_USERCLOUD, data.getUserCloud());
			values.put(USERINFO_USERID, data.getUserId()); 
			values.put(USERINFO_USERNAME, data.getUserName()); 
			values.put(USERINFO_WEIGHT, data.getWeight()); 
			return insert(values);
		}
		return false;
	}
    
}
