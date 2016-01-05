package com.ihealth.aijiakang.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.ihealth.aijiakang.utils.ClickStateUtils;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/26
 */
public class ClickBgFrameLayout extends CustomFrameLayout{
    private final String TAG = "ClickLinearLayout";
    private Drawable mDrawable = null;
    private Drawable clickDrawable = null;

    public ClickBgFrameLayout(Context context) {
        super(context);
        initClick();
    }

    public ClickBgFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initClick();
    }

    public void initClick(){
        mDrawable = this.getBackground();
        clickDrawable = getResources().getDrawable(R.drawable.click_drawable);
        ClickStateUtils clickUtil = new ClickStateUtils();
        clickUtil.setBgClick(this, mDrawable, clickDrawable);
    }
}
