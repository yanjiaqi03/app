package com.ihealth.aijiakang.activity.comm;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.ihealth.aijiakang.activity.BaseActivity;
import com.ihealth.aijiakang.sharedpreference.UserConfig;
import com.ihealth.aijiakang.widgets.FontEditText;
import com.ihealth.aijiakang.widgets.FontTextView;
import com.ihealth.aijiakang.widgets.toast.HintToast;
import com.ihealth.ihealthcloudlibrary.cloudInterface.CloudCallback;
import com.ihealth.ihealthcloudlibrary.cloudInterface.UserCloudMethod;

import java.util.ArrayList;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/26
 */
public class ChangePasswdActivity extends BaseActivity{
    private final String TAG = "ChangePasswdActivity";

    private String passwd = "";//本地存储的密码
    /** XML Resource **/
    private FrameLayout back;
    private FontEditText oriEdit;
    private FontEditText newEdit;
    private FontTextView finish;

    @Override
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);

        setContentView(R.layout.activity_change_passwd);

        initViews();
    }

    private void initViews(){
        passwd = UserConfig.getInstance().getConfigPassword(ChangePasswdActivity.this);//读取本地密码

        back = (FrameLayout) findViewById(R.id.change_passwd_return);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

        oriEdit = (FontEditText) findViewById(R.id.change_passwd_input_ori);
        newEdit = (FontEditText) findViewById(R.id.change_passwd_input_new);

        finish = (FontTextView) findViewById(R.id.change_passwd_finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oriPasswd = oriEdit.getText().toString().trim();
                if (!oriPasswd.equals(passwd)) {
                    HintToast.makeText(ChangePasswdActivity.this, getResources().getString(R.string.change_passwd_ori_error), HintToast.LENGTH_SHORT).show();
                    return;
                }

                final String newPasswd = newEdit.getText().toString().trim();
                if(!newEdit.checkPasswdWithToast(newPasswd)){
                    return;
                }

                showLoadingBar(true);
                final String userName = UserConfig.getInstance().getConfigCurrentUser(ChangePasswdActivity.this);
                UserCloudMethod.getInstance().changePassword(ChangePasswdActivity.this, userName, oriPasswd, newPasswd, new CloudCallback<String>(){
                    @Override
                    public void onSuccess(ArrayList<String> strings) {
                        UserConfig.getInstance().setConfigCurrentUser(ChangePasswdActivity.this, "");
                        UserConfig.getInstance().setConfigPassword(ChangePasswdActivity.this, "");
                        changeSuccess();
                    }

                    @Override
                    public void onFailure(String backNum, String str) {
                        showLoadingBar(false);
                    }
                });

            }
        });
    }

    /**
     * 修改密码成功
     * Author YanJiaqi
     * Created at 15/11/26 下午4:33
     */

    private void changeSuccess(){
        HintToast.makeText(ChangePasswdActivity.this, getResources().getString(R.string.change_passwd_success), HintToast.LENGTH_SHORT).show();
        clearAllActivity();
        jumpActivity(ChangePasswdActivity.this, LoginActivity.class);
    }
}
