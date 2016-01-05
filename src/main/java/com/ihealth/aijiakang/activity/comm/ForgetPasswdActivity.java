package com.ihealth.aijiakang.activity.comm;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.ihealth.aijiakang.activity.BaseActivity;
import com.ihealth.aijiakang.activity.comm.HostActivity;
import com.ihealth.aijiakang.activity.comm.LoginActivity;
import com.ihealth.aijiakang.fragment.ForgetPasswdPhoneFragment;
import com.ihealth.aijiakang.fragment.RegisterPhoneFragment;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/23
 */
public class ForgetPasswdActivity extends BaseActivity{
    private final String TAG = "RegisterActivity";
    private String mobilePhone = "";
    private  String verifyCode = "";
    @Override
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);

        setContentView(R.layout.activity_forget_passwd);

        myFragmentManager = getSupportFragmentManager();
        changeFragment(null, new ForgetPasswdPhoneFragment());
    }

    public void changeFragment(Fragment from, Fragment to) {
        FragmentTransaction ft = myFragmentManager.beginTransaction();

        if(from!=null){
            ft.addToBackStack(null);  // can back to from
        }
        ft.replace(R.id.forget_passwd_center, to).commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            runBack();
            return true;
        }
        return false;
    }

    /**
     * 执行返回键操作
     * Author YanJiaqi
     * Created at 15/11/23 下午3:17
     */

    public void runBack(){
        if(myFragmentManager.getBackStackEntryCount()>0) {
            myFragmentManager.popBackStack();
        }else{
            goBack();
        }
    }

    public void setMobilePhone(String phoneNum){
        this.mobilePhone = phoneNum;
    }

    public String getMobilePhone(){
        return this.mobilePhone;
    }

    public void setVerifyCode(String verifyCode){
        this.verifyCode = verifyCode;
    }

    public String getVerifyCode(){
        return this.verifyCode;
    }
}
