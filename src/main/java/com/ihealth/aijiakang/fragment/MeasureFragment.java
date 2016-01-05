package com.ihealth.aijiakang.fragment;

import android.content.Context;
import android.view.View;

import com.ihealth.aijiakang.system.AJKLog;

import java.util.Timer;
import java.util.TimerTask;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/18.
 */
public class MeasureFragment extends BaseFragment {
    private final String TAG = "MeasureFragment";
    public MeasureFragment() {
        super(R.layout.fragment_host_measure);
    }

    @Override
    public void mViewCreated(Context context, View view) {

    }

    @Override
    public void mOnDestroyView(Context context) {

    }

    @Override
    public void mOnShow() {
        AJKLog.d(TAG, TAG + "-> mOnShow");
    }

    @Override
    public void mOnHidden() {
        AJKLog.d(TAG, TAG + "-> mOnHidden");
    }
}
