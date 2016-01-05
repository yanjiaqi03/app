package com.ihealth.aijiakang.activity.comm;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.ihealth.aijiakang.activity.BaseActivity;
import com.ihealth.aijiakang.system.SystemTool;
import com.ihealth.aijiakang.widgets.ClickBgRelativeLayout;
import com.ihealth.aijiakang.widgets.FontTextView;
import com.ihealth.aijiakang.widgets.toast.HintToast;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/27
 */
public class AboutActivity extends BaseActivity{
    private final String TAG = "AboutActivity";

    /** XML Resource **/
    private FrameLayout back;
    private FontTextView version;
    private ClickBgRelativeLayout customerPhone;
    private ClickBgRelativeLayout wechat;
    private ClickBgRelativeLayout qq;

    @Override
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);

        setContentView(R.layout.activity_about);
        initViews();
    }

    private void initViews(){
        back = (FrameLayout) findViewById(R.id.about_return);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

        version = (FontTextView) findViewById(R.id.about_version);
        version.setText(getResources().getString(R.string.about_version) + SystemTool.getInstance().getVersionName(AboutActivity.this));

        customerPhone = (ClickBgRelativeLayout) findViewById(R.id.about_customer_service_layout);
        customerPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SystemTool.getInstance().jumpToPhone(AboutActivity.this,getResources().getString(R.string.about_customer_service_phone));
            }
        });

        wechat = (ClickBgRelativeLayout) findViewById(R.id.about_wechat_layout);
        wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SystemTool.getInstance().copy(AboutActivity.this,getResources().getString(R.string.about_wechat_value));
                HintToast.makeText(AboutActivity.this,getResources().getString(R.string.copy_success),HintToast.LENGTH_SHORT).show();
            }
        });

        qq = (ClickBgRelativeLayout) findViewById(R.id.about_qq_layout);
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SystemTool.getInstance().copy(AboutActivity.this,getResources().getString(R.string.about_qq_value));
                HintToast.makeText(AboutActivity.this, getResources().getString(R.string.copy_success), HintToast.LENGTH_SHORT).show();
            }
        });
    }
}
