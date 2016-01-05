package com.ihealth.aijiakang.logic.database;

import android.content.Context;
import android.database.Cursor;
import android.os.Handler;

import com.ihealth.aijiakang.database.data.Data_Family_Member;
import com.ihealth.aijiakang.database.data.Data_UserToken;
import com.ihealth.aijiakang.database.table.Data_TB_UserToken;
import com.ihealth.aijiakang.logic.DatabaseLogicInterface;
import com.ihealth.aijiakang.logic.LogicListener;
import com.ihealth.aijiakang.system.AJKLog;
import com.ihealth.ihealthcloudlibrary.model.output.OutputModelUser;

import java.util.ArrayList;


/**
 * Created by lanbaoshi on 15/11/24.
 */
public class UserTokenLogic implements DatabaseLogicInterface {
    private Context mContext;
    private Data_TB_UserToken data_tb_userToken;
    private Data_UserToken data_userToken;
    private final String TAG = "UserTokenLogic";

    public UserTokenLogic(Context context) {
        this.mContext = context;
        data_tb_userToken = new Data_TB_UserToken(context);

    }

    @Override
    public <T> boolean saveItem(T model) {
        if(model instanceof Data_UserToken) {
            return data_tb_userToken.addData(model);
        }
        return false;
    }

    @Override
    public <T> boolean itemExist(T model) {
        String selection = "";
        if(model instanceof Data_UserToken) {
            selection = Data_TB_UserToken.USERTOKEN_IHEALTHID + " = '" + ((Data_UserToken)model).getiHealthID() + "'";
        }
        if(selection != "") {
            Cursor cursor = data_tb_userToken.search(selection);
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
        if(model instanceof Data_UserToken) {
            Data_UserToken dataUserToken = (Data_UserToken)model;
            String selection = Data_TB_UserToken.USERTOKEN_IHEALTHID + " = '" + dataUserToken.getiHealthID() + "'";
            return data_tb_userToken.updateData(selection, Data_TB_UserToken.USERTOKEN_ACCESSTOKEN +" = '"
                    + dataUserToken.getAccessToken() + "', "
                    + Data_TB_UserToken.USERTOKEN_REFRESHTOKEN +" = '"
                    + dataUserToken.getRefreshToken() + "'");
        }
        return false;
    }

    public String getAccessToken(String userName) {
        Cursor cursor = data_tb_userToken.search(Data_TB_UserToken.USERTOKEN_IHEALTHID + " = '" + userName + "'");
        if(cursor != null && cursor.getCount() > 0) {
            while(cursor.moveToNext()) {
                return cursor.getString(cursor.getColumnIndex(Data_TB_UserToken.USERTOKEN_ACCESSTOKEN));
            }
        }
        return null;
    }

    public void saveUserTokenData(final ArrayList<Data_UserToken> list, final LogicListener listener, final Handler handler) {
        if(list != null && list.size() > 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0; i<list.size(); i++) {
                        if(itemExist(list.get(i))) {
                            AJKLog.i(TAG, "itemExist");
                            if(!updateItem(list.get(i))) {
                                AJKLog.i(TAG, "updateCloudItem fail");
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
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        AJKLog.i(TAG, "saveItem fail");
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
                            AJKLog.i(TAG, "saveUserTokenData success");
                            listener.onSuccess("");
                        }
                    });
                }
            }).start();
        }
    }
    @Override
    public <T> boolean deleteItem(String selection) {
        return false;
    }

    @Override
    public <T> boolean updateItem(String selection, String valueStr) {

        return data_tb_userToken.updateData(selection, valueStr);
    }

    @Override
    public boolean clearTable() {
        return false;
    }
}
