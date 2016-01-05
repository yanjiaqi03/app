package com.ihealth.aijiakang.database.table;

import java.util.LinkedHashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.ihealth.aijiakang.database.DataBaseHelper;
import com.ihealth.aijiakang.database.DataBaseTools;
import com.ihealth.aijiakang.database.data.Data_ShareToMail;

public class Data_TB_ShareToMail extends DataBaseTools {
	// 分享email字段
	public static final String TABLE_TB_SHARETOMAIL = "TB_ShareToMail";
	public static final String SHARETOMAIL_MAILADDRESS = "mailAddress";
	public static final String SHARETOMAIL_IHEALTHCLOUD = "iHealthCloud";
	public static final String SHARETOMAIL_123 = "people";

	private static final String TAG = "Data_TB_ShareToMail";
	
	public Data_TB_ShareToMail(Context context) {
		super(context);
		// TODO Auto-generated constructor s

	}
	
	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_TB_SHARETOMAIL;
	}

	
	@Override
	protected LinkedHashMap<String, String> getEachKey() {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> keyMap = new LinkedHashMap<String, String>();
		keyMap.put(SHARETOMAIL_IHEALTHCLOUD, "varchar(128,0)");
		keyMap.put(SHARETOMAIL_MAILADDRESS, "varchar(128,0)");
		
		return keyMap;
	}

	/* (non-Javadoc)
	 * @see com.ihealth.aijiakang.db.tools.DataBaseTools#getPrimaryKey()
	 */
	@Override
	protected String getPrimaryKey() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ihealth.aijiakang.db.tools.DataBaseTools#getHelperObject()
	 */
	@Override
	protected SQLiteOpenHelper getHelperObject() {
		// TODO Auto-generated method stub
		return DataBaseHelper.getInstance(getContext());
	}

	
	@Override
	protected String onUpgradeColumn(String column) {
		// TODO Auto-generated method stub
		String value = "";
		if(column.equals(SHARETOMAIL_IHEALTHCLOUD)) {
			value = "''";
		}else if(column.equals(SHARETOMAIL_MAILADDRESS)) {
			value = "''";
		}
		return value;
	}

	
	@Override
	public <T> boolean addData(T obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Data_ShareToMail) {
			Data_ShareToMail data = (Data_ShareToMail)obj;
			ContentValues values = new ContentValues();
			values.put(SHARETOMAIL_IHEALTHCLOUD, data.getiHealthCloud());
			values.put(SHARETOMAIL_MAILADDRESS, data.getMailAddress());
			
			return insert(values);
		}
		return false;
	}

	
}
