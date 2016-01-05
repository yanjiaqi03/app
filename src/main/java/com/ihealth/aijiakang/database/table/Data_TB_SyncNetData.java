package com.ihealth.aijiakang.database.table;

import java.util.LinkedHashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.ihealth.aijiakang.database.DataBaseHelper;
import com.ihealth.aijiakang.database.DataBaseTools;
import com.ihealth.aijiakang.database.data.Data_SyncNetData;

public class Data_TB_SyncNetData extends DataBaseTools {
	// 同步时间字段
	public static final String TABLE_TB_SYNCINFO        = "TB_SyncTime";
	public static final String SYNCINFO_IHEALTHID       = "iHealthID";
	public static final String SYNCINFO_USERID          = "userID";
	public static final String SYNCINFO_BPDSYNCTIME     = "BPSyncTime";
	public static final String SYNCINFO_CARESYNCTIME    = "CareTime";
	public static final String SYNCINFO_COMMENTSYNCTIME = "CommentTime";
	public static final String SYNCINFO_NEWSTIME        = "NewsTime";
	public static final String SYNCINFO_REMINDTIME      = "RemindTime";//9 添加提醒时间戳
	public static final String SYNCINFO_AM8TIME         = "AM8Time";//10 添加早8点时间戳
	public static final String SYNCINFO_BPDSYNCTIME_TOP = "BPSyncTimeTop";//15 添加逆向下数据的时间戳
	
	private static final String TAG = "Data_TB_SyncNetData";
    
    
    public Data_TB_SyncNetData(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
    
	
	
	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_TB_SYNCINFO;
	}

	
	@Override
	protected LinkedHashMap<String, String> getEachKey() {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> keyMap = new LinkedHashMap<String, String>();
		keyMap.put(SYNCINFO_AM8TIME, "long(10,0)");
		keyMap.put(SYNCINFO_BPDSYNCTIME, "long(10,0)");
		keyMap.put(SYNCINFO_BPDSYNCTIME_TOP, "long(10,0)");
		keyMap.put(SYNCINFO_CARESYNCTIME, "long(10,0)");
		keyMap.put(SYNCINFO_COMMENTSYNCTIME, "long(10,0)");
		keyMap.put(SYNCINFO_IHEALTHID, "varchar(128,0)");
		keyMap.put(SYNCINFO_NEWSTIME, "long(10,0)");
		keyMap.put(SYNCINFO_REMINDTIME, "long(10,0)");
		keyMap.put(SYNCINFO_USERID, "int(10,0)");
		
		return keyMap;
	}

	
	@Override
	protected String getPrimaryKey() {
		// TODO Auto-generated method stub
		return SYNCINFO_USERID;
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
		if(column.equals(SYNCINFO_AM8TIME)) {
			value = "0";
		}else if(column.equals(SYNCINFO_BPDSYNCTIME)) {
			value = "0";
		}else if(column.equals(SYNCINFO_BPDSYNCTIME_TOP)) {
			value = "0";
		}else if(column.equals(SYNCINFO_CARESYNCTIME)) {
			value = "0";
		}else if(column.equals(SYNCINFO_COMMENTSYNCTIME)) {
			value = "0";
		}else if(column.equals(SYNCINFO_IHEALTHID)) {
			value = "''";
		}else if(column.equals(SYNCINFO_NEWSTIME)) {
			value = "0";
		}else if(column.equals(SYNCINFO_REMINDTIME)) {
			value = "0";
		}else if(column.equals(SYNCINFO_USERID)) {
			value = "0";
		}
		return value;
	}

	
	@Override
	public <T> boolean addData(T obj) {
		if(obj instanceof Data_SyncNetData) {
			Data_SyncNetData data = (Data_SyncNetData)obj;
			ContentValues values = new ContentValues();
			values.put(SYNCINFO_AM8TIME, data.getAM8Time());
			values.put(SYNCINFO_BPDSYNCTIME, data.getBpSyncTime());
			values.put(SYNCINFO_BPDSYNCTIME_TOP, data.getBpSyncTimeMin());
			values.put(SYNCINFO_CARESYNCTIME, data.getCareSyncTime());
			values.put(SYNCINFO_COMMENTSYNCTIME, data.getCommentSyncTime());
			values.put(SYNCINFO_IHEALTHID, data.getiHealthID());
			values.put(SYNCINFO_NEWSTIME, data.getNewsSyncTime());
			values.put(SYNCINFO_REMINDTIME, data.getRemindSyncTime());
			values.put(SYNCINFO_USERID, data.getUserID());
			return insert(values);
		}
		return false;
	}


}
