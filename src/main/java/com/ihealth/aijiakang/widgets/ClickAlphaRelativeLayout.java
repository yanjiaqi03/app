package com.ihealth.aijiakang.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.ihealth.aijiakang.utils.ClickStateUtils;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/26
 */
public class ClickAlphaRelativeLayout extends RelativeLayout{
    private final String TAG = "ClickRelativeLayout";
    private float alpha = 0f;

    public ClickAlphaRelativeLayout(Context context) {
        super(context);
        initClick();
    }

    public ClickAlphaRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initClick();
    }

    public void initClick(){
        alpha = this.getAlpha();
        ClickStateUtils clickUtil = new ClickStateUtils();
        clickUtil.setAlphaClick(this, alpha);
    }
}
