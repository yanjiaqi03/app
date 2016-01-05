package com.ihealth.aijiakang.logic.control;

import android.content.Context;
import android.graphics.Bitmap;

import com.ihealth.aijiakang.database.data.Data_Family_Member;
import com.ihealth.aijiakang.database.data.Data_UserInfo;
import com.ihealth.aijiakang.logic.database.FamilyMemberLogic;
import com.ihealth.aijiakang.logic.LogicCallbackInterface;
import com.ihealth.aijiakang.logic.LogicListener;
import com.ihealth.aijiakang.logic.database.UserInfoLogic;
import com.ihealth.aijiakang.logic.database.UserTokenLogic;
import com.ihealth.aijiakang.sharedpreference.UserConfig;
import com.ihealth.aijiakang.system.AJKLog;
import com.ihealth.aijiakang.thirdparty.ThirdLoginData;
import com.ihealth.aijiakang.utils.BitMapUtils;
import com.ihealth.ihealthcloudlibrary.cloudInterface.CloudCallback;
import com.ihealth.ihealthcloudlibrary.cloudInterface.UserCloudMethod;
import com.ihealth.ihealthcloudlibrary.model.input.InputModelUserInfo;
import com.ihealth.ihealthcloudlibrary.model.output.OutputModelUserInfo;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;

/**
 * Created by lanbaoshi on 15/11/25.
 */
public class UserInfoControl extends BasicControl{
    private final String TAG = "UserInfoControl";
    private static UserInfoControl mInstance;
    private LogicCallbackInterface mCallback;

    private UserInfoControl() {

    }

    public static UserInfoControl getInstance() {
        if(mInstance == null) {
            mInstance = new UserInfoControl();
        }
        return mInstance;
    }
    /**
     * 下载用户信息
     * @author lanbaoshi
     * created at 15/11/26 下午3:46
     */
    public void downLoadUserInfo(final Context context, final int userId, LogicCallbackInterface callback) {
        if(context == null) {
            return;
        }
        this.mCallback = callback;
        String userName = "";
        String accessToken = "";
        final int currentUserId = UserConfig.getInstance().getConfigUserID(context);
        if(currentUserId == userId) {
            userName = UserConfig.getInstance().getConfigCurrentUser(context);
            accessToken = new UserTokenLogic(context).getAccessToken(userName);
            if (accessToken != "") {
                InputModelUserInfo userModel = new InputModelUserInfo();
                userModel.setUn(userName);
                userModel.setToken(accessToken);
                UserCloudMethod.getInstance().downloadUserInfo(context, userModel, new CloudCallback<OutputModelUserInfo>() {
                    @Override
                    public void onSuccess(final ArrayList<OutputModelUserInfo> list) {
                        if (context == null) {
                            return;
                        }
                        ArrayList<Data_UserInfo> infoList = new ArrayList<Data_UserInfo>();
                        for(int i=0; i<list.size(); i++) {
                            infoList.add(translateCloudObjectToNative(list.get(i)));
                        }
                        AJKLog.i(TAG, "Download user info success");
                        AJKLog.i(TAG, list.get(0).getUserId() + "");
                        new UserInfoLogic(context).saveUserInfoData(infoList, new LogicListener() {
                            @Override
                            public <T> void onSuccess(T model) {
                                AJKLog.i(TAG, "save user info success");
                                mCallback.onSuccess("");
                            }
                            @Override
                            public <T> void onFailure(T model) {
                                AJKLog.i(TAG, "save user info fail");
                                mCallback.onFailure("", "");
                            }
                        }, myHandler);
                    }
                    @Override
                    public void onFailure(String backNum, String str) {
                        if (context == null) {
                            return;
                        }
                        AJKLog.i(TAG, "Download user info failed");
                        mCallback.onFailure(backNum, str);
                    }
                });
            }
        }else {
            mCallback.onSuccess("");
        }
    }
    
    /**
     * 上传用户信息
     * @author lanbaoshi
     * created at 15/11/26 下午5:05
     */
    public void uploadUserInfo(final Context context, final int userId, final String name, final float weight, final long birthday,
                               final int height, final String logo, final long logoTS, final int gender, LogicCallbackInterface callback) {
        if(context == null) {
            return;
        }
        this.mCallback = callback;
        final String userName = UserConfig.getInstance().getConfigCurrentUser(context);
        final String accessToken = new UserTokenLogic(context).getAccessToken(userName);
        int currentUserId = UserConfig.getInstance().getConfigUserID(context);
        if(currentUserId == userId) {
            AJKLog.i(TAG, "upload current user");
            new UserInfoLogic(context).searchUserInfoData(userId, new LogicListener() {
                @Override
                public <T> void onSuccess(T model) {
                    if(model != null) {
                        Data_UserInfo data_userInfo = (Data_UserInfo)model;
                        InputModelUserInfo userModel = new InputModelUserInfo();
                        userModel.setUn(userName);
                        userModel.setToken(accessToken);
                        userModel.setWeight(weight);
                        userModel.setName(name);
                        userModel.setBirthDay(birthday);
                        userModel.setHeight(height);
                        userModel.setLogo(logo);
                        userModel.setGender(gender);
                        userModel.setLogoTS(logoTS);
                        userModel.setUserId(data_userInfo.getUserId());
                        userModel.setNation(data_userInfo.getNation());
                        userModel.setEmail(data_userInfo.getEmail());
                        userModel.setActivityLevel(data_userInfo.getActivityLevel());
                        userModel.setAntoLogin(data_userInfo.getAntoLogin());
                        userModel.setBmr(data_userInfo.getBmr());
                        userModel.setCloudSerialNumber(data_userInfo.getCloudSerialNumber());
                        userModel.setCloudUserMac(data_userInfo.getUserName());
                        userModel.setIsRememberPassword(data_userInfo.getIsRememberPassword());
                        userModel.setIsSporter(data_userInfo.getIsSporter());
                        userModel.setLanguage(data_userInfo.getLanguage());
                        userModel.setLastTime(data_userInfo.getLastTime());
                        userModel.setTimeZone(data_userInfo.getTimeZone());
                        userModel.setTS(data_userInfo.getLogoTS());
                        userModel.setUserCloud(data_userInfo.getUserCloud());
                        UserCloudMethod.getInstance().uploadUserInfo(context, userModel, new CloudCallback<OutputModelUserInfo>() {
                            @Override
                            public void onSuccess(final ArrayList<OutputModelUserInfo> list) {
                                if(context == null) {
                                    return;
                                }
                                ArrayList<Data_UserInfo> infoList = new ArrayList<Data_UserInfo>();
                                for(int i=0; i<list.size(); i++) {
                                    infoList.add(translateCloudObjectToNative(list.get(i)));
                                }
                                new UserInfoLogic(context).saveUserInfoData(infoList, new LogicListener() {
                                    @Override
                                    public <T> void onSuccess(T model) {
                                        mCallback.onSuccess(list.get(0).getLogo());
                                        AJKLog.i(TAG, "Upload user info success");
                                    }
                                    @Override
                                    public <T> void onFailure(T model) {

                                    }
                                }, myHandler);


                            }
                            @Override
                            public void onFailure(String backNum, String str) {
                                if(context == null) {
                                    return;
                                }
                                AJKLog.i(TAG, "Upload user info failed");
                                mCallback.onFailure(backNum, str);
                            }
                        });
                    }else {
                        AJKLog.i(TAG, "没有找到本地用户");
                    }
                }
                @Override
                public <T> void onFailure(T model) {

                }
            }, myHandler);
        }else {
            AJKLog.i(TAG, "upload family member");
            FamilyMemberControl.getInstance().updateFamilyMember(context, userId, name, weight, birthday, height, logo, logoTS, gender, callback);
        }

    }
    
    /**
     * 上传用户信息  第三方账号
     * @author lanbaoshi
     * created at 15/11/26 下午11:23
     */
    public void uploadUserInfo(final Context context, ThirdLoginData data, LogicCallbackInterface callback) {
        if(context == null) {
            return;
        }
        this.mCallback = callback;
        String userName = UserConfig.getInstance().getConfigCurrentUser(context);
        String accessToken = new UserTokenLogic(context).getAccessToken(userName);
        if(accessToken != null) {
            Bitmap bitmap = ImageLoader.getInstance().loadImageSync(data.getIcon());
            String base64Image = BitMapUtils.getInstance().bitmapToBase64(bitmap);
            InputModelUserInfo userModel = new InputModelUserInfo();
            userModel.setUn(userName);
            userModel.setToken(accessToken);
            userModel.setLogo(base64Image);
            userModel.setName(data.getNickName());
            userModel.setLogoTS(System.currentTimeMillis() / 1000);
            userModel.setBirthDay(-1362332800);//给一个1930年1月1日之前的值)
            userModel.setHeight(0);
            userModel.setWeight(0.0f);
            if(data.getSex().equals("1")) {//微信 男 1
                userModel.setGender(0);
            }else if(data.getSex().equals("2")) {//微信 女 2
                userModel.setGender(1);
            }else if(data.getSex().equals("")) {//小米账户不返回性别
                userModel.setGender(-1);
            }

            UserCloudMethod.getInstance().uploadUserInfo(context, userModel, new CloudCallback<OutputModelUserInfo>() {
                @Override
                public void onSuccess(final ArrayList<OutputModelUserInfo> list) {
                    if(context == null) {
                        return;
                    }
                    ArrayList<Data_UserInfo> infoList = new ArrayList<Data_UserInfo>();
                    for(int i=0; i<list.size(); i++) {
                        infoList.add(translateCloudObjectToNative(list.get(i)));
                    }
                    new UserInfoLogic(context).saveUserInfoData(infoList, new LogicListener() {
                        @Override
                        public <T> void onSuccess(T model) {
                            AJKLog.i(TAG, "Upload user info success");
                            mCallback.onSuccess("");
                        }
                        @Override
                        public <T> void onFailure(T model) {

                        }
                    }, myHandler);

                }
                @Override
                public void onFailure(String backNum, String str) {
                    if(context == null) {
                        return;
                    }
                    AJKLog.i(TAG, "Upload user info failed");
                    mCallback.onFailure(backNum, str);
                }
            });
        }
    }


    public Data_UserInfo getUserInfo(Context context, int userId) {
        int currentUserId = UserConfig.getInstance().getConfigUserID(context);
        if(currentUserId == userId) {
            return new UserInfoLogic(context).searchTableItem(userId);
        }else {
            int familyId = UserConfig.getInstance().getConfigFamilyId(context);
            ArrayList<Data_Family_Member> data_family_member = new FamilyMemberLogic(context).searchFamilyMember(familyId, userId);
            Data_UserInfo data_userInfo = new Data_UserInfo();
            if(data_family_member != null && data_family_member.size() > 0) {
                data_userInfo.setGender(data_family_member.get(0).getFamilyUserGender());
                data_userInfo.setLogo(data_family_member.get(0).getHeadImg());
                data_userInfo.setName(data_family_member.get(0).getRemark());
                data_userInfo.setWeight(data_family_member.get(0).getFamilyUserWeight());
                data_userInfo.setHeight(data_family_member.get(0).getFamilyUserHeight());
                data_userInfo.setBirthDay(data_family_member.get(0).getFamilyUserBirthday());
            }
            return data_userInfo;
        }
    }

    private Data_UserInfo translateCloudObjectToNative(OutputModelUserInfo outputModelUserInfo) {
        Data_UserInfo data_userInfo = new Data_UserInfo();
        data_userInfo.setLogo(outputModelUserInfo.getLogo());
        data_userInfo.setName(outputModelUserInfo.getName());
        data_userInfo.setNation(outputModelUserInfo.getNation());
        data_userInfo.setEmail(outputModelUserInfo.getEmail());
        data_userInfo.setBmr(outputModelUserInfo.getBmr());
        data_userInfo.setUserName(outputModelUserInfo.getUn());
        data_userInfo.setActivityLevel(outputModelUserInfo.getActivityLevel());
        data_userInfo.setAntoLogin(outputModelUserInfo.getAntoLogin());
        data_userInfo.setBirthDay(outputModelUserInfo.getBirthDay());
        data_userInfo.setCloudSerialNumber(outputModelUserInfo.getCloudSerialNumber());
        data_userInfo.setGender(outputModelUserInfo.getGender());
        data_userInfo.setHeight(outputModelUserInfo.getHeight());
        data_userInfo.setIsRememberPassword(outputModelUserInfo.getIsRememberPassword());
        data_userInfo.setIsSporter(outputModelUserInfo.getIsSporter());
        data_userInfo.setLanguage(outputModelUserInfo.getLanguage());
        data_userInfo.setLastTime(outputModelUserInfo.getLastTime());
        data_userInfo.setLogoTS(outputModelUserInfo.getLogoTS());
        data_userInfo.setPassWord("");
        data_userInfo.setTimeZone(outputModelUserInfo.getTimeZone());
        data_userInfo.setTS(outputModelUserInfo.getTS());
        data_userInfo.setUserCloud(outputModelUserInfo.getUserCloud());
        data_userInfo.setUserId(outputModelUserInfo.getUserId());
        data_userInfo.setWeight(outputModelUserInfo.getWeight());
        data_userInfo.setCloudUserMac(outputModelUserInfo.getCloudUserMac());

        return data_userInfo;
    }
}
