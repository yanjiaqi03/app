package com.ihealth.aijiakang.database;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ihealth.aijiakang.system.AJKLog;

public abstract class DataBaseTools {
	private Context mContext = null;
	private static final String TAG = "DataBaseTools";

	public DataBaseTools(Context context) {
		if (context != null) {
			this.mContext = context.getApplicationContext();
		}
	}

	/**
	 * @author yanjiaqi 获取Context
	 * @since 2015.10.08
	 */
	public Context getContext() {
		return mContext;
	}
	
	public void setContext(Context context) {
		mContext = context;
	}
	/**
	 * @author yanjiaqi 获取表的名称
	 * @since 2015.10.06
	 */
	protected abstract String getTableName();

	/**
	 * @author yanjiaqi 获取表的组成
	 * @return HashMap<String, String> 第一个String key 第二个String 数据类型
	 * @since 2015.10.06
	 */
	protected abstract LinkedHashMap<String, String> getEachKey();

	/**
	 * @author yanjiaqi 获取表的约束key(约束key是唯一的)
	 * @since 2015.10.06
	 * @return String
	 */
	protected abstract String getPrimaryKey();

	/**
	 * @author yanjiaqi 获取子DataBaseHelper对象
	 * @since 2015.10.06
	 * @return SQLiteOpenHelper
	 */
	protected abstract SQLiteOpenHelper getHelperObject();

	/**
	 * @author yanjiaqi 为所有字段赋默认值
	 * @since 2015.10.06
	 * @return String
	 */
	protected abstract String onUpgradeColumn(String column);
	
	/**
	 * 添加数据
	 * @author YanJiaqi
	 * @param obj
	 * @return
	 */
	public abstract <T> boolean addData(T obj);


	@SuppressWarnings("rawtypes")
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE IF NOT EXISTS ";
		String tableName = getTableName();
		LinkedHashMap<String, String> map = getEachKey();
		if (map == null) {
			return;
		}
		sql += tableName + " (";

		Iterator iter = map.entrySet().iterator();
		Map.Entry entry = null;
		int i = 0;
		while (iter.hasNext()) {
			entry = (Map.Entry) iter.next();
			if (i != 0) {
				sql += ",";
			}
			sql += (entry.getKey() + " " + entry.getValue());
			i++;
		}

		String primaryKey = getPrimaryKey();
		if (primaryKey != null && primaryKey.length() > 0) {
			sql += (",PRIMARY KEY(" + primaryKey + ")");
		}
		sql += ");";

		try {
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Boolean isTableExist = checkTableExist(db, getTableName());
//		Log.i("DataBase", "isTableExist -> " + isTableExist);
		if (!isTableExist) {
			// create new table
			onCreate(db);
			return;
		}
		Boolean isTableChanged = checkTableChanged(db, getTableName(), getEachKey());
//		Log.i("DataBase", "isTableChanged -> " + isTableChanged);
//		if (!isTableChanged) {
//			return;
//		}

		// rename to temp table
		renameToTemp(db);
		// create new table
		onCreate(db);
		// copy data
		String sql = getTableName() + " (";

		LinkedHashMap<String, String> map = getEachKey();
		Iterator iter = map.entrySet().iterator();
		Map.Entry entry = null;
		String dataString = "";
		int i = 0;
		while (iter.hasNext()) {
			entry = (Map.Entry) iter.next();

			if (i != 0) {
				sql += ",";
				dataString += ",";
			}
			sql += (String) entry.getKey();

			Boolean result = checkColumnExist(db, "temp_" + getTableName(), (String) entry.getKey());

			if (!result) {
				dataString += onUpgradeColumn((String) entry.getKey());
			} else {
				dataString += (String) entry.getKey();
			}

			i++;
		}
		sql += ")";

		sql = "insert into " + sql + " select "  + dataString + " from temp_" + getTableName();
		AJKLog.i(TAG, "sql = " + sql);
		db.execSQL(sql);
		// delete temp table
		deleteTemp(db);
	}

	/**
	 * 检查某表列是否存在
	 * 
	 * @param db
	 * @param tableName
	 *            表名
	 * @param columnName
	 *            列名
	 * @return
	 */
	private boolean checkColumnExist(SQLiteDatabase db, String tableName, String columnName) {
		boolean result = false;
		if (db == null || tableName == null || columnName == null) {
			return result;
		}
		Cursor cursor = null;
		try {
			// 查询一行
			cursor = db.rawQuery("SELECT * FROM " + tableName.trim() + " LIMIT 0", null);
			result = cursor != null && cursor.getColumnIndex(columnName.trim()) != -1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != cursor && !cursor.isClosed()) {
				cursor.close();
			}
		}

		return result;
	}

	/**
	 * 检查某表是否存在
	 * 
	 * @param db
	 * @param tableName 表名
	 * @return
	 */
	private boolean checkTableExist(SQLiteDatabase db, String tableName) {
		boolean result = false;
		if (db == null || tableName == null) {
			return result;
		}
		Cursor cursor = null;
		try {
			cursor = db.rawQuery("select count(*) from sqlite_master where type ='table' and name ='" + tableName.trim() + "'", null);
			if (cursor.moveToNext()) {
				int count = cursor.getInt(0);
				if (count > 0) {
					result = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != cursor && !cursor.isClosed()) {
				cursor.close();
			}
		}

		return result;
	}

	/**
	 * 检查某表是否变化
	 * 
	 * @param db
	 * @param tableName 表名
	 * @return
	 */

	private boolean checkTableChanged(SQLiteDatabase db, String tableName, LinkedHashMap<String, String> map) {
		Boolean result = false;
		if (db == null || tableName == null || map == null) {
			return result;
		}

		Iterator iter = map.entrySet().iterator();
		Map.Entry entry = null;
		while (iter.hasNext()) {
			entry = (Map.Entry) iter.next();

			Boolean exist = checkColumnExist(db, getTableName(), (String) entry.getKey());

			if (!exist) {
				result = true;
				break;
			}
		}

		return result;
	}

	/**
	 * @author yanjiaqi 向表中插入数据
	 * @param values
	 * @return true or false
	 * @since 2015.10.06
	 **/
	public synchronized Boolean insert(ContentValues values) {
		long result = 0;
		if (getHelperObject() == null) {
			return false;
		}
		try {
			result = getHelperObject().getWritableDatabase().insert(getTableName(), null, values);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result >= 0;
	}

	/**
	 * @author yanjiaqi 表中查询数据
	 * @param selection
	 *            条件语句
	 * @return cursor
	 * @since 2015.10.06
	 **/
	public synchronized Cursor search(String selection) {
		if (getHelperObject() == null) {
			return null;
		}
		Cursor cur = null;

		try {
			cur = getHelperObject().getReadableDatabase().query(getTableName(), null, selection, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cur;
	}

	/**
	 * @author yanjiaqi 表中查询数据
	 * @param columns
	 *            查找的列名
	 * @return cursor
	 * @since 2015.10.06
	 **/
	public synchronized Cursor search(String[] columns) {
		if (getHelperObject() == null) {
			return null;
		}
		Cursor cur = null;

		try {
			cur = getHelperObject().getReadableDatabase().query(getTableName(), columns, null, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cur;
	}

	/**
	 * @author yanjiaqi 表中查询数据
	 * @param selection
	 *            selectionArgs eq. "name=?,age=?" ["A",B+""]
	 * @return cursor
	 * @since 2015.10.06
	 **/
	public synchronized Cursor search(String selection, String[] selectionArgs) {
		if (getHelperObject() == null) {
			return null;
		}

		Cursor cur = null;
		try {
			cur = getHelperObject().getReadableDatabase().query(getTableName(), null, selection, selectionArgs, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cur;
	}
	
	/**
	 * @author lanbaoshi 根据选择条件，按照生序或者降序，取一定条数的数据
	 * @param selection
	 * @param orderBy
	 * @param limit 
	 * @return cursor
	 * @since 2015.11.18
	 */
	public synchronized Cursor search(String selection, String orderBy, String limit) {
		if (getHelperObject() == null) {
			return null;
		}

		Cursor cur = null;
		try {
			cur = getHelperObject().getReadableDatabase().query(getTableName(), null, selection, null, null, null, orderBy, limit);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cur;
	}
	/**
	 * @author yanjiaqi
	 * @param conditionStr
	 * @return 删除表中某条数据的方法
	 * @since 2015.10.06
	 */
	public synchronized Boolean deleteData(String conditionStr) {
		if (getHelperObject() == null) {
			return false;
		}
		Boolean iResult = false;
		String sql = "";
		if (conditionStr != null && conditionStr.length() > 0) {
			sql = "DELETE FROM  " + getTableName() + " where " + conditionStr;
		} else {
			return false;
		}
		try {
			getHelperObject().getWritableDatabase().execSQL(sql);
			iResult = true;
		} catch (SQLException e) {
			e.printStackTrace();
			iResult = false;
		} finally {

		}
		return iResult;
	}

	/**
	 * @author yanjiaqi
	 * @return 删除当前表的所有数据
	 * @since 2015.10.06
	 */
	public synchronized Boolean deleteTable() {
		if (getHelperObject() == null) {
			return false;
		}
		Boolean isResult = false;
		String sql = "";
		sql = "DELETE FROM " + getTableName();

		try {
			getHelperObject().getWritableDatabase().execSQL(sql);
			isResult = true;
		} catch (SQLException e) {
			e.printStackTrace();
			isResult = false;
		} finally {

		}
		return isResult;
	}

	/**
	 * @author yanjiaqi 删除所有表的数据
	 * @return
	 * @since 2015.10.06
	 */
	public synchronized Boolean deleteAllTable() {
		if (getHelperObject() == null) {
			return false;
		}
		Boolean isResult = false;
		Cursor cursor = getHelperObject().getWritableDatabase().rawQuery("select name from sqlite_master where type='table' order by name", null);
		while (cursor.moveToNext()) {
			// 遍历出表名
			String name = cursor.getString(0);
			String sql = "DELETE FROM " + name;

			try {
				getHelperObject().getWritableDatabase().execSQL(sql);
				isResult = true;
			} catch (SQLException e) {
				e.printStackTrace();
				isResult = false;
			} finally {

			}
		}
		return isResult;
	}
	/**
	 * 批量插入数据
	 * @author lanbaoshi
	 * created at 15/11/25 下午4:49
	 */
	public  synchronized <T> void addHugeData(List<T> dataList) {
		SQLiteDatabase db = getHelperObject().getWritableDatabase();
		db.beginTransaction();
		for(int i=0; i< dataList.size(); i++) {
			addData(dataList.get(i));
		}
		db.setTransactionSuccessful(); // 设置事务处理成功，不设置会自动回滚不提交
		db.endTransaction(); // 处理完成
		db.close();
	}
	/**
	 * @param conditionStr
	 * @param valueStr
	 * @return 修改数据
	 * @author yanjiaqi
	 * @since 2015.10.06
	 */
	public synchronized Boolean updateData(String conditionStr, String valueStr) {
		Boolean isResult = false;
		String sql = "";
		if (valueStr.length() > 0) {

			if (conditionStr != null && conditionStr.length() > 0) {
				sql = "UPDATE " + getTableName() + " SET " + valueStr + " where " + conditionStr + ";";
			} else {
				isResult = false;
			}
		}
		try {
			getHelperObject().getWritableDatabase().execSQL(sql);
			isResult = true;
		} catch (SQLException e) {
			e.printStackTrace();
			isResult = false;
		} finally {
		}
		return isResult;
	}

	protected void renameToTemp(SQLiteDatabase db) {
		db.execSQL("alter table " + getTableName() + " rename to temp_" + getTableName());
	}

	protected void deleteTemp(SQLiteDatabase db) {
		db.execSQL("drop table if exists temp_" + getTableName());
	}
}
