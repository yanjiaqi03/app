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
public class ClickBgRelativeLayout extends RelativeLayout{
    private final String TAG = "ClickRelativeLayout";
    private Drawable mDrawable = null;
    private Drawable clickDrawable = null;

    public ClickBgRelativeLayout(Context context) {
        super(context);
        initClick();
    }

    public ClickBgRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initClick();
    }

    public void initClick(){
        clickDrawable = getResources().getDrawable(R.drawable.click_drawable);
        mDrawable = this.getBackground();
        ClickStateUtils clickUtil = new ClickStateUtils();
        clickUtil.setBgClick(this, mDrawable, clickDrawable);
    }
}
