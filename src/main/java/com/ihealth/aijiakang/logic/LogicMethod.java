package com.ihealth.aijiakang.logic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.ihealth.aijiakang.database.data.Data_Family_Member;
import com.ihealth.aijiakang.database.data.Data_UserInfo;
import com.ihealth.aijiakang.logic.database.FamilyMemberLogic;
import com.ihealth.aijiakang.logic.database.UserInfoLogic;
import com.ihealth.aijiakang.sharedpreference.UserConfig;
import com.ihealth.aijiakang.utils.BitMapUtils;
import com.ihealth.aijiakang.utils.DateUtils;

import java.util.ArrayList;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by lanbaoshi on 15/12/1.
 */
public class LogicMethod {

    private static LogicMethod mInstance;
    private String TAG = "LogicMethod";
    private LogicMethod() {}

    public static LogicMethod getInstance() {
        if(mInstance == null) {
            mInstance = new LogicMethod();
        }
        return mInstance;
    }

    public String getBase64Image(Context context, int userId, String path) {
        int currentUserId = UserConfig.getInstance().getConfigUserID(context);
        int currentFamilyId = UserConfig.getInstance().getConfigFamilyId(context);
        int iconSize = (int) context.getResources().getDimension(R.dimen.userinfo_logo_size);
        Bitmap bitmap = BitMapUtils.getInstance().getScaledBitmap(path, iconSize, iconSize);
        if(currentUserId == userId) {
            Data_UserInfo data_userInfo = new UserInfoLogic(context).searchTableItem(userId);
            if(!path.equals(data_userInfo.getLogo())) {
                return BitMapUtils.getInstance().bitmapToBase64(bitmap);
            }
        }else if(userId == -1){
            return BitMapUtils.getInstance().bitmapToBase64(bitmap);
        }else {
            ArrayList<Data_Family_Member> list = new FamilyMemberLogic(context).searchFamilyMember(currentFamilyId, userId);
            if(!path.equals(list.get(0).getHeadImg())) {
                return BitMapUtils.getInstance().bitmapToBase64(bitmap);
            }
        }
        return "";
    }

    public String getBase64Image(Context context, int resId, BitmapFactory.Options opt) {
        if(context == null) {
            return "";
        }
        Bitmap bitmap = BitMapUtils.getInstance().getBitmap(context, resId, opt);
        if(bitmap == null) {
            return "";
        }
        return BitMapUtils.getInstance().bitmapToBase64(bitmap);
    }

    public long getBirthday(int year, int month, int day) {
        if(year == 0) {
            return  -1362332800l;//给一个1930年1月1日之前的值
        }else {
            return DateUtils.getInstance().getTimeStamp(year + "年" + month + "月" + day + "日");
        }
    }

    public int getGender(int gender) {
        if(gender == -1) {
            return 2;
        }else {
            return gender;
        }
    }

    public int getCloudGender(int gender) {
        if(gender == 2) {
            return -1;
        }else {
            return gender;
        }
    }


}
