package com.ihealth.aijiakang.fragment;

import android.content.Context;
import android.view.View;

import com.ihealth.aijiakang.activity.comm.BindPhoneActivity;
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
 * Created by YanJiaqi on 15/11/23
 */
public class BindPhoneFragment extends BaseFragment {
    private final String TAG = "BindPhoneFragment";
    private int mode = 0;
    /**
     * XML Resource
     **/
    private FontTextView phoneFinish;
    private FontEditText phoneEdit;
    private FontEditText codeEdit;
    private FontTextView getCode;
    private FontTextView topTip;
    private FontTextView bottomTip;

    public BindPhoneFragment() {
        super(R.layout.fragment_bindphone_phone);
    }

    @Override
    public void mViewCreated(Context context, View view) {
        mode = ((BindPhoneActivity) getActivity()).getMode();

        phoneEdit = (FontEditText) view.findViewById(R.id.layout_phone_edittext);
        codeEdit = (FontEditText) view.findViewById(R.id.layout_verifycode_edittext);
        topTip = (FontTextView) view.findViewById(R.id.bindphone_phone_top_tip);
        bottomTip = (FontTextView) view.findViewById(R.id.bindphone_phone_bottom_tip);

        getCode = (FontTextView) view.findViewById(R.id.layout_verifycode_bt);
        getCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = phoneEdit.getText().toString().trim();
                if (!phoneEdit.checkPhoneWithToast(phone)) {
                    return;
                }
                runGetCode(phone);
            }
        });

        phoneFinish = (FontTextView) view.findViewById(R.id.bindphone_phone_finish);
        phoneFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phoneEdit.getText().toString().trim();
                if (!phoneEdit.checkPhoneWithToast(phone)) {
                    return;
                }
                String code = codeEdit.getText().toString().trim();
                if (!codeEdit.checkVerifyCodeWithToast(code)) {
                    return;
                }

                if (mode == BindPhoneActivity.MODE_BIND) {
                    ((BindPhoneActivity) getActivity()).setMobilePhone(phone);
                    showLoadingBar(true);
                    jumpToInputPasswd();
                } else if (mode == BindPhoneActivity.MODE_CHANGE) {
                    ((BindPhoneActivity) getActivity()).goBack();
                }
            }
        });

        if (mode == BindPhoneActivity.MODE_BIND) {
            phoneFinish.setText(getResources().getString(R.string.next_step));
            topTip.setText(getResources().getString(R.string.bindphone_phone_tip));
            bottomTip.setVisibility(View.GONE);
        } else if (mode == BindPhoneActivity.MODE_CHANGE) {
            phoneFinish.setText(getResources().getString(R.string.changephone_button_txt));
            topTip.setText(getResources().getString(R.string.bindphone_change_phone_tip));
            bottomTip.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 获取验证码
     * Author YanJiaqi
     * Created at 15/11/24 下午7:09
     */
    private void runGetCode(String phone) {
        getCode.setClickable(false);
        //获取发送验证码
        UserCloudMethod.getInstance().getForgetPswVerifyCode(getActivity(), phone, new CloudCallback<String>() {
            @Override
            public void onSuccess(ArrayList<String> list) {
                if (getActivity() == null) {
                    return;
                }
                ((ForgetPasswdActivity) getActivity()).setVerifyCode(list.get(0));
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
                if (getActivity() == null) {
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

    private void jumpToInputPasswd() {
        if (getActivity() == null) {
            return;
        }
        showLoadingBar(false);
        ((BindPhoneActivity) getActivity()).changeFragment(BindPhoneFragment.this, new BindPasswdFragment());
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
