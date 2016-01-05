package com.ihealth.aijiakang.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ihealth.aijiakang.database.table.Data_TB_AM8;
import com.ihealth.aijiakang.database.table.Data_TB_BPMeasureResult;
import com.ihealth.aijiakang.database.table.Data_TB_BPMeasureResult_Up;
import com.ihealth.aijiakang.database.table.Data_TB_Care_Single;
import com.ihealth.aijiakang.database.table.Data_TB_Chat_Single;
import com.ihealth.aijiakang.database.table.Data_TB_Device;
import com.ihealth.aijiakang.database.table.Data_TB_Event;
import com.ihealth.aijiakang.database.table.Data_TB_EventState;
import com.ihealth.aijiakang.database.table.Data_TB_Family_File;
import com.ihealth.aijiakang.database.table.Data_TB_Family_Info;
import com.ihealth.aijiakang.database.table.Data_TB_Family_Member;
import com.ihealth.aijiakang.database.table.Data_TB_Friend;
import com.ihealth.aijiakang.database.table.Data_TB_NewDynamic;
import com.ihealth.aijiakang.database.table.Data_TB_NewDynamic_TS;
import com.ihealth.aijiakang.database.table.Data_TB_PHONE;
import com.ihealth.aijiakang.database.table.Data_TB_Remind_Single;
import com.ihealth.aijiakang.database.table.Data_TB_SaveUser;
import com.ihealth.aijiakang.database.table.Data_TB_ShareToMail;
import com.ihealth.aijiakang.database.table.Data_TB_SyncNetData;
import com.ihealth.aijiakang.database.table.Data_TB_Task;
import com.ihealth.aijiakang.database.table.Data_TB_Tip;
import com.ihealth.aijiakang.database.table.Data_TB_Unit;
import com.ihealth.aijiakang.database.table.Data_TB_UserInfo;
import com.ihealth.aijiakang.database.table.Data_TB_UserToken;
import com.ihealth.aijiakang.system.AJKLog;

public class DataBaseHelper extends SQLiteOpenHelper {
	private final static String DB_NAME = "iHealthAiJiaKangMI.DB";
	private final static int version = 21;
	private static DataBaseHelper mDB = null;
	private Context mContext = null;

	private DataBaseHelper(Context context) {
		super(context, DB_NAME, null, version);
		if (context != null) {
			this.mContext = context.getApplicationContext();
		}
	}

	public synchronized static DataBaseHelper getInstance(Context context) {
		if (context == null) {
			return null;
		}

		if (mDB == null) {
			mDB = new DataBaseHelper(context);
		}
		
		return mDB;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		AJKLog.i("DataBaseHelper", "onCreate");
		new Data_TB_AM8(mContext).onCreate(db);
		new Data_TB_BPMeasureResult(mContext).onCreate(db);
		new Data_TB_BPMeasureResult_Up(mContext).onCreate(db);
		new Data_TB_Care_Single(mContext).onCreate(db);
		new Data_TB_Chat_Single(mContext).onCreate(db);
		new Data_TB_Device(mContext).onCreate(db);
		new Data_TB_Event(mContext).onCreate(db);
		new Data_TB_EventState(mContext).onCreate(db);
		new Data_TB_Family_File(mContext).onCreate(db);
		new Data_TB_Family_Info(mContext).onCreate(db);
		new Data_TB_Family_Member(mContext).onCreate(db);
		new Data_TB_Friend(mContext).onCreate(db);
		new Data_TB_NewDynamic(mContext).onCreate(db);
		new Data_TB_NewDynamic_TS(mContext).onCreate(db);
		new Data_TB_PHONE(mContext).onCreate(db);
		new Data_TB_Remind_Single(mContext).onCreate(db);
		new Data_TB_SaveUser(mContext).onCreate(db);
		new Data_TB_ShareToMail(mContext).onCreate(db);
		new Data_TB_SyncNetData(mContext).onCreate(db);
		new Data_TB_Task(mContext).onCreate(db);
		new Data_TB_Tip(mContext).onCreate(db);
		new Data_TB_Unit(mContext).onCreate(db);
		new Data_TB_UserInfo(mContext).onCreate(db);
		new Data_TB_UserToken(mContext).onCreate(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		AJKLog.i("DataBaseHelper", "onUpgrade");
		new Data_TB_AM8(mContext).onUpgrade(db, oldVersion, newVersion);
		new Data_TB_BPMeasureResult(mContext).onUpgrade(db, oldVersion, newVersion);
		new Data_TB_BPMeasureResult_Up(mContext).onUpgrade(db, oldVersion, newVersion);
		new Data_TB_Care_Single(mContext).onUpgrade(db, oldVersion, newVersion);
		new Data_TB_Chat_Single(mContext).onUpgrade(db, oldVersion, newVersion);
		new Data_TB_Device(mContext).onUpgrade(db, oldVersion, newVersion);
		new Data_TB_Event(mContext).onUpgrade(db, oldVersion, newVersion);
		new Data_TB_EventState(mContext).onUpgrade(db, oldVersion, newVersion);
		new Data_TB_Family_File(mContext).onUpgrade(db, oldVersion, newVersion);
		new Data_TB_Family_Info(mContext).onUpgrade(db, oldVersion, newVersion);
		new Data_TB_Family_Member(mContext).onUpgrade(db, oldVersion, newVersion);
		new Data_TB_Friend(mContext).onUpgrade(db, oldVersion, newVersion);
		new Data_TB_NewDynamic(mContext).onUpgrade(db, oldVersion, newVersion);
		new Data_TB_NewDynamic_TS(mContext).onUpgrade(db, oldVersion, newVersion);
		new Data_TB_PHONE(mContext).onUpgrade(db, oldVersion, newVersion);
		new Data_TB_Remind_Single(mContext).onUpgrade(db, oldVersion, newVersion);
		new Data_TB_SaveUser(mContext).onUpgrade(db, oldVersion, newVersion);
		new Data_TB_ShareToMail(mContext).onUpgrade(db, oldVersion, newVersion);
		new Data_TB_SyncNetData(mContext).onUpgrade(db, oldVersion, newVersion);
		new Data_TB_Task(mContext).onUpgrade(db, oldVersion, newVersion);
		new Data_TB_Tip(mContext).onUpgrade(db, oldVersion, newVersion);
		new Data_TB_Unit(mContext).onUpgrade(db, oldVersion, newVersion);
		new Data_TB_UserInfo(mContext).onUpgrade(db, oldVersion, newVersion);
		new Data_TB_UserToken(mContext).onUpgrade(db, oldVersion, newVersion);
	}
}
