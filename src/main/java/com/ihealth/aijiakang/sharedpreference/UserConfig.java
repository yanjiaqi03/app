package com.ihealth.aijiakang.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.ihealth.aijiakang.utils.VariableUtils;

import iHealth.AiJiaKang.MI.MyApplication;

/**
 * Created by lanbaoshi on 15/11/24.
 */
public class UserConfig {
    //Guest 字段
    public final static String USER_GUEST = "Guest";
    //UserId字段
    public static final String USER_ID = "UserId";
    private static final String USER_CONFIG_FILE = "User_Config_File";
    private static final String USER_CONFIG_VALUE_CURRENT_USER = "User_Config_Value_CurrentUser";
    private static final String USER_CONFIG_VALUE_PASSWORD = "User_Config_Value_Password";
    private static final String USER_CONFIG_VALUE_USER_ID = "User_Config_Value_UserID";
    private static final String USER_CONFIG_VALUE_FAMILY_ID = "User_Config_Value_Family_ID";
    private static UserConfig mInstance = null;

    public synchronized static UserConfig getInstance() {
        if (mInstance == null) {
            mInstance = new UserConfig();
        }

        return mInstance;
    }

    public String getConfigCurrentUser(Context context) {
        if(context == null){
            return "";
        }

        SharedPreferences mySharedPreferences= context.getSharedPreferences(USER_CONFIG_FILE,
                Context.MODE_PRIVATE);

        return mySharedPreferences.getString(USER_CONFIG_VALUE_CURRENT_USER, "");
    }

    public void setConfigCurrentUser(Context context, String value) {
        if(context == null){
            return;
        }

        SharedPreferences mySharedPreferences= context.getSharedPreferences(USER_CONFIG_FILE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(USER_CONFIG_VALUE_CURRENT_USER, value);
        editor.commit();
    }
    public String getConfigPassword(Context context) {
        if(context == null){
            return "";
        }

        SharedPreferences mySharedPreferences= context.getSharedPreferences(USER_CONFIG_FILE,
                Context.MODE_PRIVATE);

        return mySharedPreferences.getString(USER_CONFIG_VALUE_PASSWORD, "");
    }

    public void setConfigPassword(Context context, String value) {
        if(context == null){
            return;
        }

        SharedPreferences mySharedPreferences= context.getSharedPreferences(USER_CONFIG_FILE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(USER_CONFIG_VALUE_PASSWORD, value);
        editor.commit();
    }

    public void setConfigUserID(Context context, int userId) {
        if(context == null){
            return;
        }
        SharedPreferences mySharedPreferences= context.getSharedPreferences(USER_CONFIG_FILE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putInt(USER_CONFIG_VALUE_USER_ID, userId);
        editor.commit();
    }

    public int getConfigUserID(Context context) {
        if(context == null){
            return -1;
        }
        SharedPreferences mySharedPreferences= context.getSharedPreferences(USER_CONFIG_FILE,
                Context.MODE_PRIVATE);
        return mySharedPreferences.getInt(USER_CONFIG_VALUE_USER_ID, -1);
    }

    public int getConfigFamilyId(Context context) {
        if(context == null){
            return -1;
        }
        SharedPreferences mySharedPreferences = context.getSharedPreferences(USER_CONFIG_FILE,
                Context.MODE_PRIVATE);
        return mySharedPreferences.getInt(USER_CONFIG_VALUE_FAMILY_ID, -1);
    }

    public void setConfigFamilyId(Context context, int familyUserId) {
        if(context == null){
            return;
        }
        SharedPreferences mySharedPreferences= context.getSharedPreferences(USER_CONFIG_FILE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putInt(USER_CONFIG_VALUE_FAMILY_ID, familyUserId);
        editor.commit();
    }

    public boolean isLogin(Context context){
        if(context == null){
            return false;
        }

        String currentUser = getConfigCurrentUser(context);
        if(currentUser.equals("") || currentUser.equals(USER_GUEST)){
            return false;
        }

        return true;
    }
}
