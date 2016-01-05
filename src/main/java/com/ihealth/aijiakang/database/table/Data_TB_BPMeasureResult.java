package com.ihealth.aijiakang.database.table;

import java.util.LinkedHashMap;

import com.ihealth.aijiakang.database.DataBaseHelper;
import com.ihealth.aijiakang.database.DataBaseTools;
import com.ihealth.aijiakang.database.data.Data_BPMeasureResult;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public class Data_TB_BPMeasureResult extends DataBaseTools {
	// 血压字段
	public static final String TABLE_TB_BPRESULT = "TB_BPResult";
	public static final String BPRESULT_BPMEASUREID = "bpMeasureID";
	public static final String BPRESULT_IHEALTHCLOUD = "iHealthCloud";
	public static final String BPRESULT_SYS = "sys";
	public static final String BPRESULT_DIA = "dia";
	public static final String BPRESULT_PULSE = "pulse";
	public static final String BPRESULT_BPLEVEL = "bpLevel";
	public static final String BPRESULT_ISIHB = "isIHB";
	public static final String BPRESULT_WAVELET = "wavelet";
	public static final String BPRESULT_MEASURETYPE = "measureType";
	public static final String BPRESULT_BPMEASUREDATE = "bpMeasureDate";
	public static final String BPRESULT_BPNOTE = "bpNote";
	public static final String BPRESULT_DEVICETYPE = "deviceType";
	public static final String BPRESULT_BPMDEVICEID = "bpmDeviceID";
	public static final String BPRESULT_WHO = "wHO";
	public static final String BPRESULT_CHANGETYPE = "changeType";
	public static final String BPRESULT_LASTCHANGETIME = "lastChangeTime";
	public static final String BPRESULT_BPDATAID = "bpDataID";
	public static final String BPRESULT_DATACREATETIME = "dataCreatTime";
	public static final String BPRESULT_LAT = "lat";
	public static final String BPRESULT_LON = "lon";
	public static final String BPRESULT_TIMEZONE = "timeZone";
	public static final String BPRESULT_BPMOOD = "bpMood";
	public static final String BPRESULT_TEMP = "temp";
	public static final String BPRESULT_WEATHER = "weather";
	public static final String BPRESULT_HUMIDITY = "humidity";
	public static final String BPRESULT_VISIBILITY = "visibility";
	public static final String BPRESULT_BPACTIVITY = "bpActivity";
	public static final String BPRESULT_USEDUSERID = "bpUsedUserid";
	public static final String BPRESULT_NOTECHANGETIME = "NoteChangeTime";
	public static final String BPRESULT_CARE_JSON = "Care_Json";
	public static final String BPRESULT_CONTENT_JSON = "Content_Json";
	public static final String BPRESULT_TAKEPILL = "takePill";	//liujun add ver3 
	public static final String BPRESULT_PERSONALIZED = "personalized";  //liujun add ver4 
	public static final String BPRESULT_ISDISPLAY = "isDisplay";  //liujun add ver4 
	public static final String BPRESULT_TIMEZONETS = "timezoneTime"; //liujun add
	
	private static final String TAG = "Data_TB_BPMeasureResult";
    
    
    public Data_TB_BPMeasureResult(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
    
	
	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_TB_BPRESULT;
	}

	
	@Override
	protected LinkedHashMap<String, String> getEachKey() {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> keyMap = new LinkedHashMap<String, String>();
		keyMap.put(BPRESULT_BPMEASUREID, "varchar(128,0)");
		keyMap.put(BPRESULT_IHEALTHCLOUD, "varchar(128,0)");
		keyMap.put(BPRESULT_SYS, "float(8,0) default 120.0");
		keyMap.put(BPRESULT_DIA, "float(8,0) default 80.0");
		keyMap.put(BPRESULT_PULSE, "int(4,0) default 70");
		keyMap.put(BPRESULT_BPLEVEL, "int(4,0)");
		keyMap.put(BPRESULT_ISIHB, "int(4,0)");
		keyMap.put(BPRESULT_WAVELET, "varchar(2048,0)");
		keyMap.put(BPRESULT_MEASURETYPE, "int(4,0)");
		keyMap.put(BPRESULT_BPMEASUREDATE, "long(10,0)");
		keyMap.put(BPRESULT_BPNOTE, "varchar(1000,0)");
		keyMap.put(BPRESULT_DEVICETYPE, "varchar(128,0)");
		keyMap.put(BPRESULT_BPMDEVICEID, "varchar(128,0)");
		keyMap.put(BPRESULT_WHO, "int(4,0)");
		keyMap.put(BPRESULT_CHANGETYPE, "int(4,0)"); 
		keyMap.put(BPRESULT_LASTCHANGETIME, "long(10,0)");
		keyMap.put(BPRESULT_BPDATAID, "varchar(128)");
		keyMap.put(BPRESULT_DATACREATETIME, "long(10,0)");
		keyMap.put(BPRESULT_LAT, "double(8,0)");
		keyMap.put(BPRESULT_LON, "double(8,0)"); 
		keyMap.put(BPRESULT_TIMEZONE, "float(8,0)");
		keyMap.put(BPRESULT_BPMOOD, "int(4,0)"); 
		keyMap.put(BPRESULT_TEMP, "varchar(128,0)"); 
		keyMap.put(BPRESULT_WEATHER, "varchar(128,0)"); 
		keyMap.put(BPRESULT_HUMIDITY, "varchar(128,0)");
		keyMap.put(BPRESULT_VISIBILITY, "varchar(128,0)"); 
		keyMap.put(BPRESULT_BPACTIVITY, "int(4,0)");
		keyMap.put(BPRESULT_USEDUSERID, "int(4,0) default 0");
		keyMap.put(BPRESULT_NOTECHANGETIME, "long(10,0) default 0");
		keyMap.put(BPRESULT_CARE_JSON, "varchar(128,0)");
		keyMap.put(BPRESULT_CONTENT_JSON, "varchar(128,0)");
		keyMap.put(BPRESULT_TAKEPILL, "int(4,0)");
		keyMap.put(BPRESULT_PERSONALIZED, "varchar(1024,0)");
		keyMap.put(BPRESULT_ISDISPLAY, "int(4,0)");
		keyMap.put(BPRESULT_TIMEZONETS, "long(10,0)");
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
		if(column.equals(BPRESULT_BPMEASUREID)) {
			value = "''";
		}else if(column.equals(BPRESULT_IHEALTHCLOUD)) {
			value = "''";
		}else if(column.equals(BPRESULT_SYS)) {
			value = "120.0";
		}else if(column.equals(BPRESULT_DIA)) {
			value = "80.0";
		}else if(column.equals(BPRESULT_PULSE)) {
			value = "70";
		}
		else if(column.equals(BPRESULT_BPLEVEL)) {
			value = "0";
		}
		else if(column.equals(BPRESULT_ISIHB)) {
			value = "0";
		}
		else if(column.equals(BPRESULT_WAVELET)) {
			value = "''";
		}
		else if(column.equals(BPRESULT_MEASURETYPE)) {
			value = "0";
		}
		else if(column.equals(BPRESULT_BPMEASUREDATE)) {
			value = "0";
		}
		else if(column.equals(BPRESULT_BPNOTE)) {
			value = "''";
		}
		else if(column.equals(BPRESULT_DEVICETYPE)) {
			value = "''";
		}
		else if(column.equals(BPRESULT_BPMDEVICEID)) {
			value = "''";
		}
		else if(column.equals(BPRESULT_WHO)) {
			value = "0";
		}
		else if(column.equals(BPRESULT_CHANGETYPE)) {
			value = "0";
		} 
		else if(column.equals(BPRESULT_LASTCHANGETIME)) {
			value = "0";
		}
		else if(column.equals(BPRESULT_BPDATAID)) {
			value = "''";
		}
		else if(column.equals(BPRESULT_DATACREATETIME)) {
			value = "0";
		}
		else if(column.equals(BPRESULT_LAT)) {
			value = "0";
		}
		else if(column.equals(BPRESULT_LON)) {
			value = "0";
		} 
		else if(column.equals(BPRESULT_TIMEZONE)) {
			value = "0";
		}
		else if(column.equals(BPRESULT_BPMOOD)) {
			value = "0";
		} 
		else if(column.equals(BPRESULT_TEMP)) {
			value = "''";
		} 
		else if(column.equals(BPRESULT_WEATHER)) {
			value = "''";
		} 
		else if(column.equals(BPRESULT_HUMIDITY)) {
			value = "''";
		}
		else if(column.equals(BPRESULT_VISIBILITY)) {
			value = "''";
		} 
		else if(column.equals(BPRESULT_BPACTIVITY)) {
			value = "0";
		}
		else if(column.equals(BPRESULT_USEDUSERID)) {
			value = "0";
		}
		else if(column.equals(BPRESULT_NOTECHANGETIME)) {
			value = "0";
		}
		else if(column.equals(BPRESULT_CARE_JSON)) {
			value = "''";
		}
		else if(column.equals(BPRESULT_CONTENT_JSON)) {
			value = "''";
		}
		else if(column.equals(BPRESULT_TAKEPILL)) {
			value = "0";
		}
		else if(column.equals(BPRESULT_PERSONALIZED)) {
			value = "''";
		}
		else if(column.equals(BPRESULT_ISDISPLAY)){
			value = "0";
		}
		else if(column.equals(BPRESULT_TIMEZONETS)) {
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
			values.put(BPRESULT_BPACTIVITY, data_BPMeasureResult.getBpActivity());
			values.put(BPRESULT_BPDATAID, data_BPMeasureResult.getBpDataID());
			values.put(BPRESULT_BPLEVEL, data_BPMeasureResult.getBpLevel());
			values.put(BPRESULT_BPMDEVICEID, data_BPMeasureResult.getBpmDeviceID());
			values.put(BPRESULT_BPMEASUREDATE, data_BPMeasureResult.getBpMeasureDate());
			values.put(BPRESULT_BPMEASUREID, data_BPMeasureResult.getBpMeasureID());
			values.put(BPRESULT_BPMOOD, data_BPMeasureResult.getBpMood());
			values.put(BPRESULT_BPNOTE, data_BPMeasureResult.getBpNote());
			values.put(BPRESULT_CARE_JSON, data_BPMeasureResult.getCare_Json());
			values.put(BPRESULT_CHANGETYPE, data_BPMeasureResult.getChangeType());
			values.put(BPRESULT_CONTENT_JSON, data_BPMeasureResult.getContent_Json());
			values.put(BPRESULT_DATACREATETIME, data_BPMeasureResult.getDataCreatTime());
			values.put(BPRESULT_DEVICETYPE, data_BPMeasureResult.getDeviceType());
			values.put(BPRESULT_DIA, data_BPMeasureResult.getDia());
			values.put(BPRESULT_HUMIDITY, data_BPMeasureResult.getHumidity());
			values.put(BPRESULT_IHEALTHCLOUD, data_BPMeasureResult.getiHealthCloud());
			values.put(BPRESULT_ISDISPLAY, data_BPMeasureResult.getIsDisplay());
			values.put(BPRESULT_ISIHB, data_BPMeasureResult.getIsIHB());
			values.put(BPRESULT_LASTCHANGETIME, data_BPMeasureResult.getLastChangeTime());
			values.put(BPRESULT_LAT, data_BPMeasureResult.getLat());
			values.put(BPRESULT_LON, data_BPMeasureResult.getLon());
			values.put(BPRESULT_MEASURETYPE, data_BPMeasureResult.getMeasureType());
			values.put(BPRESULT_NOTECHANGETIME, data_BPMeasureResult.getNoteChangeTime());
			values.put(BPRESULT_PERSONALIZED, data_BPMeasureResult.getPersonalized());
			values.put(BPRESULT_PULSE, data_BPMeasureResult.getPulse());
			values.put(BPRESULT_SYS, data_BPMeasureResult.getSys());
			values.put(BPRESULT_TAKEPILL, data_BPMeasureResult.getTakePill());
			values.put(BPRESULT_TEMP, data_BPMeasureResult.getTemp());
			values.put(BPRESULT_TIMEZONE, data_BPMeasureResult.getTimeZone());
			values.put(BPRESULT_USEDUSERID, data_BPMeasureResult.getUsedUserID());
			values.put(BPRESULT_VISIBILITY, data_BPMeasureResult.getVisibility());
			values.put(BPRESULT_WAVELET, data_BPMeasureResult.getWavelet());
			values.put(BPRESULT_WEATHER, data_BPMeasureResult.getWeather());
			values.put(BPRESULT_WHO, data_BPMeasureResult.getwHO());
			values.put(BPRESULT_TIMEZONETS, data_BPMeasureResult.getTimezoneTS());
			return insert(values);
		}
		return false;
	}
}
