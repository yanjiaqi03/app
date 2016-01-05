package com.ihealth.aijiakang.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/29
 */
public class BpConfig {
    private static final String BP_CONFIG_FILE = "Bp_Config_File";
    private static final String BP_CONFIG_VALUE_UNIT = "Bp_Config_Value_Unit";
    public static final int MMHG = 0;
    public static final int KPA = 1;

    private static BpConfig mInstance = null;

    public synchronized static BpConfig getInstance(){
        if(mInstance == null){
            mInstance = new BpConfig();
        }

        return mInstance;
    }

    /**
     * 获取血压单位
     * Author YanJiaqi
     * Created at 15/11/29 上午12:25
     */

    public int getConfigBpUnit(Context context){
        if(context == null){
            return MMHG;
        }
        SharedPreferences mySharedPreferences= context.getSharedPreferences(BP_CONFIG_FILE,
                Context.MODE_PRIVATE);

        return mySharedPreferences.getInt(BP_CONFIG_VALUE_UNIT, MMHG);
    }
    /**
     * 设置血压单位
     * Author YanJiaqi
     * Created at 15/11/29 上午12:46
     */

    public void setConfigBpUnit(Context context,int mUnit){
        if(context == null){
            return;
        }

        SharedPreferences mySharedPreferences= context.getSharedPreferences(BP_CONFIG_FILE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putInt(BP_CONFIG_VALUE_UNIT, mUnit);
        editor.commit();
    }
}
