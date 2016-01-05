package com.ihealth.aijiakang.database.table;

import java.util.LinkedHashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.ihealth.aijiakang.database.DataBaseHelper;
import com.ihealth.aijiakang.database.DataBaseTools;
import com.ihealth.aijiakang.database.data.Data_Task;

public class Data_TB_Task extends DataBaseTools {
	//用户任务
	public static final String TABLE_TB_TASK = "TB_TASK";    // 用户任务表
	public static final String TASK_ID       = "taskId";     // 任务ID
	public static final String TASK_USERID   = "taskUserId"; // 任务的用户ID
	public static final String TASK_TYPE     = "taskType";   // 任务的类型
	public static final String TASK_CONTENT  = "taskContent";// 任务内容
	public static final String TASK_STATE    = "taskState";  // 任务状态
	public static final String TASK_ISREAD   = "taskIsRead"; // 是否已读

    private static final String TAG = "Data_TB_Task";
    
    public Data_TB_Task(Context context) {
    	super(context);
    }

	
	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_TB_TASK;
	}

	/* (non-Javadoc)
	 * @see com.ihealth.aijiakang.db.tools.DataBaseTools#getEachKey()
	 */
	@Override
	protected LinkedHashMap<String, String> getEachKey() {
		LinkedHashMap<String, String> keyMap = new LinkedHashMap<String, String>();
		keyMap.put(TASK_CONTENT, "varchar(128,0)");
		keyMap.put(TASK_ID, "int(10,0)");
		keyMap.put(TASK_ISREAD, "int(10,0)");
		keyMap.put(TASK_STATE, "int(10,0)");
		keyMap.put(TASK_TYPE, "varchar(128,0)");
		keyMap.put(TASK_USERID, "int(10,0)");
		
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
		if(column.equals(TASK_CONTENT)) {
			value = "''";
		}else if(column.equals(TASK_ID)) {
			value = "0";
		}else if(column.equals(TASK_ISREAD)) {
			value = "0";
		}else if(column.equals(TASK_STATE)) {
			value = "0";
		}else if(column.equals(TASK_TYPE)) {
			value = "''";
		}else if(column.equals(TASK_USERID)) {
			value = "0";
		}
		return value;
	}

	
	@Override
	public <T> boolean addData(T obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Data_Task) {
			Data_Task data = (Data_Task)obj;
			ContentValues values = new ContentValues();
			values.put(TASK_CONTENT, data.getContent());
			values.put(TASK_ID, data.getTaskId());
			values.put(TASK_ISREAD, data.getIsRead());
			values.put(TASK_STATE, data.getTaskState());
			values.put(TASK_TYPE, data.getType());
			values.put(TASK_USERID, data.getUserId());
			
			return insert(values);
		}
		return false;
	}
}
