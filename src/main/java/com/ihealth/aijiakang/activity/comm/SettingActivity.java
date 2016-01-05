package com.ihealth.aijiakang.activity.comm;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.ihealth.aijiakang.activity.BaseActivity;
import com.ihealth.aijiakang.imageloader.ImageLoaderTools;
import com.ihealth.aijiakang.sharedpreference.UserConfig;
import com.ihealth.aijiakang.thirdparty.MiPushTools;
import com.ihealth.aijiakang.widgets.ClickBgRelativeLayout;
import com.ihealth.aijiakang.widgets.FontTextView;
import com.ihealth.aijiakang.widgets.dialog.TwoKeysDialog;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/26
 */
public class SettingActivity extends BaseActivity {
    private final String TAG = "SettingActivity";
    private TwoKeysDialog mTwoKeyDialog;
    /** XML Resource **/
    private FrameLayout back;
    private ClickBgRelativeLayout accountBind;
    private ClickBgRelativeLayout changePasswd;
    private ClickBgRelativeLayout PreferSetting;
    private ClickBgRelativeLayout normalProblems;
    private ClickBgRelativeLayout ussageIntro;
    private FontTextView exitAccount;

    @Override
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        setContentView(R.layout.activity_setting);

        initViews();
    }

    private void initViews() {
        back = (FrameLayout) findViewById(R.id.setting_return);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

        accountBind = (ClickBgRelativeLayout) findViewById(R.id.setting_acount_bind_layout);
        accountBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpActivity(SettingActivity.this,AccoundBindActivity.class);
            }
        });

        changePasswd = (ClickBgRelativeLayout) findViewById(R.id.setting_change_passwd_layout);
        changePasswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpActivity(SettingActivity.this,ChangePasswdActivity.class);
            }
        });

        PreferSetting = (ClickBgRelativeLayout) findViewById(R.id.setting_perfect_setting_layout);
        PreferSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpActivity(SettingActivity.this,PreferActivity.class);
            }
        });

        normalProblems = (ClickBgRelativeLayout) findViewById(R.id.setting_normal_problems_layout);
        normalProblems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpActivity(SettingActivity.this,NormalQuesActivity.class);
            }
        });

        ussageIntro = (ClickBgRelativeLayout) findViewById(R.id.setting_use_intro_layout);
        ussageIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpActivity(SettingActivity.this,UseIntroActivity.class);
            }
        });

        exitAccount = (FontTextView) findViewById(R.id.setting_exit_account);
        exitAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showExitDialog();
            }
        });

    }

    /**
     * 显示退出对话框
     * Author YanJiaqi
     * Created at 15/12/2 下午2:56
     */

    private void showExitDialog(){
        if(mTwoKeyDialog == null){
            mTwoKeyDialog = new TwoKeysDialog(this, "",
                    getResources().getString(R.string.exit_app), TwoKeysDialog.MODE_ORANGE_CONFIRM, new TwoKeysDialog.TwoKeysInterface() {
                @Override
                public void confirm() {
                    ImageLoaderTools.getInstance().clearMemory();
                    UserConfig.getInstance().setConfigCurrentUser(SettingActivity.this, "");
                    UserConfig.getInstance().setConfigPassword(SettingActivity.this, "");
                    UserConfig.getInstance().setConfigUserID(SettingActivity.this, -1);
                    clearAllActivity();
                    jumpActivity(SettingActivity.this, LoginActivity.class);
                }

                @Override
                public void cancel() {

                }
            });
        }
        if(!mTwoKeyDialog.isShowing()){
            mTwoKeyDialog.show();
        }
    }
}
