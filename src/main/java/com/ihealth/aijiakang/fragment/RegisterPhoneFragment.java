package com.ihealth.aijiakang.fragment;

import android.content.Context;
import android.view.View;

import com.ihealth.aijiakang.activity.comm.ForgetPasswdActivity;
import com.ihealth.aijiakang.activity.comm.RegisterActivity;
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
 * Created by YanJiaqi on 15/11/23
 */
public class RegisterPhoneFragment extends BaseFragment {
    private final String TAG = "RegisterPhoneFragment";
    private FontTextView phoneFinish;
    private FontEditText phoneEdit;
    private FontEditText codeEdit;
    private FontTextView getCode;
    public RegisterPhoneFragment() {
        super(R.layout.fragment_register_phone);
    }

    @Override
    public void mViewCreated(Context context, View view) {
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

        phoneFinish = (FontTextView) view.findViewById(R.id.register_phone_finish);
        phoneFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phoneEdit.getText().toString().trim();
                if(!phoneEdit.checkPhoneWithToast(phone)){
                    return;
                }
                String code = codeEdit.getText().toString().trim();
                if(!codeEdit.checkVerifyCodeWithToast(code)){
                    return;
                }
                ((RegisterActivity) getActivity()).setMobilePhone(phone);

                showLoadingBar(true);
                UserCloudMethod.getInstance().commitVerifyCode(getActivity(), phoneEdit.getText().toString().trim(),
                        codeEdit.getText().toString().trim(), new CloudCallback<String>() {
                            @Override
                            public void onSuccess(ArrayList<String> list) {
                                if(getActivity() == null){
                                    return;
                                }
                                AJKLog.i(TAG, "commit verify code success");
                                jumpToInputcode();
                            }
                            @Override
                            public void onFailure(String backNum, String str) {
                                if(getActivity() == null){
                                    return;
                                }
                                showLoadingBar(false);
                                AJKLog.i(TAG, "commit verify code failed");
                            }
                        });

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
        UserCloudMethod.getInstance().getRegisterVerifyCode(getActivity(), phone, new CloudCallback<String>() {
            @Override
            public void onSuccess(ArrayList<String> list) {
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
                if(getActivity() == null){
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

    private void jumpToInputcode(){
        if(getActivity() == null){
            return;
        }
        showLoadingBar(false);
        ((RegisterActivity)getActivity()).changeFragment(RegisterPhoneFragment.this,new RegisterInfoFragment());
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
