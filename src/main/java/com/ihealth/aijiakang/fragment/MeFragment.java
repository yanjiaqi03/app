package com.ihealth.aijiakang.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ihealth.aijiakang.activity.comm.AboutActivity;
import com.ihealth.aijiakang.activity.comm.FeedBackActivity;
import com.ihealth.aijiakang.activity.comm.HostActivity;
import com.ihealth.aijiakang.activity.user.RelationshipActivity;
import com.ihealth.aijiakang.activity.comm.SettingActivity;
import com.ihealth.aijiakang.activity.user.UserInfoActivity;
import com.ihealth.aijiakang.database.data.Data_UserInfo;
import com.ihealth.aijiakang.imageloader.ImageLoaderTools;
import com.ihealth.aijiakang.logic.database.UserInfoLogic;
import com.ihealth.aijiakang.sharedpreference.UserConfig;
import com.ihealth.aijiakang.system.AJKLog;
import com.ihealth.aijiakang.widgets.ClickBgLinearLayout;
import com.ihealth.aijiakang.widgets.ClickBgRelativeLayout;
import com.ihealth.aijiakang.widgets.FontTextView;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/18.
 */
public class MeFragment extends BaseFragment {
    private final String TAG = "MeFragment";
    /** XML Resource **/
    private ClickBgLinearLayout userLayout;
    private ImageView userLogo;
    private FontTextView userName;
    private FontTextView accountTv;
    private ClickBgRelativeLayout deviceLayout;
    private ClickBgRelativeLayout relationLayout;
    private ClickBgRelativeLayout doctorLayout;
    private ClickBgRelativeLayout feedLayout;
    private ClickBgRelativeLayout aboutLayout;
    private ClickBgRelativeLayout settingLayout;

    public MeFragment() {
        super(R.layout.fragment_host_me);
    }

    @Override
    public void mViewCreated(Context context, View view) {
        userLayout = (ClickBgLinearLayout) view.findViewById(R.id.host_me_user_layout);
        userLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HostActivity)getActivity()).jumpActivity(getActivity(), UserInfoActivity.class);
            }
        });
        userLogo = (ImageView) view.findViewById(R.id.host_me_user_logo);
        userName = (FontTextView) view.findViewById(R.id.host_me_user_name);
        accountTv = (FontTextView) view.findViewById(R.id.host_me_account_tv);

        deviceLayout = (ClickBgRelativeLayout) view.findViewById(R.id.host_me_device_layout);
        deviceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        relationLayout = (ClickBgRelativeLayout) view.findViewById(R.id.host_me_relationship_layout);
        relationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt(UserConfig.USER_ID, UserConfig.getInstance().getConfigUserID(getActivity()));
                ((HostActivity)getActivity()).jumpActivity(getActivity(), RelationshipActivity.class, bundle);
            }
        });

        doctorLayout = (ClickBgRelativeLayout) view.findViewById(R.id.host_me_doctor_service_layout);
        doctorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        setDoctorState();
        feedLayout = (ClickBgRelativeLayout) view.findViewById(R.id.host_me_feedback_layout);
        feedLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HostActivity)getActivity()).jumpActivity(getActivity(), FeedBackActivity.class);
            }
        });
        aboutLayout = (ClickBgRelativeLayout) view.findViewById(R.id.host_me_about_layout);
        aboutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HostActivity)getActivity()).jumpActivity(getActivity(), AboutActivity.class);
            }
        });
        settingLayout = (ClickBgRelativeLayout) view.findViewById(R.id.host_me_setting_layout);
        settingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HostActivity)getActivity()).jumpActivity(getActivity(), SettingActivity.class);
            }
        });
    }

    @Override
    public void mOnDestroyView(Context context) {

    }

    @Override
    public void mOnShow() {

        AJKLog.d(TAG, TAG + "-> mOnShow");
        setUserInfo();
    }

    @Override
    public void mOnHidden() {
        AJKLog.d(TAG, TAG + "-> mOnHidden");
    }

    /**
     * 设置是否显示家庭医生服务
     * Author YanJiaqi
     * Created at 15/11/26 上午11:36
     */

    private void setDoctorState(){
        boolean isShow = false;
        if(isShow){
            doctorLayout.setVisibility(View.VISIBLE);
        }else{
            doctorLayout.setVisibility(View.GONE);
        }
    }

    public void setUserInfo() {
        int userId = UserConfig.getInstance().getConfigUserID(getActivity());
        Data_UserInfo data_userInfo  = new UserInfoLogic(getActivity()).searchTableItem(userId);
        if(data_userInfo != null) {
            String url = data_userInfo.getLogo();
            String nickName = data_userInfo.getName();
            AJKLog.i(TAG, "nickName = " + nickName);
            AJKLog.i(TAG, "url = " + url.length());
            userName.setText(nickName);
            accountTv.setText(data_userInfo.getUserName());
            ImageLoaderTools.getInstance().displayNetRoundImg(url, userLogo);
        }else{
            userName.setText("");
            accountTv.setText("");
            ImageLoaderTools.getInstance().displayNetRoundImg("", userLogo);
        }

    }

}
