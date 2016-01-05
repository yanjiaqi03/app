package com.ihealth.aijiakang.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.ihealth.aijiakang.system.AJKLog;
import com.ihealth.aijiakang.utils.DrawableUtils;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/19
 */
public class SwitchTextView extends FontTextView {
    private final String TAG = "SwitchTextView";
    public final static int OFF_STATE = -1;
    public final static int ON_STATE = 1;
    private int offColor = 0;
    private int onColor = 0;
    private Drawable onDrawable;
    private Drawable offDrawable;
    private int currentState = 0;

    public SwitchTextView(Context context) {
        super(context);
    }

    public SwitchTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SwitchTextView);
        offColor = a.getColor(R.styleable.SwitchTextView_offColor, 00000000);
        onColor = a.getColor(R.styleable.SwitchTextView_onColor, 00000000);

        int offRes = a.getResourceId(R.styleable.SwitchTextView_offBg,0);
        AJKLog.i(TAG, "offRes -> " + offRes);
        int onRes = a.getResourceId(R.styleable.SwitchTextView_onBg,0);
        initDrawable(context,offRes,onRes);
        AJKLog.i(TAG, "onRes -> " + onRes);

        a.recycle();
    }

    private void initDrawable(Context context,int offRes,int onRes){
        if(offRes!=0){
            offDrawable = DrawableUtils.getInstance().getDrawable(context,offRes,null);
            AJKLog.i(TAG, "offDrawable -> " + offDrawable);
        }
        if(onRes!=0){
            onDrawable = DrawableUtils.getInstance().getDrawable(context,onRes,null);
            AJKLog.i(TAG, "onDrawable -> " + onDrawable);
        }
    }
    /**
     * 选择TextView的显示状态(开||关)
     * Author YanJiaqi
     * Created at 15/11/19 下午2:41
     */

    public void setSwitchState(int state){
        if(state == OFF_STATE){
            this.setTextColor(offColor);
            this.setBackground(offDrawable);
            this.currentState = OFF_STATE;
        }else if(state == ON_STATE){
            this.setTextColor(onColor);
            this.setBackground(onDrawable);
            this.currentState = ON_STATE;
        }
    }

    public int getCurrentState(){
        return this.currentState;
    }

    @Override
    protected void onDetachedFromWindow() {
        setBackground(null);
        DrawableUtils.getInstance().clearDrawable(offDrawable);
        offDrawable = null;
        DrawableUtils.getInstance().clearDrawable(onDrawable);
        onDrawable = null;
        super.onDetachedFromWindow();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        try {
            super.onDraw(canvas);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
