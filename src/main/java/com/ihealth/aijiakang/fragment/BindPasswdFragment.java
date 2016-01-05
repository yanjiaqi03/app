package com.ihealth.aijiakang.fragment;

import android.content.Context;
import android.view.View;

import com.ihealth.aijiakang.activity.comm.BindPhoneActivity;
import com.ihealth.aijiakang.utils.RegularUtils;
import com.ihealth.aijiakang.widgets.FontEditText;
import com.ihealth.aijiakang.widgets.FontTextView;
import com.ihealth.aijiakang.widgets.toast.HintToast;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/23
 */
public class BindPasswdFragment extends BaseFragment{
    private final String TAG = "BindPasswdFragment";
    private FontTextView tip;
    private FontEditText passwdEdit;
    private FontTextView finish;

    public BindPasswdFragment(){
        super(R.layout.fragment_bindphone_passwd);
    }
    @Override
    public void mViewCreated(final Context context, View view) {
        tip = (FontTextView) view.findViewById(R.id.bindphone_passwd_tip);
        tip.setText(getResources().getString(R.string.bindphone_passwd_tip0)
                + RegularUtils.getInstance().changePhoneToX(((BindPhoneActivity) getActivity()).getMobilePhone())
                +getResources().getString(R.string.bindphone_passwd_tip1));

        passwdEdit = (FontEditText) view.findViewById(R.id.bindphone_passwd_edittext);
        finish = (FontTextView) view.findViewById(R.id.bindphone_passwd_finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String password = passwdEdit.getText().toString().trim();
                if(!passwdEdit.checkPasswdWithToast(password)){
                    return;
                }
                showLoadingBar(true);
                finishBind();
            }
        });
    }

    /**
     * 完成注册
     * Author YanJiaqi
     * Created at 15/11/23 下午4:49
     */
    private void finishBind(){
        if(getActivity() == null){
            return;
        }
        showLoadingBar(false);
        ((BindPhoneActivity)getActivity()).goBack();
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
