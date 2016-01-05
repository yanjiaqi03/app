package com.ihealth.aijiakang.activity.comm;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.ihealth.aijiakang.activity.BaseActivity;
import com.ihealth.aijiakang.logic.LogicCallbackInterface;
import com.ihealth.aijiakang.logic.control.LoginControl;
import com.ihealth.aijiakang.sharedpreference.UserConfig;
import com.ihealth.aijiakang.system.AJKLog;
import com.ihealth.aijiakang.thirdparty.MiPushTools;
import com.ihealth.aijiakang.thirdparty.ThirdLoginData;
import com.ihealth.aijiakang.thirdparty.ThirdLoginTools;
import com.ihealth.aijiakang.widgets.FontEditText;
import com.ihealth.aijiakang.widgets.FontTextView;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/19
 */
public class LoginActivity extends BaseActivity {
    private final String TAG = "LoginActivity";
    /*XML Resource*/
    private FontEditText phoneEt;
    private FontEditText passwdEt;
    private FontTextView loginBt;
    private FontTextView registBt;
    private FontTextView forgetBt;
    private LinearLayout miLogin;
    private LinearLayout wechatLogin;
    private LinearLayout guestLogin;

    @Override
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        //注销推送
        MiPushTools.getInstance().unRegistPush(myApplication);
        //清除通知栏
        MiPushTools.getInstance().clearNotifications(myApplication);

        phoneEt = (FontEditText) findViewById(R.id.login_activity_phone_edit);
        passwdEt = (FontEditText) findViewById(R.id.login_activity_passwd_edit);

        loginBt = (FontTextView) findViewById(R.id.login_activity_login_bt);
        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phone = phoneEt.getText().toString().trim();
                if (!phoneEt.checkPhoneWithToast(phone)) {
                    return;
                }
                String passwd = passwdEt.getText().toString().trim();
                if(!passwdEt.checkPasswdWithToast(passwd)){
                    return;
                }
                //执行登录操作
                showLoadingBar(true);
                LoginControl.getInstance().loginByiHealthID(LoginActivity.this, phone, passwd, new LogicCallbackInterface() {
                    @Override
                    public void onSuccess(String result) {
                        loginSuccess();
                    }
                    @Override
                    public void onFailure(String errorNum, String content) {
                        showLoadingBar(false);

                    }
                });
            }
        });
        registBt = (FontTextView) findViewById(R.id.login_activity_regist_bt);
        registBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpActivity(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.finish();
            }
        });

        forgetBt = (FontTextView) findViewById(R.id.login_activity_forgetpasswd_bt);
        forgetBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpActivity(LoginActivity.this, ForgetPasswdActivity.class);
            }
        });

        miLogin = (LinearLayout) findViewById(R.id.login_activity_mi_bt);
        miLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThirdLoginTools.getInstance().MIThirdLogin(LoginActivity.this, new ThirdLoginTools.ThirdLoginListener() {
                    @Override
                    public void cancel() {
                        AJKLog.e(TAG, "MI Cancel");
                    }
                    @Override
                    public void success(final ThirdLoginData data) {
                        //执行第三方登录
                        //AJKLog.i(TAG, "currentThread = " + Thread.currentThread().toString());
                        AJKLog.i(TAG, "NickName = " + data.getNickName());
                        AJKLog.i(TAG, "Icon = " + data.getIcon());
                        AJKLog.i(TAG, "Sex = " + data.getSex());
                        final String userName = data.getUserId() + "@mi";
                        String password = userName + "ihealth";
                        showLoadingBar(true);
                        LoginControl.getInstance().loginByThirdID(LoginActivity.this, userName, password, data, new LogicCallbackInterface() {
                            @Override
                            public void onSuccess(String result) {
                                loginSuccess();
                            }
                            @Override
                            public void onFailure(String errorNum, String content) {
                                showLoadingBar(false);
                            }
                        });
                    }

                    @Override
                    public void error(String error) {
                        AJKLog.e(TAG, "MI error -> " + error);
                    }
                });
            }
        });

        wechatLogin = (LinearLayout) findViewById(R.id.login_activity_wechat_bt);
        wechatLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThirdLoginTools.getInstance().WechatThirdLogin(new ThirdLoginTools.ThirdLoginListener() {
                    @Override
                    public void cancel() {
                        AJKLog.e(TAG, "Wechat Cancel");
                    }

                    @Override
                    public void success(final ThirdLoginData data) {
                        //执行第三方登录
                        AJKLog.i(TAG, "currentThread = " + Thread.currentThread().toString());
                        AJKLog.i(TAG, "NickName = " + data.getNickName());
                        AJKLog.i(TAG, "Icon = " + data.getIcon());
                        AJKLog.i(TAG, "Sex = " + data.getSex());
                        AJKLog.i(TAG, "UserId = " + data.getUserId());
                        final String userName = data.getUserId() + "@weixin";
                        String password = userName + "ihealth";
                        showLoadingBar(true);
                        LoginControl.getInstance().loginByThirdID(LoginActivity.this, userName, password, data, new LogicCallbackInterface() {
                            @Override
                            public void onSuccess(String result) {
                                loginSuccess();
                            }
                            @Override
                            public void onFailure(String errorNum, String content) {
                                showLoadingBar(false);
                            }
                        });
                    }
                    @Override
                    public void error(String error) {
                        AJKLog.e(TAG, "Wechat error -> " + error);
                    }
                },myHandler);
            }
        });

        guestLogin = (LinearLayout) findViewById(R.id.login_activity_guest_bt);
        guestLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserConfig.getInstance().setConfigCurrentUser(LoginActivity.this, "Guest");
                UserConfig.getInstance().setConfigPassword(LoginActivity.this, "");
                loginSuccess();
            }
        });
    }

    /**
     * 登录成功的跳转方法
     * Author YanJiaqi
     * Created at 15/11/20 下午3:02
     */

    private void loginSuccess() {
        showLoadingBar(false);
        //注册推送
        MiPushTools.getInstance().init(myApplication);
        jumpActivity(this, HostActivity.class);
        this.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            this.finish();
            return true;
        }
        return false;
    }
}
