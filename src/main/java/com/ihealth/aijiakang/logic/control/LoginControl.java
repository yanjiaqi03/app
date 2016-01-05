package com.ihealth.aijiakang.logic.control;

import android.content.Context;

import com.ihealth.aijiakang.database.data.Data_UserInfo;
import com.ihealth.aijiakang.database.data.Data_UserToken;
import com.ihealth.aijiakang.logic.ErrorCodeHandler;
import com.ihealth.aijiakang.logic.LogicCallbackInterface;
import com.ihealth.aijiakang.logic.LogicListener;
import com.ihealth.aijiakang.logic.database.UserInfoLogic;
import com.ihealth.aijiakang.logic.database.UserTokenLogic;
import com.ihealth.aijiakang.sharedpreference.UserConfig;
import com.ihealth.aijiakang.system.AJKLog;
import com.ihealth.aijiakang.thirdparty.ThirdLoginData;
import com.ihealth.ihealthcloudlibrary.cloudInterface.AJKCloudMethod;
import com.ihealth.ihealthcloudlibrary.cloudInterface.CloudCallback;
import com.ihealth.ihealthcloudlibrary.cloudInterface.UserCloudMethod;
import com.ihealth.ihealthcloudlibrary.model.input.InputModelUser;
import com.ihealth.ihealthcloudlibrary.model.output.OutputModelUser;
import com.ihealth.ihealthcloudlibrary.model.output.OutputModelUserLogin;
import java.util.ArrayList;

/**
 * Created by lanbaoshi on 15/11/24.
 */
public class LoginControl extends BasicControl{
    private final String TAG = "LoginControl";
    private static LoginControl mInstance;
    private LogicCallbackInterface mCallback;
    private LoginControl() {

    }

    public static LoginControl getInstance() {
        if(mInstance == null) {
            mInstance = new LoginControl();
        }
        return mInstance;
    }
    public void loginByiHealthID(final Context context, String userName, final String password, final LogicCallbackInterface callback) {
        this.mCallback = callback;
        InputModelUser inputModelUser = new InputModelUser();
        inputModelUser.setUserName(userName);
        inputModelUser.setPassword(password);
        AJKCloudMethod.getInstance().loginUserInfo(context, inputModelUser, new CloudCallback<OutputModelUserLogin>() {
            @Override
            public void onSuccess(ArrayList<OutputModelUserLogin> list) {
                //Toast.makeText(context, "login successfully", Toast.LENGTH_LONG).show();
                if(context == null) {
                    return;
                }
                AJKLog.i(TAG, "login successfully");
                final ArrayList<Data_UserToken> tokenList = new ArrayList<Data_UserToken>();
                final ArrayList<Data_UserInfo> infoList = new ArrayList<Data_UserInfo>();
                for(int i=0; i<list.size(); i++) {
                    Data_UserToken data_userToken = new Data_UserToken();
                    data_userToken.setiHealthID(list.get(i).getUn());
                    AJKLog.i(TAG, "un = " + list.get(i).getUn());
                    data_userToken.setAccessToken(list.get(i).getAccessToken());
                    data_userToken.setRefreshToken(list.get(i).getRefreshToken());
                    tokenList.add(data_userToken);

                    Data_UserInfo data_userInfo = new Data_UserInfo();
                    data_userInfo.setWeight(list.get(i).getWeight());
                    data_userInfo.setGender(list.get(i).getGender());
                    data_userInfo.setBirthDay(list.get(i).getBirthDay());
                    data_userInfo.setName(list.get(i).getName());
                    data_userInfo.setHeight(list.get(i).getHeight());
                    data_userInfo.setLogo(list.get(i).getLogo());
                    data_userInfo.setLogoTS(list.get(i).getLogoTS());
                    data_userInfo.setUserId(list.get(i).getUserId());
                    data_userInfo.setUserName(list.get(i).getUn());
                    infoList.add(data_userInfo);

                    new UserTokenLogic(context).saveUserTokenData(tokenList, new LogicListener() {
                        @Override
                        public <T> void onSuccess(T model) {
                            AJKLog.i(TAG, "saveUserTokenData success");

                            new UserInfoLogic(context).saveUserInfoData(infoList, new LogicListener() {
                                @Override
                                public <T> void onSuccess(T model) {
                                    AJKLog.i(TAG, "saveUserInfoData success");
                                    UserConfig.getInstance().setConfigCurrentUser(context, tokenList.get(0).getiHealthID());
                                    UserConfig.getInstance().setConfigUserID(context, infoList.get(0).getUserId());
                                    UserConfig.getInstance().setConfigPassword(context, password);
                                    AJKLog.i(TAG, UserConfig.getInstance().getConfigCurrentUser(context));
                                    AJKLog.i(TAG, UserConfig.getInstance().getConfigUserID(context) + "");
                                    mCallback.onSuccess("");
                                }

                                @Override
                                public <T> void onFailure(T model) {
                                    AJKLog.i(TAG, "saveUserInfoData fail");
                                    mCallback.onFailure("", "");
                                }
                            }, myHandler);
                        }
                        @Override
                        public <T> void onFailure(T model) {
                            AJKLog.i(TAG, "saveUserTokenData fail");
                            mCallback.onFailure("", "");
                        }
                    }, myHandler);
                }
            }
            @Override
            public void onFailure(String backNum, String str) {
                if(context == null) {
                    return;
                }
                AJKLog.i(TAG, "login failed");
                ErrorCodeHandler.getInstance().handleLoginErrorCode(context, backNum);
                mCallback.onFailure(backNum, str);
            }
        });
    }

    public void loginByThirdID(final Context context, final String userName, final String password, final ThirdLoginData data, LogicCallbackInterface callback) {
        this.mCallback = callback;
        InputModelUser inputModelUser = new InputModelUser();
        inputModelUser.setUserName(userName);
        inputModelUser.setPassword(password);
        UserCloudMethod.getInstance().register_third(context, inputModelUser, new CloudCallback<OutputModelUser>() {
            @Override
            public void onSuccess(ArrayList<OutputModelUser> list) {
                if(context == null) {
                    return;
                }
                AJKLog.i(TAG, "login successfully");
                final ArrayList<Data_UserToken> tokenList = new ArrayList<Data_UserToken>();

                for(int i=0; i<list.size(); i++) {
                    final int currentUserId = list.get(i).getUserId();
                    final String isRegister = list.get(i).getIsReg();
                    Data_UserToken data_userToken = new Data_UserToken();
                    data_userToken.setiHealthID(list.get(i).getiHealthId());
                    data_userToken.setRefreshToken(list.get(i).getRefreshToken());
                    data_userToken.setAccessToken(list.get(i).getAccessToken());
                    tokenList.add(data_userToken);

                    new UserTokenLogic(context).saveUserTokenData(tokenList, new LogicListener() {
                        @Override
                        public <T> void onSuccess(T model) {
                            AJKLog.i(TAG, "saveUserTokenData success");
                            UserConfig.getInstance().setConfigCurrentUser(context, tokenList.get(0).getiHealthID());
                            UserConfig.getInstance().setConfigPassword(context, password);
                            UserConfig.getInstance().setConfigUserID(context, currentUserId);

                            AJKLog.i(TAG, "Current UserId = " + UserConfig.getInstance().getConfigUserID(context));
                            AJKLog.i(TAG, "isRegister = " + isRegister);
                            if(isRegister.equals("1")) {
                                UserInfoControl.getInstance().uploadUserInfo(context, data, new LogicCallbackInterface() {
                                    @Override
                                    public void onSuccess(String result) {
                                        mCallback.onSuccess("");
                                    }
                                    @Override
                                    public void onFailure(String errorNum, String content) {
                                        mCallback.onFailure(errorNum, content);
                                    }
                                });
                            }else {
                                UserInfoControl.getInstance().downLoadUserInfo(context, currentUserId, new LogicCallbackInterface() {
                                    @Override
                                    public void onSuccess(String result) {

                                        mCallback.onSuccess("");
                                    }
                                    @Override
                                    public void onFailure(String errorNum, String content) {
                                        mCallback.onFailure(errorNum, content);
                                    }
                                });
                            }
                        }

                        @Override
                        public <T> void onFailure(T model) {
                            AJKLog.i(TAG, "saveUserTokenData fail");
                        }
                    }, myHandler);
                }
                //isRegister： "1" 注册 “0” 登录
            }
            @Override
            public void onFailure(String backNum, String str) {
                if(context == null) {
                    return;
                }
                AJKLog.i(TAG, "login failed");
                ErrorCodeHandler.getInstance().handleLoginErrorCode(context, backNum);
                mCallback.onFailure(backNum, str);
            }
        });
    }

    public void registerAccount(final Context context, final String userName, final String password, final String nickName, LogicCallbackInterface callback) {
        this.mCallback = callback;
        InputModelUser inputModelUser = new InputModelUser();
        inputModelUser.setUserName(userName);
        inputModelUser.setName(nickName);
        inputModelUser.setPassword(password);
        inputModelUser.setBirthDay(-1362332800);
        inputModelUser.setGender(-1);
        inputModelUser.setHeight(0);
        inputModelUser.setWeight(0.0f);
        inputModelUser.setLogo("");
        inputModelUser.setLogoTS(0);
        AJKCloudMethod.getInstance().registerUserInfo(context, inputModelUser, new CloudCallback<OutputModelUser>() {
            @Override
            public void onSuccess(ArrayList<OutputModelUser> list) {
                if(context == null) {
                    return;
                }
                ArrayList<Data_UserToken> tokenList = new ArrayList<Data_UserToken>();
                final ArrayList<Data_UserInfo> infoList = new ArrayList<Data_UserInfo>();
                AJKLog.i(TAG, "regist account success");
                for(int i=0; i<list.size(); i++) {
                    if(list.get(i).getIsReg().equals("1")) {
                        Data_UserToken data_userToken = new Data_UserToken();
                        data_userToken.setAccessToken(list.get(i).getAccessToken());
                        data_userToken.setiHealthID(list.get(i).getiHealthId());
                        data_userToken.setRefreshToken(list.get(i).getRefreshToken());
                        tokenList.add(data_userToken);

                        Data_UserInfo data_userInfo = new Data_UserInfo();
                        data_userInfo.setUserName(userName);
                        data_userInfo.setName(nickName);
                        data_userInfo.setUserId(list.get(i).getUserId());
                        data_userInfo.setGender(-1);
                        data_userInfo.setBirthDay(-1362332800);//给一个1930年1月1日之前的值)
                        data_userInfo.setHeight(0);
                        data_userInfo.setWeight(0.0f);
                        data_userInfo.setLogoTS(0);
                        data_userInfo.setLogo("");
                        infoList.add(data_userInfo);

                        new UserTokenLogic(context).saveUserTokenData(tokenList, new LogicListener() {
                            @Override
                            public <T> void onSuccess(T model) {
                                new UserInfoLogic(context).saveUserInfoData(infoList, new LogicListener() {
                                    @Override
                                    public <T> void onSuccess(T model) {
                                        mCallback.onSuccess("");
                                    }
                                    @Override
                                    public <T> void onFailure(T model) {
                                        mCallback.onFailure("", "");
                                    }
                                }, myHandler);
                            }
                            @Override
                            public <T> void onFailure(T model) {
                                mCallback.onFailure("", "");
                            }
                        }, myHandler);

                    }else {
                        mCallback.onFailure("", "");
                        AJKLog.i(TAG, "Modify user info fail");
                    }
                }
            }
            @Override
            public void onFailure(String backNum, String str) {
                AJKLog.i(TAG, "regist account failed");
                if(context == null) {
                    return;
                }
                ErrorCodeHandler.getInstance().handleRegisterErrorCode(context, backNum);
                mCallback.onFailure(backNum, str);
            }
        });
    }

    public void autoLogin(final Context context, String userName, final String password, LogicCallbackInterface callback) {

        if(userName.endsWith("@mi") || userName.endsWith("@weixin")) {
            loginByThirdID(context, userName, password, null, callback);
        }else {
            loginByiHealthID(context, userName, password, callback);
        }
    }

}
