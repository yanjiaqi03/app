package com.ihealth.aijiakang.fragment;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ihealth.aijiakang.system.AJKLog;
import com.ihealth.aijiakang.widgets.FontTextView;

import org.w3c.dom.Text;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/18.
 */
public class DiscoverFragment extends BaseFragment {
    private final String TAG = "DiscoverFragment";
    private FontTextView titleTv;
    public DiscoverFragment() {
        super(R.layout.fragment_host_discover);
    }

    @Override
    public void mViewCreated(Context context, View view) {
        titleTv = (FontTextView) view.findViewById(R.id.host_discover_title_tv);
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
