package com.ihealth.aijiakang.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

import iHealth.AiJiaKang.MI.MyApplication;

/**
 * Created by lanbaoshi on 15/11/24.
 */
public class AppConfig {
    private static final String APP_CONFIG_FILE = "App_Config_File";
    private static final String APP_CONFIG_VALUE_INTRO = "App_Config_Value_Intro";
    private static final String APP_CONFIG_VALUE_AUTO_LOGIN = "App_Config_Value_AutoLogin";
    private static final String APP_CONFIG_VALUE_IMAGE_URL = "App_Config_Value_ImageUrl";
    private static final String APP_CONFIG_VALUE_IMAGE_LINK_URL = "App_Config_Value_Image_LinkUrl";
    private static final String APP_CONFIG_VALUE_ADVER_END_TIME = "App_Config_Value_Adver_EndTime";
    private static final String APP_CONFIG_VALUE_TESTING_VOICE = "App_Config_Value_Testing_Voice";
    private static final String APP_CONFIG_VALUE_RESULT_VOICE = "App_Config_Value_Result_Voice";
    public static final int VOICE_ON = 1;
    public static final int VOICE_OFF = -1;
    private static AppConfig mInstance = null;

    public synchronized static AppConfig getInstance() {
        if (mInstance == null) {
            mInstance = new AppConfig();
        }

        return mInstance;
    }

    public String getConfigIntroduction(Context context) {
        if(context == null){
            return "";
        }

        SharedPreferences mySharedPreferences= context.getSharedPreferences(APP_CONFIG_FILE,
                Context.MODE_PRIVATE);

        return mySharedPreferences.getString(APP_CONFIG_VALUE_INTRO, "");
    }

    public void setConfigIntroduction(Context context, String value) {
        if(context == null){
            return;
        }

        SharedPreferences mySharedPreferences= context.getSharedPreferences(APP_CONFIG_FILE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(APP_CONFIG_VALUE_INTRO, value);
        editor.commit();
    }

    public int getConfigAutoLogin(Context context) {
        if(context == null){
            return -1;
        }
        SharedPreferences mySharedPreferences= context.getSharedPreferences(APP_CONFIG_FILE,
                Context.MODE_PRIVATE);

        return mySharedPreferences.getInt(APP_CONFIG_VALUE_AUTO_LOGIN, 0);
    }

    public void setConfigAutoLogin(Context context, int value) {
        if(context == null) {
            return;
        }
        SharedPreferences mySharedPreferences= context.getSharedPreferences(APP_CONFIG_FILE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putInt(APP_CONFIG_VALUE_AUTO_LOGIN, value);
        editor.commit();
    }

    public void setConfigAdverImageUrl(Context context, String value) {
        if(context == null) {
            return;
        }
        SharedPreferences mySharedPreferences= context.getSharedPreferences(APP_CONFIG_FILE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(APP_CONFIG_VALUE_IMAGE_URL, value);
        editor.commit();
    }

    public String getConfigAdverImageUrl(Context context) {
        if(context == null){
            return "";
        }
        SharedPreferences mySharedPreferences= context.getSharedPreferences(APP_CONFIG_FILE,
                Context.MODE_PRIVATE);

        return mySharedPreferences.getString(APP_CONFIG_VALUE_IMAGE_URL, "");
    }

    public void setConfigAdverImageLinkUrl(Context context, String value) {
        if(context == null) {
            return;
        }
        SharedPreferences mySharedPreferences= context.getSharedPreferences(APP_CONFIG_FILE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(APP_CONFIG_VALUE_IMAGE_LINK_URL, value);
        editor.commit();
    }

    public String getConfigAdverImageLinkUrl(Context context) {
        if(context == null){
            return "";
        }
        SharedPreferences mySharedPreferences= context.getSharedPreferences(APP_CONFIG_FILE,
                Context.MODE_PRIVATE);

        return mySharedPreferences.getString(APP_CONFIG_VALUE_IMAGE_LINK_URL, "");
    }

    public void setConfigValueAdverEndTime(Context context, long value) {
        if(context == null) {
            return;
        }
        SharedPreferences mySharedPreferences= context.getSharedPreferences(APP_CONFIG_FILE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putLong(APP_CONFIG_VALUE_ADVER_END_TIME, value);
        editor.commit();
    }

    public long getConfigAdverEndTime(Context context) {
        if(context == null){
            return -1;
        }
        SharedPreferences mySharedPreferences= context.getSharedPreferences(APP_CONFIG_FILE,
                Context.MODE_PRIVATE);

        return mySharedPreferences.getLong(APP_CONFIG_VALUE_ADVER_END_TIME, -1);
    }

    public void setConfigValueTestingVoice(Context context,int value){
        if(context == null){
            return;
        }

        SharedPreferences mySharedPreferences= context.getSharedPreferences(APP_CONFIG_FILE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putInt(APP_CONFIG_VALUE_TESTING_VOICE, value);
        editor.commit();
    }

    public int getConfigValueTestingVoice(Context context){
        if(context == null){
            return VOICE_ON;
        }

        SharedPreferences mySharedPreferences= context.getSharedPreferences(APP_CONFIG_FILE,
                Context.MODE_PRIVATE);

        return mySharedPreferences.getInt(APP_CONFIG_VALUE_TESTING_VOICE, VOICE_ON);
    }

    public void setConfigValueResultVoice(Context context,int value){
        if(context == null){
            return;
        }

        SharedPreferences mySharedPreferences= context.getSharedPreferences(APP_CONFIG_FILE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putInt(APP_CONFIG_VALUE_RESULT_VOICE, value);
        editor.commit();
    }

    public int getConfigValueResultVoice(Context context){
        if(context == null){
            return VOICE_ON;
        }

        SharedPreferences mySharedPreferences= context.getSharedPreferences(APP_CONFIG_FILE,
                Context.MODE_PRIVATE);

        return mySharedPreferences.getInt(APP_CONFIG_VALUE_RESULT_VOICE, VOICE_ON);
    }
}
