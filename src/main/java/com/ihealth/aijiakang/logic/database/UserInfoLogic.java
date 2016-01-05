package com.ihealth.aijiakang.logic.database;

import android.content.Context;
import android.database.Cursor;
import android.os.Handler;

import com.ihealth.aijiakang.database.data.Data_Family_Member;
import com.ihealth.aijiakang.database.data.Data_UserInfo;
import com.ihealth.aijiakang.database.table.Data_TB_UserInfo;
import com.ihealth.aijiakang.logic.DatabaseLogicInterface;
import com.ihealth.aijiakang.logic.LogicListener;
import com.ihealth.aijiakang.system.AJKLog;

import java.util.ArrayList;

/**
 * Created by lanbaoshi on 15/11/24.
 */
public class UserInfoLogic implements DatabaseLogicInterface {
    private Context mContext;
    private Data_TB_UserInfo data_tb_userInfo;
    private Data_UserInfo data_userInfo;
    private final String TAG = "UserInfoLogic";

    public UserInfoLogic(Context context) {
        this.mContext = context;
        data_tb_userInfo = new Data_TB_UserInfo(context);
    }

    public Data_UserInfo searchTableItem(int key) {
        String selection = Data_TB_UserInfo.USERINFO_USERID + " = " + key + "";
        Cursor cursor = data_tb_userInfo.search(selection);
        if(cursor != null && cursor.getCount() > 0) {
            while(cursor.moveToNext()) {
                Data_UserInfo data = new Data_UserInfo();
                data.setNation(cursor.getString(cursor.getColumnIndex(Data_TB_UserInfo.USERINFO_NATION)));
                data.setEmail(cursor.getString(cursor.getColumnIndex(Data_TB_UserInfo.USERINFO_EMAIL)));
                data.setBmr(cursor.getInt(cursor.getColumnIndex(Data_TB_UserInfo.USERINFO_BMR)));
                data.setUserName(cursor.getString(cursor.getColumnIndex(Data_TB_UserInfo.USERINFO_USERNAME)));
                data.setWeight(cursor.getFloat(cursor.getColumnIndex(Data_TB_UserInfo.USERINFO_WEIGHT)));
                data.setActivityLevel(cursor.getInt(cursor.getColumnIndex(Data_TB_UserInfo.USERINFO_ACTIVITYLEVEL)));
                data.setAntoLogin(cursor.getInt(cursor.getColumnIndex(Data_TB_UserInfo.USERINFO_ANTOLOGIN)));
                data.setBirthDay(cursor.getLong(cursor.getColumnIndex(Data_TB_UserInfo.USERINFO_BIRTHDAY)));
                data.setCloudSerialNumber(cursor.getInt(cursor.getColumnIndex(Data_TB_UserInfo.USERINFO_CLOUDSERIALNUMBER)));
                data.setCloudUserMac(cursor.getString(cursor.getColumnIndex(Data_TB_UserInfo.USERINFO_CLOUDUSERMAC)));
                data.setGender(cursor.getInt(cursor.getColumnIndex(Data_TB_UserInfo.USERINFO_GENDER)));
                data.setHeight(cursor.getInt(cursor.getColumnIndex(Data_TB_UserInfo.USERINFO_HEIGHT)));
                data.setIsRememberPassword(cursor.getInt(cursor.getColumnIndex(Data_TB_UserInfo.USERINFO_ISREMMBERPASSWORD)));
                data.setIsSporter(cursor.getInt(cursor.getColumnIndex(Data_TB_UserInfo.USERINFO_ISSPORTER)));
                data.setLanguage(cursor.getString(cursor.getColumnIndex(Data_TB_UserInfo.USERINFO_LANGUAGE)));
                data.setLastTime(cursor.getString(cursor.getColumnIndex(Data_TB_UserInfo.USERINFO_LASTTIME)));
                data.setLogo(cursor.getString(cursor.getColumnIndex(Data_TB_UserInfo.USERINFO_LOGO)));
                data.setLogoTS(cursor.getLong(cursor.getColumnIndex(Data_TB_UserInfo.USERINFO_LOGOTS)));
                data.setName(cursor.getString(cursor.getColumnIndex(Data_TB_UserInfo.USERINFO_NAME)));
                data.setTimeZone(cursor.getString(cursor.getColumnIndex(Data_TB_UserInfo.USERINFO_TIMEZONE)));
                data.setUserCloud(cursor.getInt(cursor.getColumnIndex(Data_TB_UserInfo.USERINFO_USERCLOUD)));
                data.setUserId(cursor.getInt(cursor.getColumnIndex(Data_TB_UserInfo.USERINFO_USERID)));
                data.setTS(cursor.getLong(cursor.getColumnIndex(Data_TB_UserInfo.USERINFO_TS)));
                return data;
            }
        }
        return null;
    }


    public void searchUserInfoData(final int key, final LogicListener listener, final Handler handler) {
        if (listener == null || handler == null) {
            return;
        }else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onSuccess(searchTableItem(key));
                        }
                    });
                }
            }).start();
        }
        return;
    }

    public void saveUserInfoData(final ArrayList<Data_UserInfo> list, final LogicListener listener, final Handler handler) {
        if(list != null && list.size() > 0) {
            AJKLog.i(TAG, "saveUserInfoData");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0; i<list.size(); i++) {
                        if(list.get(i).getLogoTS() == -2208988800l) {
                            list.get(i).setLogo("");
                        }
                        if(itemExist(list.get(i))) {
                            AJKLog.i(TAG, "itemExist");
                            if(!updateItem(list.get(i))) {
                                AJKLog.i(TAG, "updateItem fail");
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        listener.onFailure("");

                                    }
                                });
                                return;
                            }
                        }else {
                            AJKLog.i(TAG, "saveItem");
                            if(!saveItem(list.get(i))) {
                                AJKLog.i(TAG, "saveItem fail");
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        listener.onFailure("");

                                    }
                                });
                                return;
                            }
                        }
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onSuccess("");
                        }
                    });
                }
            }).start();
        }else {
            AJKLog.i(TAG, "list is empty");
        }
    }

    @Override
    public <T> boolean saveItem(T model) {
        if(model instanceof Data_UserInfo) {
            return data_tb_userInfo.addData((Data_UserInfo)model);
        }
        return false;
    }

    @Override
    public <T> boolean itemExist(T model) {
        String selection = "";
        if(model instanceof Data_UserInfo) {
            Data_UserInfo data_userInfo = (Data_UserInfo)model;
            selection = Data_TB_UserInfo.USERINFO_USERID + " = " + data_userInfo.getUserId();
        }
        if(selection != "") {
            Cursor cursor = data_tb_userInfo.search(selection);
            if(cursor != null && cursor.getCount() > 0) {
                cursor.close();
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    @Override
    public <T> boolean updateItem(T model) {
        if(model instanceof Data_UserInfo){
            Data_UserInfo data_userInfo = (Data_UserInfo)model;
            String selection = Data_TB_UserInfo.USERINFO_USERID + " = " + data_userInfo.getUserId();
            String valueStr = Data_TB_UserInfo.USERINFO_ACTIVITYLEVEL + " = "
                    + data_userInfo.getActivityLevel() + ", "
                    + Data_TB_UserInfo.USERINFO_ANTOLOGIN + " = "
                    + data_userInfo.getAntoLogin() + ", "
                    + Data_TB_UserInfo.USERINFO_BIRTHDAY + " = "
                    + data_userInfo.getBirthDay() + ", "
                    + Data_TB_UserInfo.USERINFO_BMR + " = "
                    + data_userInfo.getBmr() + ", "
                    + Data_TB_UserInfo.USERINFO_CLOUDSERIALNUMBER + " = "
                    + data_userInfo.getCloudSerialNumber() + ", "
                    + Data_TB_UserInfo.USERINFO_CLOUDUSERMAC + " = '"
                    + data_userInfo.getCloudUserMac() + "', "
                    + Data_TB_UserInfo.USERINFO_EMAIL + " = '"
                    + data_userInfo.getEmail() + "', "
                    + Data_TB_UserInfo.USERINFO_GENDER + " = "
                    + data_userInfo.getGender() + ", "
                    + Data_TB_UserInfo.USERINFO_HEIGHT + " = "
                    + data_userInfo.getHeight() + ", "
                    + Data_TB_UserInfo.USERINFO_ISREMMBERPASSWORD + " = "
                    + data_userInfo.getIsRememberPassword() + ", "
                    + Data_TB_UserInfo.USERINFO_ISSPORTER + " = "
                    + data_userInfo.getIsSporter() + ", "
                    + Data_TB_UserInfo.USERINFO_LANGUAGE + " = '"
                    + data_userInfo.getLanguage() + "', "
                    + Data_TB_UserInfo.USERINFO_LASTTIME + " = '"
                    + data_userInfo.getLastTime() + "', "
                    + Data_TB_UserInfo.USERINFO_LOGO + " = '"
                    + data_userInfo.getLogo() + "', "
                    + Data_TB_UserInfo.USERINFO_LOGOTS + " = "
                    + data_userInfo.getLogoTS() + ", "
                    + Data_TB_UserInfo.USERINFO_NAME + " = '"
                    + data_userInfo.getName() + "', "
                    + Data_TB_UserInfo.USERINFO_NATION + " = '"
                    + data_userInfo.getNation() + "', "
                    + Data_TB_UserInfo.USERINFO_TIMEZONE + " = '"
                    + data_userInfo.getTimeZone() + "', "
                    + Data_TB_UserInfo.USERINFO_TS + " = "
                    + data_userInfo.getTS() + ", "
                    + Data_TB_UserInfo.USERINFO_USERCLOUD + " = "
                    + data_userInfo.getUserCloud() + ", "
                    + Data_TB_UserInfo.USERINFO_USERNAME + " = '"
                    + data_userInfo.getUserName() + "', "
                    + Data_TB_UserInfo.USERINFO_WEIGHT + " = "
                    + data_userInfo.getWeight();
            return data_tb_userInfo.updateData(selection, valueStr);
        }
        return false;
    }

    @Override
    public <T> boolean deleteItem(String selection) {
        return data_tb_userInfo.deleteData(selection);
    }

    @Override
    public <T> boolean updateItem(String selection, String valueStr) {
        return data_tb_userInfo.updateData(selection, valueStr);

    }

    @Override
    public boolean clearTable() {
        return false;
    }
}
