package com.ihealth.aijiakang.database.table;

import java.util.LinkedHashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.ihealth.aijiakang.database.DataBaseHelper;
import com.ihealth.aijiakang.database.DataBaseTools;
import com.ihealth.aijiakang.database.data.Data_Family_Info;

/**
 * @author liujun
 * @since 2014-12-24
 * 家庭信息实体类
 */
public class Data_TB_Family_Info extends DataBaseTools {
	//liujun add from 20141224
	//家庭信息表
	public static final String TABLE_TB_FAMILYINFO = "TB_FAMILYINFO"; //家庭信息表
	public static final String FAMILYINFO_ID = "familyId";            //家庭ID
	public static final String FAMILYINFO_CREATERID = "createrId";    //创建者ID
	public static final String FAMILYINFO_FAMILYNAME = "familyName";  //家庭名称
	public static final String FAMILYINFO_LOGO = "logo";              //家庭全家福（图片的路径）
	public static final String FAMILYINFO_LOGOTS = "logoTS";          //全家福更新时间
	public static final String FAMILYINFO_VOICE = "voice";            //家庭录音
	public static final String FAMILYINFO_VOICETS = "voiceTS";        //家庭录音更新时间
	public static final String FAMILYINFO_TS = "TS";                  //家庭信息更新时间
	
	private static final String TAG = "Data_TB_Family_Info";
    
    public Data_TB_Family_Info(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
    
	
	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_TB_FAMILYINFO;
	}

	
	@Override
	protected LinkedHashMap<String, String> getEachKey() {
		LinkedHashMap<String, String> keyMap = new LinkedHashMap<String, String>();
		keyMap.put(FAMILYINFO_CREATERID, "int(4,0)");
		keyMap.put(FAMILYINFO_FAMILYNAME, "varchar(128,0)");
		keyMap.put(FAMILYINFO_ID, "int(4,0)");
		keyMap.put(FAMILYINFO_LOGO, "varchar(128,0)");
		keyMap.put(FAMILYINFO_LOGOTS, "long(25,0)");
		keyMap.put(FAMILYINFO_TS, "long(25,0)");
		keyMap.put(FAMILYINFO_VOICE, "varchar(128,0)");
		keyMap.put(FAMILYINFO_VOICETS, "long(25,0)");
		
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
		if(column.equals(FAMILYINFO_CREATERID)) {
			value = "0";
		}else if(column.equals(FAMILYINFO_FAMILYNAME)) {
			value = "''";
		}else if(column.equals(FAMILYINFO_ID)) {
			value = "0";
		}else if(column.equals(FAMILYINFO_LOGO)) {
			value = "''";
		}else if(column.equals(FAMILYINFO_TS)) {
			value = "0";
		}else if(column.equals(FAMILYINFO_VOICE)) {
			value = "''";
		}else if(column.equals(FAMILYINFO_VOICETS)) {
			value = "0";
		}
		return value;
	}

	
	@Override
	public <T> boolean addData(T obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Data_Family_Info) {
			Data_Family_Info data = (Data_Family_Info)obj;
			ContentValues values = new ContentValues();
			values.put(FAMILYINFO_CREATERID, data.getCreaterId());
			values.put(FAMILYINFO_FAMILYNAME, data.getFamilyName());
			values.put(FAMILYINFO_ID, data.getFamilyId());
			values.put(FAMILYINFO_LOGO, data.getFamilyLogo());
			values.put(FAMILYINFO_LOGOTS, data.getFamilyLogoTS());
			values.put(FAMILYINFO_TS, data.getFamilyTS());
			values.put(FAMILYINFO_VOICE, data.getFamilyVoice());
			values.put(FAMILYINFO_VOICETS, data.getFamilyVoiceTS());
			return insert(values);
		}
		return false;
	}
}
