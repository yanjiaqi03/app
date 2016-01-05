package com.ihealth.aijiakang.activity.comm;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ihealth.aijiakang.activity.BaseActivity;
import com.ihealth.aijiakang.system.AJKLog;
import com.ihealth.aijiakang.widgets.FontTextView;
import com.ihealth.aijiakang.widgets.SwitchImageView;
import com.ihealth.aijiakang.widgets.dialog.OneKeyDialog;
import com.ihealth.aijiakang.widgets.dialog.TwoKeysDialog;
import com.ihealth.aijiakang.widgets.toast.HintToast;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/12/1
 */
public class AccoundBindActivity extends BaseActivity{
    private final String TAG = "AccoundBindActivity";
    private final int BINDED = 1;
    private final int UNBIND = 0;
    private int phoneState = UNBIND;
    private int wechatState = UNBIND;
    private int miState = UNBIND;
    private OneKeyDialog mOneKeyDialog = null;
    private TwoKeysDialog mTwoKeysDialog = null;

    /** XML Resource **/
    private FrameLayout back;
    private RelativeLayout wechatLayout;
    private SwitchImageView wechatSelectIcon;
    private FontTextView wechatSelectTv;

    private RelativeLayout miLayout;
    private SwitchImageView miSelectIcon;
    private FontTextView miSelectTv;

    private RelativeLayout phoneLayout;
    private FontTextView phoneTv;
    private LinearLayout phoneUnbindLayout;
    private FontTextView phoneChange;


    @Override
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);

        setContentView(R.layout.activity_accountbind);

        initViews();
    }

    private void initViews(){
        back = (FrameLayout) findViewById(R.id.accountbind_return);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

        wechatSelectIcon = (SwitchImageView) findViewById(R.id.accountbind_wechat_select_icon);
        wechatSelectTv = (FontTextView) findViewById(R.id.accountbind_wechat_select_txt);

        wechatLayout = (RelativeLayout) findViewById(R.id.accountbind_wechat_layout);
        wechatLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(wechatState == BINDED){
                    //去解绑
                    if(adjustUnbind()){
                        showTwoKeyDialog(getResources().getString(R.string.accountbind_unbind_wechat));
                    }
                }else if(wechatState == UNBIND){
                    //去绑定
                }
            }
        });

        miSelectIcon = (SwitchImageView) findViewById(R.id.accountbind_mi_select_icon);
        miSelectTv = (FontTextView) findViewById(R.id.accountbind_mi_select_txt);

        miLayout = (RelativeLayout) findViewById(R.id.accountbind_mi_layout);
        miLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(miState == BINDED){
                    //去解绑
                    if(adjustUnbind()){
                        showTwoKeyDialog(getResources().getString(R.string.accountbind_unbind_mi));
                    }
                }else if(miState == UNBIND){
                    //去绑定
                }
            }
        });

        phoneTv = (FontTextView) findViewById(R.id.accountbind_phone_txt);
        phoneUnbindLayout = (LinearLayout) findViewById(R.id.accountbind_phone_unbind_layout);
        phoneChange = (FontTextView) findViewById(R.id.accountbind_phone_change_bt);

        phoneLayout = (RelativeLayout) findViewById(R.id.accountbind_phone_layout);
        phoneLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle extras = new Bundle();
                if(phoneState == UNBIND){
                    //绑定
                    extras.putInt(BindPhoneActivity.MODE,BindPhoneActivity.MODE_BIND);
                }else if(phoneState == BINDED){
                    //更改
                    extras.putInt(BindPhoneActivity.MODE,BindPhoneActivity.MODE_CHANGE);
                }

                jumpActivity(AccoundBindActivity.this,BindPhoneActivity.class,extras);
            }
        });

        readBindState();
    }

    /**
     * 读取绑定状态
     * Author YanJiaqi
     * Created at 15/12/1 下午5:25
     */

    private void readBindState(){
        //查看绑定手机号
        String phone = "13502044642";
        if(phone.equals("")){
            phoneTv.setText(getResources().getString(R.string.phone_txt));
            phoneState = UNBIND;
            phoneUnbindLayout.setVisibility(View.VISIBLE);
            phoneChange.setVisibility(View.GONE);
        }else{
            phoneTv.setText(phone);
            phoneState = BINDED;
            phoneUnbindLayout.setVisibility(View.GONE);
            phoneChange.setVisibility(View.VISIBLE);
        }

        //查看绑定微信
        wechatState = BINDED;
        if(wechatState == BINDED){
            wechatSelectTv.setText(getResources().getString(R.string.accountbind_binded));
            wechatSelectIcon.setSwitchState(SwitchImageView.ON_STATE);
        }else if(wechatState == UNBIND){
            wechatSelectTv.setText(getResources().getString(R.string.accoundbind_unbind));
            wechatSelectIcon.setSwitchState(SwitchImageView.OFF_STATE);
        }

        //查看绑定小米
        miState = UNBIND;
        if(miState == BINDED){
            miSelectTv.setText(getResources().getString(R.string.accountbind_binded));
            miSelectIcon.setSwitchState(SwitchImageView.ON_STATE);
        }else if(miState == UNBIND){
            miSelectTv.setText(getResources().getString(R.string.accoundbind_unbind));
            miSelectIcon.setSwitchState(SwitchImageView.OFF_STATE);
        }
    }

    /**
     * 判断是否可以解绑
     * Author YanJiaqi
     * Created at 15/12/1 下午5:40
     */

    private boolean adjustUnbind(){
        int sum = miState+phoneState+wechatState;
        AJKLog.i(TAG,"sum = " + sum);
        if(sum>1){
            return true;
        }else{
            showOneKeyDialog();
            return false;
        }
    }

    /**
     * 显示无法解绑提示框
     * Author YanJiaqi
     * Created at 15/12/1 下午7:30
     */

    private void showOneKeyDialog(){
        if(mOneKeyDialog == null){
            mOneKeyDialog = new OneKeyDialog(this, "", getResources().getString(R.string.accountbind_not_unbind), new OneKeyDialog.OneKeyInterface() {
                @Override
                public void confirm() {

                }
            });
        }
        if(!mOneKeyDialog.isShowing()){
            mOneKeyDialog.show();
        }
    }

    /**
     * 显示是否解绑提示框
     * Author YanJiaqi
     * Created at 15/12/1 下午7:34
     */

    private void showTwoKeyDialog(String content){
        if(mTwoKeysDialog == null){
            mTwoKeysDialog = new TwoKeysDialog(this, TwoKeysDialog.MODE_ORANGE_CANCEL, new TwoKeysDialog.TwoKeysInterface() {
                @Override
                public void confirm() {

                }

                @Override
                public void cancel() {

                }
            });
        }
        if(!mTwoKeysDialog.isShowing()){
            mTwoKeysDialog.refreshData("",content);
            mTwoKeysDialog.show();
        }
    }
}
