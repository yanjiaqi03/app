package com.ihealth.aijiakang.database.table;

import java.util.LinkedHashMap;

import com.ihealth.aijiakang.database.DataBaseHelper;
import com.ihealth.aijiakang.database.DataBaseTools;
import com.ihealth.aijiakang.database.data.Data_BPMeasureResult;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public class Data_TB_BPMeasureResult_Up extends DataBaseTools {
	
	// 血压字段待上传
	public static final String TABLE_TB_BPRESULT_UP = "TB_BPResult_up";
	public static final String BPRESULT_BPMEASUREID_UP = "bpMeasureID";
	public static final String BPRESULT_IHEALTHCLOUD_UP = "iHealthCloud";
	public static final String BPRESULT_SYS_UP = "sys";
	public static final String BPRESULT_DIA_UP = "dia";
	public static final String BPRESULT_PULSE_UP = "pulse";
	public static final String BPRESULT_BPLEVEL_UP = "bpLevel";
	public static final String BPRESULT_ISIHB_UP = "isIHB";
	public static final String BPRESULT_WAVELET_UP = "wavelet";
	public static final String BPRESULT_MEASURETYPE_UP = "measureType";
	public static final String BPRESULT_BPMEASUREDATE_UP = "bpMeasureDate";
	public static final String BPRESULT_BPNOTE_UP = "bpNote";
	public static final String BPRESULT_DEVICETYPE_UP = "deviceType";
	public static final String BPRESULT_BPMDEVICEID_UP = "bpmDeviceID";
	public static final String BPRESULT_WHO_UP = "wHO";
	public static final String BPRESULT_CHANGETYPE_UP = "changeType";
	public static final String BPRESULT_LASTCHANGETIME_UP = "lastChangeTime";
	public static final String BPRESULT_BPDATAID_UP = "bpDataID";
	public static final String BPRESULT_DATACREATETIME_UP = "dataCreatTime";
	public static final String BPRESULT_LAT_UP = "lat";
	public static final String BPRESULT_LON_UP = "lon";
	public static final String BPRESULT_TIMEZONE_UP = "timeZone";
	public static final String BPRESULT_BPMOOD_UP = "bpMood";
	public static final String BPRESULT_TEMP_UP = "temp";
	public static final String BPRESULT_WEATHER_UP = "weather";
	public static final String BPRESULT_HUMIDITY_UP = "humidity";
	public static final String BPRESULT_VISIBILITY_UP = "visibility";
	public static final String BPRESULT_BPACTIVITY_UP = "bpActivity";
	public static final String BPRESULT_USEDUSERID_UP = "bpUsedUserid";
	public static final String BPRESULT_NOTECHANGETIME_UP = "NoteChangeTime";
	public static final String BPRESULT_CARE_JSON_UP = "Care_Json";
	public static final String BPRESULT_CONTENT_JSON_UP = "Content_Json";
	public static final String BPRESULT_TAKEPILL_UP = "takePill";//liujun add ver3 
    public static final String BPRESULT_PERSONALIZED_UP = "personalized";  //liujun add ver4 
    public static final String BPRESULT_ISDISPLAY_UP = "isDisplay";  //liujun add ver4 -- 控制上云
    
    private static final String TAG = "Data_TB_BPMeasureResult_Up";
   
    
    public Data_TB_BPMeasureResult_Up(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
    
	
	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_TB_BPRESULT_UP;
	}
	
	@Override
	protected LinkedHashMap<String, String> getEachKey() {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> keyMap = new LinkedHashMap<String, String>();
		keyMap.put(BPRESULT_BPMEASUREID_UP, "varchar(128,0)");
		keyMap.put(BPRESULT_IHEALTHCLOUD_UP, "varchar(128,0)");
		keyMap.put(BPRESULT_SYS_UP, "float(8,0) default 120.0");
		keyMap.put(BPRESULT_DIA_UP, "float(8,0) default 80.0");
		keyMap.put(BPRESULT_PULSE_UP, "int(4,0) default 70");
		keyMap.put(BPRESULT_BPLEVEL_UP, "int(4,0)");
		keyMap.put(BPRESULT_ISIHB_UP, "int(4,0)");
		keyMap.put(BPRESULT_WAVELET_UP, "varchar(128,0)");
		keyMap.put(BPRESULT_MEASURETYPE_UP, "int(4,0)");
		keyMap.put(BPRESULT_BPMEASUREDATE_UP, "long(10,0)");
		keyMap.put(BPRESULT_BPNOTE_UP, "varchar(1000,0)");
		keyMap.put(BPRESULT_DEVICETYPE_UP, "varchar(128,0)");
		keyMap.put(BPRESULT_BPMDEVICEID_UP, "varchar(128,0)");
		keyMap.put(BPRESULT_WHO_UP, "int(4,0)");
		keyMap.put(BPRESULT_CHANGETYPE_UP, "int(4,0)"); 
		keyMap.put(BPRESULT_LASTCHANGETIME_UP, "long(10,0)");
		keyMap.put(BPRESULT_BPDATAID_UP, "varchar(128)");
		keyMap.put(BPRESULT_DATACREATETIME_UP, "long(10,0)");
		keyMap.put(BPRESULT_LAT_UP, "double(8,0)");
		keyMap.put(BPRESULT_LON_UP, "double(8,0)"); 
		keyMap.put(BPRESULT_TIMEZONE_UP, "float(8,0)");
		keyMap.put(BPRESULT_BPMOOD_UP, "int(4,0)"); 
		keyMap.put(BPRESULT_TEMP_UP, "varchar(128,0)"); 
		keyMap.put(BPRESULT_WEATHER_UP, "varchar(128,0)"); 
		keyMap.put(BPRESULT_HUMIDITY_UP, "varchar(128,0)");
		keyMap.put(BPRESULT_VISIBILITY_UP, "varchar(128,0)"); 
		keyMap.put(BPRESULT_BPACTIVITY_UP, "int(4,0)");
		keyMap.put(BPRESULT_USEDUSERID_UP, "int(4,0) default 0");
		keyMap.put(BPRESULT_NOTECHANGETIME_UP, "long(10,0) default 0");
		keyMap.put(BPRESULT_CARE_JSON_UP, "varchar(128,0)");
		keyMap.put(BPRESULT_CONTENT_JSON_UP, "varchar(128,0)");
		keyMap.put(BPRESULT_TAKEPILL_UP, "int(4,0)");
		keyMap.put(BPRESULT_PERSONALIZED_UP, "varchar(1024,0)");
		keyMap.put(BPRESULT_ISDISPLAY_UP, "int(4,0)");
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
		if(column.equals(BPRESULT_BPMEASUREID_UP)) {
			value = "''";
		}else if(column.equals(BPRESULT_IHEALTHCLOUD_UP)) {
			value = "''";
		}else if(column.equals(BPRESULT_SYS_UP)) {
			value = "120.0";
		}else if(column.equals(BPRESULT_DIA_UP)) {
			value = "80.0";
		}else if(column.equals(BPRESULT_PULSE_UP)) {
			value = "70";
		}
		else if(column.equals(BPRESULT_BPLEVEL_UP)) {
			value = "0";
		}
		else if(column.equals(BPRESULT_ISIHB_UP)) {
			value = "0";
		}
		else if(column.equals(BPRESULT_WAVELET_UP)) {
			value = "''";
		}
		else if(column.equals(BPRESULT_MEASURETYPE_UP)) {
			value = "0";
		}
		else if(column.equals(BPRESULT_BPMEASUREDATE_UP)) {
			value = "0";
		}
		else if(column.equals(BPRESULT_BPNOTE_UP)) {
			value = "''";
		}
		else if(column.equals(BPRESULT_DEVICETYPE_UP)) {
			value = "''";
		}
		else if(column.equals(BPRESULT_BPMDEVICEID_UP)) {
			value = "''";
		}
		else if(column.equals(BPRESULT_WHO_UP)) {
			value = "0";
		}
		else if(column.equals(BPRESULT_CHANGETYPE_UP)) {
			value = "0";
		} 
		else if(column.equals(BPRESULT_LASTCHANGETIME_UP)) {
			value = "0";
		}
		else if(column.equals(BPRESULT_BPDATAID_UP)) {
			value = "''";
		}
		else if(column.equals(BPRESULT_DATACREATETIME_UP)) {
			value = "0";
		}
		else if(column.equals(BPRESULT_LAT_UP)) {
			value = "0";
		}
		else if(column.equals(BPRESULT_LON_UP)) {
			value = "0";
		} 
		else if(column.equals(BPRESULT_TIMEZONE_UP)) {
			value = "0";
		}
		else if(column.equals(BPRESULT_BPMOOD_UP)) {
			value = "0";
		} 
		else if(column.equals(BPRESULT_TEMP_UP)) {
			value = "''";
		} 
		else if(column.equals(BPRESULT_WEATHER_UP)) {
			value = "''";
		} 
		else if(column.equals(BPRESULT_HUMIDITY_UP)) {
			value = "''";
		}
		else if(column.equals(BPRESULT_VISIBILITY_UP)) {
			value = "''";
		} 
		else if(column.equals(BPRESULT_BPACTIVITY_UP)) {
			value = "0";
		}
		else if(column.equals(BPRESULT_USEDUSERID_UP)) {
			value = "0";
		}
		else if(column.equals(BPRESULT_NOTECHANGETIME_UP)) {
			value = "0";
		}
		else if(column.equals(BPRESULT_CARE_JSON_UP)) {
			value = "''";
		}
		else if(column.equals(BPRESULT_CONTENT_JSON_UP)) {
			value = "''";
		}
		else if(column.equals(BPRESULT_TAKEPILL_UP)) {
			value = "0";
		}
		else if(column.equals(BPRESULT_PERSONALIZED_UP)) {
			value = "''";
		}
		else if(column.equals(BPRESULT_ISDISPLAY_UP)){
			value = "0";
		}
		return value;
	}
	
	@Override
	public <T> boolean addData(T obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Data_BPMeasureResult) {
			Data_BPMeasureResult data_BPMeasureResult = (Data_BPMeasureResult)obj;
			ContentValues values = new ContentValues();
			values.put(BPRESULT_BPACTIVITY_UP, data_BPMeasureResult.getBpActivity());
			values.put(BPRESULT_BPDATAID_UP, data_BPMeasureResult.getBpDataID());
			values.put(BPRESULT_BPLEVEL_UP, data_BPMeasureResult.getBpLevel());
			values.put(BPRESULT_BPMDEVICEID_UP, data_BPMeasureResult.getBpmDeviceID());
			values.put(BPRESULT_BPMEASUREDATE_UP, data_BPMeasureResult.getBpMeasureDate());
			values.put(BPRESULT_BPMEASUREID_UP, data_BPMeasureResult.getBpMeasureID());
			values.put(BPRESULT_BPMOOD_UP, data_BPMeasureResult.getBpMood());
			values.put(BPRESULT_BPNOTE_UP, data_BPMeasureResult.getBpNote());
			values.put(BPRESULT_CARE_JSON_UP, data_BPMeasureResult.getCare_Json());
			values.put(BPRESULT_CHANGETYPE_UP, data_BPMeasureResult.getChangeType());
			values.put(BPRESULT_CONTENT_JSON_UP, data_BPMeasureResult.getContent_Json());
			values.put(BPRESULT_DATACREATETIME_UP, data_BPMeasureResult.getDataCreatTime());
			values.put(BPRESULT_DEVICETYPE_UP, data_BPMeasureResult.getDeviceType());
			values.put(BPRESULT_DIA_UP, data_BPMeasureResult.getDia());
			values.put(BPRESULT_HUMIDITY_UP, data_BPMeasureResult.getHumidity());
			values.put(BPRESULT_IHEALTHCLOUD_UP, data_BPMeasureResult.getiHealthCloud());
			values.put(BPRESULT_ISDISPLAY_UP, data_BPMeasureResult.getIsDisplay());
			values.put(BPRESULT_ISIHB_UP, data_BPMeasureResult.getIsIHB());
			values.put(BPRESULT_LASTCHANGETIME_UP, data_BPMeasureResult.getLastChangeTime());
			values.put(BPRESULT_LAT_UP, data_BPMeasureResult.getLat());
			values.put(BPRESULT_LON_UP, data_BPMeasureResult.getLon());
			values.put(BPRESULT_MEASURETYPE_UP, data_BPMeasureResult.getMeasureType());
			values.put(BPRESULT_NOTECHANGETIME_UP, data_BPMeasureResult.getNoteChangeTime());
			values.put(BPRESULT_PERSONALIZED_UP, data_BPMeasureResult.getPersonalized());
			values.put(BPRESULT_PULSE_UP, data_BPMeasureResult.getPulse());
			values.put(BPRESULT_SYS_UP, data_BPMeasureResult.getSys());
			values.put(BPRESULT_TAKEPILL_UP, data_BPMeasureResult.getTakePill());
			values.put(BPRESULT_TEMP_UP, data_BPMeasureResult.getTemp());
			values.put(BPRESULT_TIMEZONE_UP, data_BPMeasureResult.getTimeZone());
			values.put(BPRESULT_USEDUSERID_UP, data_BPMeasureResult.getUsedUserID());
			values.put(BPRESULT_VISIBILITY_UP, data_BPMeasureResult.getVisibility());
			values.put(BPRESULT_WAVELET_UP, data_BPMeasureResult.getWavelet());
			values.put(BPRESULT_WEATHER_UP, data_BPMeasureResult.getWeather());
			values.put(BPRESULT_WHO_UP, data_BPMeasureResult.getwHO());
			return insert(values);
		}
		return false;
	}
}
