package com.ihealth.aijiakang.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.ihealth.aijiakang.utils.ClickStateUtils;

/**
 * Created by YanJiaqi on 15/11/26
 */
public class ClickAlphaLinearLayout extends LinearLayout{
    private final String TAG = "ClickLinearLayout";
    private float alpha = 0f;

    public ClickAlphaLinearLayout(Context context) {
        super(context);
        initClick();
    }

    public ClickAlphaLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initClick();
    }

    public void initClick(){
        alpha = this.getAlpha();
        ClickStateUtils clickUtil = new ClickStateUtils();
        clickUtil.setAlphaClick(this, alpha);
    }
}
