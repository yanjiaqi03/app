package com.ihealth.aijiakang.activity.comm;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.ihealth.aijiakang.activity.BaseActivity;
import com.ihealth.aijiakang.fragment.RegisterPhoneFragment;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/23
 */
public class RegisterActivity extends BaseActivity{
    private final String TAG = "RegisterActivity";
    private FrameLayout back;
    private String mobilePhone = "";

    @Override
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);

        setContentView(R.layout.activity_register);

        myFragmentManager = getSupportFragmentManager();
        initViews();
        changeFragment(null,new RegisterPhoneFragment());
    }

    private void initViews(){
        back = (FrameLayout) findViewById(R.id.register_return);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runBack();
            }
        });
    }

    public void changeFragment(Fragment from, Fragment to) {
        FragmentTransaction ft = myFragmentManager.beginTransaction();

        if(from!=null){
            ft.addToBackStack(null);  // can back to from
        }
        ft.replace(R.id.register_center, to).commit();
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

    private void runBack(){
        if(myFragmentManager.getBackStackEntryCount()>0) {
            myFragmentManager.popBackStack();
        }else{
            jumpActivity(RegisterActivity.this, LoginActivity.class);
            goBack();
        }
    }

    public void setMobilePhone(String phoneNum){
        this.mobilePhone = phoneNum;
    }

    public String getMobilePhone(){
       return this.mobilePhone;
    }

    public void jumpToHost(){
        jumpActivity(this, HostActivity.class);
        this.finish();
    }
}
