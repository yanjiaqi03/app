package com.ihealth.aijiakang.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.ihealth.aijiakang.utils.ClickStateUtils;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/28
 */
public class ClickBgTextView extends FontTextView {
    private final String TAG = "ClickTextView";
    private Drawable mDrawable = null;
    private Drawable clickDrawable = null;

    public ClickBgTextView(Context context) {
        super(context);
        initClick();
    }

    public ClickBgTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initClick();
    }

    public void initClick() {
        mDrawable = this.getBackground();
        clickDrawable = getResources().getDrawable(R.drawable.click_drawable);
        ClickStateUtils clickUtil = new ClickStateUtils();
        clickUtil.setBgClick(this,mDrawable,clickDrawable);
    }
}
