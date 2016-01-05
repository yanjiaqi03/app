package com.ihealth.aijiakang.sharedpreference;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.ihealth.aijiakang.activity.user.RelationshipActivity;
import com.ihealth.aijiakang.system.AJKLog;

/**
 * Created by YanJiaqi on 15/12/7
 */
public class PushConfig {
    private final String TAG = "PushConfig";
    private static PushConfig mInstance = null;
    private static final String PUSH_CONFIG_FILE = "Push_Config_File";
    private static final String PUSH_CONFIG_KEY = "Push_Config_Key";
    public static final String PUSH_CONFIG_VALUE_NONE = "Push_Config_Value_None";
    public static final String PUSH_CONFIG_VALUE_ADD_FAMILY = "Push_Config_Value_Add_Family";

    public static synchronized PushConfig getInstance(){
        if(mInstance == null){
            mInstance = new PushConfig();
        }

        return mInstance;
    }

    /**
     * 获取推送事件
     * Author YanJiaqi
     * Created at 15/12/7 下午8:21
     */

    public String getPushCount(Context context){
        if(context == null){
            return PUSH_CONFIG_VALUE_NONE;
        }
        SharedPreferences mySharedPreferences= context.getSharedPreferences(PUSH_CONFIG_FILE,
                Context.MODE_PRIVATE);

        return mySharedPreferences.getString(PUSH_CONFIG_KEY, PUSH_CONFIG_VALUE_NONE);
    }

    /**
     * 设置推送事件
     * Author YanJiaqi
     * Created at 15/12/7 下午8:22
     */

    public void setPushCount(Context context,String count){
        if(context == null){
            return;
        }

        SharedPreferences mySharedPreferences= context.getSharedPreferences(PUSH_CONFIG_FILE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(PUSH_CONFIG_KEY, count);
        editor.commit();
    }

    /**
     * 清楚推送事件
     * Author YanJiaqi
     * Created at 15/12/7 下午8:26
     */

    public void clearPushCount(Context context){
        if(context == null){
            return;
        }

        SharedPreferences mySharedPreferences= context.getSharedPreferences(PUSH_CONFIG_FILE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(PUSH_CONFIG_KEY, PUSH_CONFIG_VALUE_NONE);
        editor.commit();
    }

    /**
     * 执行由于推送导致的界面跳转
     * Author YanJiaqi
     * Created at 15/12/7 下午9:02
     */

    public void doPushIntent(Context context){
        if(context == null){
            return;
        }
        String count = getPushCount(context);
        AJKLog.i(TAG,"count -> " + count);
        clearPushCount(context);
        if(count.equals(PUSH_CONFIG_VALUE_ADD_FAMILY)){
            Intent intent = new Intent(context, RelationshipActivity.class);
            context.startActivity(intent);
        }
    }
}
