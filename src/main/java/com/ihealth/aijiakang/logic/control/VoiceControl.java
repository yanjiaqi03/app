package com.ihealth.aijiakang.logic.control;

import android.content.Context;

import com.ihealth.aijiakang.sharedpreference.AppConfig;
import com.ihealth.aijiakang.widgets.SwitchImageView;

/**
 * Created by YanJiaqi on 15/11/29
 */
public class VoiceControl {
    private static VoiceControl mInstance = null;
    public static VoiceControl getInstance(){
        if(mInstance == null){
            mInstance = new VoiceControl();
        }

        return mInstance;
    }

    /**
     * 读取播报语音设置
     * Author YanJiaqi
     * Created at 15/11/29 上午1:12
     */

    public int readTestingVoice(Context context){
        int value = AppConfig.getInstance().getConfigValueTestingVoice(context);
        if(value == AppConfig.VOICE_OFF){
            return SwitchImageView.OFF_STATE;
        } else {
            return SwitchImageView.ON_STATE;
        }
    }

    /**
     * 读取结果语音播报设置
     * Author YanJiaqi
     * Created at 15/11/29 上午1:13
     */

    public int readResultVoice(Context context){
        int value = AppConfig.getInstance().getConfigValueResultVoice(context);
        if(value == AppConfig.VOICE_OFF){
            return SwitchImageView.OFF_STATE;
        } else {
            return SwitchImageView.ON_STATE;
        }
    }
}
