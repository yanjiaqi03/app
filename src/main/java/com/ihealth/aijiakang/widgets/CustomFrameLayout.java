package com.ihealth.aijiakang.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by YanJiaqi on 15/12/10
 */
public class CustomFrameLayout extends FrameLayout{
    protected SizeChangedListener mSizeChangedListener = null;

    public CustomFrameLayout(Context context) {
        super(context);
    }

    public CustomFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setSizeChangedListener(SizeChangedListener mSizeChangedListener){
        this.mSizeChangedListener = mSizeChangedListener;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(mSizeChangedListener!=null){
            mSizeChangedListener.onSizeChanged(w,h,oldw,oldh);
        }
    }
}
