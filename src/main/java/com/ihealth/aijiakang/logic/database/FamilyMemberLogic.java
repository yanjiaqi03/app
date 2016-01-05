package com.ihealth.aijiakang.logic.database;

import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import com.ihealth.aijiakang.database.data.Data_Family_Member;
import com.ihealth.aijiakang.database.table.Data_TB_Family_Member;
import com.ihealth.aijiakang.logic.DatabaseLogicInterface;
import com.ihealth.aijiakang.logic.LogicListener;
import com.ihealth.aijiakang.system.AJKLog;
import java.util.ArrayList;

/**
 * Created by lanbaoshi on 15/11/30.
 */
public class FamilyMemberLogic implements DatabaseLogicInterface {
    private Context mContext;
    private Data_TB_Family_Member data_tb_family_member;
    private Data_Family_Member data_family_member;
    private final String TAG = "FamilyMemberLogic";

    public FamilyMemberLogic(Context context) {
        this.mContext = context;
        data_tb_family_member = new Data_TB_Family_Member(context);
    }

    @Override
    public <T> boolean saveItem(T model) {
        if(model instanceof Data_Family_Member){
            return data_tb_family_member.addData(model);
        }
        return false;
    }
    @Override
    public <T> boolean itemExist(T model) {
        String selection = "";
        if(model instanceof Data_Family_Member) {
            Data_Family_Member data_family_member = (Data_Family_Member)model;
            selection = Data_TB_Family_Member.FAMILY_FAMILYID + " = " + data_family_member.getFamilyId() + " and "
                    + Data_TB_Family_Member.FAMILY_USERID + " = " + data_family_member.getUserId();
        }
        AJKLog.i(TAG, "selection = " + selection);
        if(selection != "") {
            Cursor cursor = data_tb_family_member.search(selection);
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
    public <T> boolean updateItem(String selection, String valueStr) {
        return false;
    }

    @Override
    public <T> boolean updateItem(T model) {
        if(model instanceof Data_Family_Member) {
            Data_Family_Member data_family_member = (Data_Family_Member)model;
            AJKLog.i(TAG, "updateCloudItem " + data_family_member.getFamilyUserBirthday() + "");
            AJKLog.i(TAG, "updateCloudItem " + data_family_member.getFamilyId() + "");
            AJKLog.i(TAG, "updateCloudItem " + data_family_member.getUserId() + "");
            String selection = Data_TB_Family_Member.FAMILY_FAMILYID + " = " + data_family_member.getFamilyId() + " and "
                    + Data_TB_Family_Member.FAMILY_USERID + " = " + data_family_member.getUserId();
            String valueStr = Data_TB_Family_Member.FAMILY_HEADIMG + " = '" + data_family_member.getHeadImg() + "', "
                            + Data_TB_Family_Member.FAMILY_REFRESHTOKEN + " = '" + data_family_member.getRefreshToken() + "', "
                            + Data_TB_Family_Member.FAMILY_REMARK + " = '" + data_family_member.getRemark() + "', "
                            + Data_TB_Family_Member.FAMILY_TOKEN + " = '" + data_family_member.getToken() + "', "
                            + Data_TB_Family_Member.FAMILY_TS + " = " + data_family_member.getTS() + ", "
                            + Data_TB_Family_Member.FAMILY_UN + " = '" + data_family_member.getUn() + "', "
                            + Data_TB_Family_Member.FAMILY_USERBIRTHDAY + " = " + data_family_member.getFamilyUserBirthday() + ", "
                            + Data_TB_Family_Member.FAMILY_USERGENDER + " = " + data_family_member.getFamilyUserGender() + ", "
                            + Data_TB_Family_Member.FAMILY_USERHEIGHT + " = " + data_family_member.getFamilyUserHeight() + ", "
                            + Data_TB_Family_Member.FAMILY_USERNAME + " = '" + data_family_member.getFamilyUserName() + "', "
                            + Data_TB_Family_Member.FAMILY_USERWEIGHT + " = " + data_family_member.getFamilyUserWeight();
            return data_tb_family_member.updateData(selection, valueStr);
        }
        return false;
    }

    @Override
    public <T> boolean deleteItem(String selection) {
        return data_tb_family_member.deleteData(selection);
    }

    @Override
    public boolean clearTable() {
        return data_tb_family_member.deleteTable();
    }

    public void saveFamilyMemberData(final ArrayList<Data_Family_Member> list, final LogicListener listener, final Handler handler) {
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
                            AJKLog.i(TAG, "saveFamilyMemberData success");
                            listener.onSuccess("");
                        }
                    });
                }
            }).start();
        }
    }

    public ArrayList<Data_Family_Member> searchFamilyMember(int familyId, int familyUserId) {
        String selection = Data_TB_Family_Member.FAMILY_FAMILYID + " = " + familyId;
        if(familyUserId != 0) {
            selection += " and " + Data_TB_Family_Member.FAMILY_USERID + " = " + familyUserId;
        }
        ArrayList<Data_Family_Member> list = new ArrayList<Data_Family_Member>();
        Cursor cursor = data_tb_family_member.search(selection);
        if(cursor != null && cursor.getCount() > 0) {
            while(cursor.moveToNext()) {
                Data_Family_Member data_family_member = new Data_Family_Member();
                data_family_member.setUserId(cursor.getInt(cursor.getColumnIndex(Data_TB_Family_Member.FAMILY_USERID)));
                data_family_member.setUn(cursor.getString(cursor.getColumnIndex(Data_TB_Family_Member.FAMILY_UN)));
                data_family_member.setTS(cursor.getLong(cursor.getColumnIndex(Data_TB_Family_Member.FAMILY_TS)));
                data_family_member.setToken(cursor.getString(cursor.getColumnIndex(Data_TB_Family_Member.FAMILY_TOKEN)));
                data_family_member.setFamilyId(cursor.getInt(cursor.getColumnIndex(Data_TB_Family_Member.FAMILY_FAMILYID)));
                data_family_member.setHeadImg(cursor.getString(cursor.getColumnIndex(Data_TB_Family_Member.FAMILY_HEADIMG)));
                data_family_member.setRefreshToken(cursor.getString(cursor.getColumnIndex(Data_TB_Family_Member.FAMILY_REFRESHTOKEN)));
                data_family_member.setRemark(cursor.getString(cursor.getColumnIndex(Data_TB_Family_Member.FAMILY_REMARK)));
                data_family_member.setFamilyUserHeight(cursor.getInt(cursor.getColumnIndex(Data_TB_Family_Member.FAMILY_USERHEIGHT)));
                data_family_member.setFamilyUserGender(cursor.getInt(cursor.getColumnIndex(Data_TB_Family_Member.FAMILY_USERGENDER)));
                data_family_member.setFamilyUserBirthday(cursor.getLong(cursor.getColumnIndex(Data_TB_Family_Member.FAMILY_USERBIRTHDAY)));
                data_family_member.setFamilyUserName(cursor.getString(cursor.getColumnIndex(Data_TB_Family_Member.FAMILY_USERNAME)));
                data_family_member.setFamilyUserWeight(cursor.getFloat(cursor.getColumnIndex(Data_TB_Family_Member.FAMILY_USERWEIGHT)));
                list.add(data_family_member);
            }
        }
        return list;
    }

    public void searchFamilyMember(final int familyId, final int familyUserId,
                                   final LogicListener listener, final Handler handler) {
        if(listener == null || handler == null) {
            return;
        }else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onSuccess(searchFamilyMember(familyId, familyUserId));
                        }
                    });
                }
            }).start();
        }
    }
}
