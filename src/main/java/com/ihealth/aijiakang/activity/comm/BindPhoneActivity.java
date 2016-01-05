package com.ihealth.aijiakang.activity.comm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.ihealth.aijiakang.activity.BaseActivity;
import com.ihealth.aijiakang.fragment.BindPhoneFragment;
import com.ihealth.aijiakang.widgets.FontTextView;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/23
 */
public class BindPhoneActivity extends BaseActivity{
    private final String TAG = "BindPhoneActivity";
    private String mobilePhone = "";
    public static final String MODE = "mode";
    public static final int MODE_CHANGE = 200;
    public static final int MODE_BIND = 100;
    private int mode = MODE_BIND;

    /** XML Resource **/
    private FrameLayout back;
    private FontTextView title;

    @Override
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);

        setContentView(R.layout.activity_bindphone);

        myFragmentManager = getSupportFragmentManager();
        initViews();
        changeFragment(null, new BindPhoneFragment());
    }

    private void initViews(){
        mode = getIntent().getExtras().getInt(MODE,MODE_BIND);

        title = (FontTextView) findViewById(R.id.bindphone_title);
        if(mode == MODE_BIND){
            title.setText(getResources().getString(R.string.bindphone_title));
        }else if(mode == MODE_CHANGE){
            title.setText(getResources().getString(R.string.changephone_title));
        }

        back = (FrameLayout) findViewById(R.id.bindphone_return);
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
        ft.replace(R.id.bindphone_center, to).commit();
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
            goBack();
        }
    }

    public void setMobilePhone(String phoneNum){
        this.mobilePhone = phoneNum;
    }

    public String getMobilePhone(){
       return this.mobilePhone;
    }

    public int getMode(){
        return this.mode;
    }
}
