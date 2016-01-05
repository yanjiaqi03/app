package com.ihealth.aijiakang.database.table;

import java.util.LinkedHashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.ihealth.aijiakang.database.DataBaseHelper;
import com.ihealth.aijiakang.database.DataBaseTools;
import com.ihealth.aijiakang.database.data.Data_Unit;

/**
 * 用户单位表
 * 
 * @author licheng
 */
public class Data_TB_Unit extends DataBaseTools {
	
	// 用户单位
	public static final String TABLE_TB_UNIT = "TB_Unit";
	public static final String UNIT_USERNAME = "UserName";
	public static final String UNIT_BPUNIT = "BPUnit";
	public static final String UNIT_BPUNITTS = "BPUnitTS";
	public static final String UNIT_BGUNIT = "BGUnit";
	public static final String UNIT_BGUNITTS = "BGUnitTS";
	public static final String UNIT_HEIGHTUNIT = "HeightUnit";
	public static final String UNIT_HEIGHTUNITTS = "HeightUnitTS";
	public static final String UNIT_WEIGHTUNIT = "WeightUnit";
	public static final String UNIT_WEIGHTUNITTS = "WeightUnitTS";
	public static final String UNIT_FOODWEIGHTUNIT = "FoodWeightUnit";
	public static final String UNIT_FOODWEIGHTUNITTS = "FoodWeightUnitTS";
	public static final String UNIT_LENGTHUNIT = "LengthUnit";
	public static final String UNIT_LENGTHUNITTS = "LengthUnitTS";
	
	private static final String TAG = "Data_TB_Unit";

	/**
	 * @param context
	 */
	public Data_TB_Unit(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_TB_UNIT;
	}

	
	@Override
	protected LinkedHashMap<String, String> getEachKey() {
		LinkedHashMap<String, String> keyMap = new LinkedHashMap<String, String>();
		keyMap.put(UNIT_BGUNIT, "int(4,0) default 1");
		keyMap.put(UNIT_BGUNITTS, "long(25,0)");
		keyMap.put(UNIT_BPUNIT, "int(4,0) default 0");
		keyMap.put(UNIT_BPUNITTS, "long(25,0)");
		keyMap.put(UNIT_FOODWEIGHTUNIT, "int(4,0) default 1");
		keyMap.put(UNIT_FOODWEIGHTUNITTS, "long(25,0)");
		keyMap.put(UNIT_HEIGHTUNIT, "int(4,0) default 1");
		keyMap.put(UNIT_HEIGHTUNITTS, "long(25,0)");
		keyMap.put(UNIT_LENGTHUNIT, "int(4,0) default 1");
		keyMap.put(UNIT_LENGTHUNITTS, "long(25,0)");
		keyMap.put(UNIT_USERNAME, "varchar(128,0)");
		keyMap.put(UNIT_WEIGHTUNIT, "int(4,0) default 1");
		keyMap.put(UNIT_WEIGHTUNITTS, "long(25,0)");
		return keyMap;
	}

	
	@Override
	protected String getPrimaryKey() {
		// TODO Auto-generated method stub
		return UNIT_USERNAME;
	}

	
	@Override
	protected SQLiteOpenHelper getHelperObject() {
		// TODO Auto-generated method stub
		return DataBaseHelper.getInstance(getContext());
	}

	
	@Override
	protected String onUpgradeColumn(String column) {
		String value = "";
		if(column.equals(UNIT_BGUNIT)) {
			value = "1";
		}else if(column.equals(UNIT_BGUNITTS)) {
			value = "0";
		}else if(column.equals(UNIT_BPUNIT)) {
			value = "0";
		}else if(column.equals(UNIT_BPUNITTS)) {
			value = "0";
		}else if(column.equals(UNIT_FOODWEIGHTUNIT)) {
			value = "1";
		}else if(column.equals(UNIT_FOODWEIGHTUNITTS)) {
			value = "0";
		}else if(column.equals(UNIT_HEIGHTUNIT)) {
			value = "1";
		}else if(column.equals(UNIT_HEIGHTUNITTS)) {
			value = "0";
		}else if(column.endsWith(UNIT_LENGTHUNIT)) {
			value = "1";
		}else if(column.equals(UNIT_LENGTHUNITTS)) {
			value = "0";
		}else if(column.equals(UNIT_USERNAME)) {
			value = "''";
		}else if(column.equals(UNIT_WEIGHTUNIT)) {
			value = "1";
		}else if(column.equals(UNIT_WEIGHTUNITTS)) {
			value = "0";
		}
		return value;
	}

	
	@Override
	public <T> boolean addData(T obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Data_Unit) {
			Data_Unit data = (Data_Unit)obj;
			ContentValues values = new ContentValues();
			values.put(UNIT_BGUNIT, data.getBGUnit());
			values.put(UNIT_BGUNITTS, data.getBGUnitTS());
			values.put(UNIT_BPUNIT, data.getBPUnit());
			values.put(UNIT_BPUNITTS, data.getBPUnitTS());
			values.put(UNIT_FOODWEIGHTUNIT, data.getFoodWeightUnit());
			values.put(UNIT_FOODWEIGHTUNITTS, data.getFoodWeightUnitTS());
			values.put(UNIT_HEIGHTUNIT, data.getHeightUnit());
			values.put(UNIT_HEIGHTUNITTS, data.getHeightUnitTS());
			values.put(UNIT_LENGTHUNIT, data.getLengthUnit());
			values.put(UNIT_LENGTHUNITTS, data.getLengthUnitTS());
			values.put(UNIT_USERNAME, data.getUserName());
			values.put(UNIT_WEIGHTUNIT, data.getWeightUnit());
			values.put(UNIT_WEIGHTUNITTS, data.getWeightUnitTS());
			return insert(values);
		}
		return false;
	}
	

}
