package com.ihealth.aijiakang.fragment;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.ihealth.aijiakang.activity.comm.ForgetPasswdActivity;
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
public class ForgetPasswdSettingFragment extends BaseFragment{
    private final String TAG = "ForgetPasswdSettingFragment";

    /** XML Resource **/
    private FrameLayout back;
    private FontEditText passwdEdit;
    private FontTextView finish;

    public ForgetPasswdSettingFragment(){
        super(R.layout.fragment_forget_setting);
    }
    @Override
    public void mViewCreated(final Context context, View view) {
        back = (FrameLayout) view.findViewById(R.id.forget_passwd_setting_return);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ForgetPasswdActivity)getActivity()).runBack();
            }
        });

        passwdEdit = (FontEditText) view.findViewById(R.id.forget_passwdset_setting_edittext);

        finish = (FontTextView) view.findViewById(R.id.forget_passwd_setting_finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = passwdEdit.getText().toString().trim();
                if(!passwdEdit.checkPasswdWithToast(password)){
                    return;
                }
                String mobilePhone = ((ForgetPasswdActivity)getActivity()).getMobilePhone();
                String verifyCode = ((ForgetPasswdActivity)getActivity()).getVerifyCode();
                UserCloudMethod.getInstance().changePasswordByVerifyCode(context, mobilePhone, verifyCode, password, new CloudCallback() {
                    @Override
                    public void onSuccess(ArrayList arrayList) {
                        finishPasswdSetting();
                    }
                    @Override
                    public void onFailure(String backNum, String str) {

                    }
                });
            }
        });
    }

    /**
     * 完成密码设置
     * Author YanJiaqi
     * Created at 15/11/26 下午2:24
     */

    private void finishPasswdSetting(){
        if(getActivity() == null){
            return;
        }
        getActivity().finish();
    }

    @Override
    public void mOnDestroyView(Context context) {

    }

    @Override
    public void mOnShow() {

    }

    @Override
    public void mOnHidden() {

    }
}
