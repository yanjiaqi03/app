package com.ihealth.aijiakang.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.ihealth.aijiakang.utils.ClickStateUtils;

/**
 * Created by YanJiaqi on 15/11/28
 */
public class ClickAlphaTextView extends FontTextView {
    private final String TAG = "ClickTextView";
    private float alpha = 0f;

    public ClickAlphaTextView(Context context) {
        super(context);
        initClick();
    }

    public ClickAlphaTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initClick();
    }

    public void initClick() {
        alpha = this.getAlpha();
        ClickStateUtils clickUtil = new ClickStateUtils();
        clickUtil.setAlphaClick(this,alpha);
    }
}
