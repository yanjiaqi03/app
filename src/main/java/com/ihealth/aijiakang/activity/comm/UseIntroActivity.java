package com.ihealth.aijiakang.activity.comm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.ihealth.aijiakang.activity.BaseActivity;
import com.ihealth.aijiakang.utils.VariableUtils;
import com.ihealth.aijiakang.widgets.FontTextView;
import com.ihealth.aijiakang.widgets.GuidePager;
import com.ihealth.aijiakang.widgets.PointsView;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/12/2
 */
public class UseIntroActivity extends BaseActivity{
    private final String TAG = "UseIntroActivity";

    /** XML Resource **/
    private FrameLayout back;
    private GuidePager introPager;
    private PointsView introPoints;
    private FontTextView startTest;

    @Override
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);

        setContentView(R.layout.activity_useintro);

        initViews();
    }

    private void initViews(){
        back = (FrameLayout) findViewById(R.id.useintro_return);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

        startTest = (FontTextView) findViewById(R.id.useintro_start_test);
        startTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                UseIntroActivity.this.sendBroadcast(new Intent(VariableUtils.HOST_JUMP_TO_TEST));
                Bundle extras = new Bundle();
                extras.putInt(HostActivity.NEW_INTENT_ACTION,HostActivity.GO_TO_TEST);
                jumpActivity(UseIntroActivity.this, HostActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TOP,extras);
            }
        });

        int[] pagers = new int[]{R.mipmap.useintro_1,R.mipmap.useintro_2,R.mipmap.useintro_3,R.mipmap.useintro_4};

        introPoints = (PointsView) findViewById(R.id.useintro_points);
        introPoints.setSelectPosition(0);

        introPager = (GuidePager) findViewById(R.id.useintro_viewpager);
        introPager.addPagers(getSupportFragmentManager(),pagers);
        introPager.setGuideChangeListener(new GuidePager.GuideChangeListener() {
            @Override
            public void onSelected(int position) {
                introPoints.setSelectPosition(position);
            }
        });
    }
}
