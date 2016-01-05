package com.ihealth.aijiakang.fragment;

import android.content.Context;
import android.view.View;

import com.ihealth.aijiakang.activity.comm.RegisterActivity;
import com.ihealth.aijiakang.logic.LogicCallbackInterface;
import com.ihealth.aijiakang.logic.control.LoginControl;
import com.ihealth.aijiakang.widgets.FontEditText;
import com.ihealth.aijiakang.widgets.FontTextView;
import com.ihealth.aijiakang.widgets.toast.HintToast;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/23
 */
public class RegisterInfoFragment extends BaseFragment{
    private final String TAG = "RegisterInfoFragment";
    private FontEditText usernameEdit;
    private FontEditText passwdEdit;
    private FontTextView finish;
    public RegisterInfoFragment(){
        super(R.layout.fragment_register_info);
    }
    @Override
    public void mViewCreated(final Context context, View view) {
        usernameEdit = (FontEditText) view.findViewById(R.id.register_input_username);
        passwdEdit = (FontEditText) view.findViewById(R.id.register_passwdset_edittext);
        finish = (FontTextView) view.findViewById(R.id.register_passwd_finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = usernameEdit.getText().toString().trim();
                if (username.length() == 0
                        || username.length() > getResources().getInteger(R.integer.username_max_length)) {
                    HintToast.makeText(getActivity(), getResources().getString(R.string.check_username_error), HintToast.LENGTH_SHORT).show();
                    return;
                }
                final String password = passwdEdit.getText().toString().trim();
                if (password.length() < getResources().getInteger(R.integer.password_min_length)
                        || password.length() > getResources().getInteger(R.integer.password_max_length)) {
                    HintToast.makeText(getActivity(), getResources().getString(R.string.check_passwd_error), HintToast.LENGTH_SHORT).show();
                    return;
                }
                showLoadingBar(true);
                //执行创建账户
                final String un = ((RegisterActivity) getActivity()).getMobilePhone();
                LoginControl.getInstance().registerAccount(getActivity(), un, password, username, new LogicCallbackInterface() {
                            @Override
                            public void onSuccess(String result) {
                                if(getActivity() == null){
                                    return;
                                }
                                finishRegist();
                            }
                            @Override
                            public void onFailure(String errorNum, String content) {
                                if(getActivity() == null){
                                    return;
                                }
                                showLoadingBar(false);
                            }
                        });

            }
        });
    }

    /**
     * 完成注册
     * Author YanJiaqi
     * Created at 15/11/23 下午4:49
     */
    private void finishRegist(){
        if(getActivity() == null){
            return;
        }
        showLoadingBar(false);
        ((RegisterActivity)getActivity()).jumpToHost();
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
