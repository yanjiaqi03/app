package com.ihealth.aijiakang.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by YanJiaqi on 15/11/29
 */
public class ResultsConfig {
    private static final String RESULTS_CONFIG_FILE = "Results_Config_File";
    //记录选择查看成员
    private static final String RESULTS_CONFIG_VALUE_SELECT_WHO = "Results_Config_Value_Select_Who";

    private static ResultsConfig mInstance = null;

    public synchronized static ResultsConfig getInstance(){
        if(mInstance == null){
            mInstance = new ResultsConfig();
        }

        return mInstance;
    }

    /**
     * 获取记录选择的用户
     * Author YanJiaqi
     * Created at 15/11/29 上午12:25
     */

    public int getConfigResultsSelectWho(Context context){
        int currentUserId = UserConfig.getInstance().getConfigUserID(context);
        if(context == null){
            return currentUserId;
        }

        SharedPreferences mySharedPreferences= context.getSharedPreferences(RESULTS_CONFIG_FILE,
                Context.MODE_PRIVATE);

        return mySharedPreferences.getInt(RESULTS_CONFIG_VALUE_SELECT_WHO,currentUserId);
    }
    /**
     * 设置记录选择的用户
     * Author YanJiaqi
     * Created at 15/11/29 上午12:46
     */

    public void setConfigResultsSelectWho(Context context,int userid){
        if(context == null){
            return;
        }

        SharedPreferences mySharedPreferences= context.getSharedPreferences(RESULTS_CONFIG_FILE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putInt(RESULTS_CONFIG_VALUE_SELECT_WHO, userid);
        editor.commit();
    }
}
