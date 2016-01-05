package com.ihealth.aijiakang.utils;

import android.text.format.Time;

import com.ihealth.aijiakang.system.AJKLog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lanbaoshi on 15/11/28.
 */
public class DateUtils {

    private static DateUtils mInstance;
    private Time mTime;

    private DateUtils() {
        mTime = new Time();
        mTime.setToNow();
    }

    public static DateUtils getInstance() {
        if(mInstance == null) {
            mInstance = new DateUtils();
        }
        return mInstance;
    }

    public void setTimeMillis(long time) {
        if(time >= -1262332800000l) {//1930年1月1日
            mTime.set(time);
        }else {
            mTime.year = 0;
            mTime.month = -1;
            mTime.monthDay = 0;
        }
    }

    public int getYear() {
        return mTime.year;
    }

    public int getMonth() {
       return mTime.month + 1;
    }

    public int getDay() {
        return mTime.monthDay;
    }

    public int getHour() {
        return mTime.hour;
    }

    public int getMinute() {
        return mTime.minute;
    }

    public int getSecond() {
        return mTime.second;
    }

    public long getTimeStamp(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        try {
            date = format.parse(time);
        }catch (ParseException exception) {
            exception.getStackTrace();
        }
        AJKLog.i("DateUtiles", "getTimeStamp = " + date.getTime());
        return date.getTime() / 1000;
    }
}
