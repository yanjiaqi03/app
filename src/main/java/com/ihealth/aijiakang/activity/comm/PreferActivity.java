package com.ihealth.aijiakang.activity.comm;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.ihealth.aijiakang.activity.BaseActivity;
import com.ihealth.aijiakang.logic.control.BpControl;
import com.ihealth.aijiakang.logic.control.VoiceControl;
import com.ihealth.aijiakang.sharedpreference.AppConfig;
import com.ihealth.aijiakang.widgets.FontTextView;
import com.ihealth.aijiakang.widgets.SwitchImageView;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/29
 */
public class PreferActivity extends BaseActivity {
    private final String TAG = "PreferActivity";

    /** XML Resource **/
    private FrameLayout back;
    private RelativeLayout unitLayout;
    private FontTextView unitTv;
    private SwitchImageView testingSwitch;
    private SwitchImageView resultSwitch;

    @Override
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);

        setContentView(R.layout.activity_prefer);

        initViews();
    }

    private void initViews() {
        back = (FrameLayout) findViewById(R.id.prefer_return);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferActivity.this.finish();
            }
        });

        unitLayout = (RelativeLayout) findViewById(R.id.prefer_unit_layout);
        unitLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        unitTv = (FontTextView) findViewById(R.id.prefer_unit_tv);
        unitTv.setText(BpControl.getInstance().getUnit(this));

        testingSwitch = (SwitchImageView) findViewById(R.id.prefer_testing_voice_switch);
        testingSwitch.setSwitchState(VoiceControl.getInstance().readTestingVoice(this));
        testingSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (testingSwitch.getCurrentState() == SwitchImageView.OFF_STATE) {
                    testingSwitch.setSwitchState(SwitchImageView.ON_STATE);
                    AppConfig.getInstance().setConfigValueTestingVoice(PreferActivity.this,AppConfig.VOICE_ON);
                } else if (testingSwitch.getCurrentState() == SwitchImageView.ON_STATE) {
                    testingSwitch.setSwitchState(SwitchImageView.OFF_STATE);
                    AppConfig.getInstance().setConfigValueTestingVoice(PreferActivity.this, AppConfig.VOICE_OFF);
                }
            }
        });

        resultSwitch = (SwitchImageView) findViewById(R.id.prefer_result_voice_switch);
        resultSwitch.setSwitchState(VoiceControl.getInstance().readResultVoice(this));
        resultSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (resultSwitch.getCurrentState() == SwitchImageView.OFF_STATE) {
                    resultSwitch.setSwitchState(SwitchImageView.ON_STATE);
                    AppConfig.getInstance().setConfigValueResultVoice(PreferActivity.this, AppConfig.VOICE_ON);
                } else if (resultSwitch.getCurrentState() == SwitchImageView.ON_STATE) {
                    resultSwitch.setSwitchState(SwitchImageView.OFF_STATE);
                    AppConfig.getInstance().setConfigValueResultVoice(PreferActivity.this, AppConfig.VOICE_OFF);
                }
            }
        });
    }
}
