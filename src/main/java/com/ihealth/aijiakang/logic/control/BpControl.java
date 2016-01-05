package com.ihealth.aijiakang.logic.control;

import android.content.Context;

import com.ihealth.aijiakang.sharedpreference.BpConfig;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/29
 */
public class BpControl {
    private static BpControl mInstance = null;
    public static BpControl getInstance(){
        if(mInstance == null){
            mInstance = new BpControl();
        }

        return mInstance;
    }

    /**
     * 获取血压单位
     * Author YanJiaqi
     * Created at 15/11/29 上午1:08
     */

    public String getUnit(Context context){
        String unit = "";

        int value = BpConfig.getInstance().getConfigBpUnit(context);
        switch (value){
            case BpConfig.MMHG:
                unit = context.getResources().getString(R.string.mmHg);
                break;
            case BpConfig.KPA:
                unit = context.getResources().getString(R.string.kpa);
                break;
        }

        return unit;
    }
}
