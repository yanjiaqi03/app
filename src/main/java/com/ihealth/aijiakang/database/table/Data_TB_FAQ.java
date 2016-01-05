package com.ihealth.aijiakang.database.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import com.ihealth.aijiakang.database.DataBaseHelper;
import com.ihealth.aijiakang.database.DataBaseTools;
import com.ihealth.aijiakang.database.data.Data_FAQ;

import java.util.LinkedHashMap;

/**
 * Created by lanbaoshi on 15/12/9.
 */
public class Data_TB_FAQ extends DataBaseTools {

    public static final String TABLE_TB_FAQ = "TB_FAQ";
    public static final String FAQ_QUESTIONID = "QuestionId";
    public static final String FAQ_QUESTIONTS = "QuestionTs";
    public static final String FAQ_QUESTIONTITLE = "QuestionTitle";
    public static final String FAQ_QUESTIONCONTENT = "QuestionContent";

    public Data_TB_FAQ(Context context) {
        super(context);
    }

    @Override
    protected LinkedHashMap<String, String> getEachKey() {
        LinkedHashMap<String, String> keyMap = new LinkedHashMap<String, String>();
        keyMap.put(FAQ_QUESTIONCONTENT, "varchar(2048,0)");
        keyMap.put(FAQ_QUESTIONTITLE, "varchar(128,0)");
        keyMap.put(FAQ_QUESTIONID, "int(10,0)");
        keyMap.put(FAQ_QUESTIONTS, "long(25,0)");

        return keyMap;
    }

    @Override
    protected String getPrimaryKey() {
        return null;
    }

    @Override
    protected SQLiteOpenHelper getHelperObject() {
        return DataBaseHelper.getInstance(getContext());
    }

    @Override
    protected String getTableName() {
        return TABLE_TB_FAQ;
    }

    @Override
    protected String onUpgradeColumn(String column) {
        String value = "";
        if(column.equals(FAQ_QUESTIONID)) {
            value = "0";
        }else if(column.equals(FAQ_QUESTIONTS)) {
            value = "0";
        }else if(column.equals(FAQ_QUESTIONTITLE)) {
            value = "''";
        }else if(column.equals(FAQ_QUESTIONCONTENT)) {
            value = "''";
        }
        return value;
    }

    @Override
    public <T> boolean addData(T obj) {
        if(obj instanceof Data_FAQ) {
            Data_FAQ data_faq = (Data_FAQ)obj;
            ContentValues values = new ContentValues();
            values.put(FAQ_QUESTIONID, data_faq.getQuestionId());
            values.put(FAQ_QUESTIONTS, data_faq.getQuestionTS());
            values.put(FAQ_QUESTIONTITLE, data_faq.getQuestionTitle());
            values.put(FAQ_QUESTIONCONTENT, data_faq.getQuestContent());
            return insert(values);
        }
        return false;
    }
}
