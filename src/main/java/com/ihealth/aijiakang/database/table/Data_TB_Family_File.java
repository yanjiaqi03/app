package com.ihealth.aijiakang.database.table;

import java.util.LinkedHashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.ihealth.aijiakang.database.DataBaseHelper;
import com.ihealth.aijiakang.database.DataBaseTools;
import com.ihealth.aijiakang.database.data.Data_Family_File;

public class Data_TB_Family_File extends DataBaseTools {
	//家庭文件表（语音和图片）
	public static final String TABLE_TB_FAMILYFILES = "TB_FAMILYFILES";  //家庭文件
	public static final String FAMILYFILES_LINETYPE = "lineType";        //关联的类型（个人还是家庭，如果是个人则关联的ID是个人，如果是家庭则关联的ID是家庭）
	public static final String FAMILYFILES_LINEID = "lineId";            //关联的ID
	public static final String FAMILYFILES_FILETYPE = "fileType";        //文件的类型（图片还是语音）
	public static final String FAMILYFILES_FILECODE = "fileCode";        //文件的编码（base64）
	public static final String FAMILYFILES_TS = "TS";                    //文件的TS
	
	private static final String TAG = "Data_TB_Family_File";
   
    public Data_TB_Family_File(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
    
	
	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_TB_FAMILYFILES;
	}

	
	@Override
	protected LinkedHashMap<String, String> getEachKey() {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> keyMap = new LinkedHashMap<String, String>();
		keyMap.put(FAMILYFILES_FILECODE, "varchar(128,0)");
		keyMap.put(FAMILYFILES_FILETYPE, "int(4,0)");
		keyMap.put(FAMILYFILES_LINEID, "int(4,0)");
		keyMap.put(FAMILYFILES_LINETYPE, "int(10,0)");
		keyMap.put(FAMILYFILES_TS, "long(25,0)");
		
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
		if(column.equals(FAMILYFILES_FILECODE)) {
			value = "''";
		}else if(column.equals(FAMILYFILES_FILETYPE)) {
			value = "0";
		}else if(column.equals(FAMILYFILES_LINEID)) {
			value = "0";
		}else if(column.equals(FAMILYFILES_LINETYPE)) {
			value = "0";
		}else if(column.equals(FAMILYFILES_TS)) {
			value = "0";
		}
		return value;
	}

	
	@Override
	public <T> boolean addData(T obj) {
		if(obj instanceof Data_Family_File) {
			Data_Family_File data = (Data_Family_File)obj;
			ContentValues values = new ContentValues();
			values.put(FAMILYFILES_FILECODE, data.getFileCode());
			values.put(FAMILYFILES_FILETYPE, data.getFileType());
			values.put(FAMILYFILES_LINEID, data.getLineID());
			values.put(FAMILYFILES_LINETYPE, data.getLineType());
			values.put(FAMILYFILES_TS, data.getTS());
			
			return insert(values);
		}
		return false;
	}
    
}
