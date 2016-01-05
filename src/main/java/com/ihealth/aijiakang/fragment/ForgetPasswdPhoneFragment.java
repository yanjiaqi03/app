package com.ihealth.aijiakang.fragment;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.ihealth.aijiakang.activity.comm.ForgetPasswdActivity;
import com.ihealth.aijiakang.logic.ErrorCodeHandler;
import com.ihealth.aijiakang.system.AJKLog;
import com.ihealth.aijiakang.utils.RegularUtils;
import com.ihealth.aijiakang.utils.TimerUtils;
import com.ihealth.aijiakang.widgets.FontEditText;
import com.ihealth.aijiakang.widgets.FontTextView;
import com.ihealth.aijiakang.widgets.toast.HintToast;
import com.ihealth.ihealthcloudlibrary.cloudInterface.CloudCallback;
import com.ihealth.ihealthcloudlibrary.cloudInterface.UserCloudMethod;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/26
 */
public class ForgetPasswdPhoneFragment extends BaseFragment{
    private final String TAG = "ForgetPasswdPhoneFragment";
    /** XML Resource **/
    private FrameLayout back;
    private FontEditText phoneEdit;
    private FontTextView getCode;
    private FontEditText codeEdit;
    private FontTextView nextStep;

    public ForgetPasswdPhoneFragment(){
        super(R.layout.fragment_forget_phone);
    }
    @Override
    public void mViewCreated(Context context, View view) {
        back = (FrameLayout) view.findViewById(R.id.forget_passwd_phone_return);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ForgetPasswdActivity)getActivity()).runBack();
            }
        });

        phoneEdit = (FontEditText) view.findViewById(R.id.layout_phone_edittext);
        codeEdit = (FontEditText) view.findViewById(R.id.layout_verifycode_edittext);

        getCode = (FontTextView) view.findViewById(R.id.layout_verifycode_bt);
        getCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = phoneEdit.getText().toString().trim();
                if(!phoneEdit.checkPhoneWithToast(phone)){
                    return;
                }
                runGetCode(phone);
            }
        });

        nextStep = (FontTextView) view.findViewById(R.id.forget_passwd_phone_nextstep);
        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = phoneEdit.getText().toString().trim();
                if(!phoneEdit.checkPhoneWithToast(phone)){
                    return;
                }
                ((ForgetPasswdActivity)getActivity()).setMobilePhone(phone);
                String code = codeEdit.getText().toString().trim();
                if(!codeEdit.checkVerifyCodeWithToast(code)){
                    return;
                }

                if(code.equals(((ForgetPasswdActivity)getActivity()).getVerifyCode())) {
                    jumpToPasswdSetting();
                }else {
                    HintToast.makeText(getActivity(), getResources().getString(R.string.verifycode_not_be_right), HintToast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 获取验证码
     * Author YanJiaqi
     * Created at 15/11/24 下午7:09
     */
    private void runGetCode(String phone){
        getCode.setClickable(false);
        //获取发送验证码
        UserCloudMethod.getInstance().getForgetPswVerifyCode(getActivity(), phone, new CloudCallback<String>() {
            @Override
            public void onSuccess(ArrayList<String> list) {
                if(getActivity() == null) {
                    return;
                }
                ((ForgetPasswdActivity)getActivity()).setVerifyCode(list.get(0));
                AJKLog.i(TAG, "Get verify code success");

                TimerUtils.getInstance().startCodeTimer(getActivity(), ((ForgetPasswdActivity) getActivity()).myHandler, new TimerUtils.CodeTimerInterface() {
                    @Override
                    public void onPreTimer() {
                        getCode.setClickable(false);
                        getCode.setTextColor(getResources().getColor(R.color.text_shadow_color));
                    }

                    @Override
                    public void onUpdateTimer(int timerCount) {
                        getCode.setText(timerCount + getResources().getString(R.string.afterseconds_send));
                    }

                    @Override
                    public void onOverTimer() {
                        getCode.setClickable(true);
                        getCode.setTextColor(getResources().getColor(R.color.orange_color));
                        getCode.setText(getResources().getString(R.string.get_code));
                    }
                });
            }

            @Override
            public void onFailure(String backNum, String str) {
                AJKLog.i(TAG, "Get verify code failed");
                if(getActivity() == null) {
                    return;
                }
                getCode.setClickable(true);

                ErrorCodeHandler.getInstance().handleRegisterErrorCode(getActivity(), backNum);
            }
        });
    }

    /**
     * 跳转到填写验证码
     * Author YanJiaqi
     * Created at 15/11/23 下午4:24
     */

    private void jumpToPasswdSetting(){
        if(getActivity() == null){
            return;
        }
        showLoadingBar(false);
        ((ForgetPasswdActivity)getActivity()).changeFragment(ForgetPasswdPhoneFragment.this, new ForgetPasswdSettingFragment());
    }

    @Override
    public void mOnDestroyView(Context context) {
        TimerUtils.getInstance().cancelCodeTimer();
    }

    @Override
    public void mOnShow() {

    }

    @Override
    public void mOnHidden() {

    }
}
