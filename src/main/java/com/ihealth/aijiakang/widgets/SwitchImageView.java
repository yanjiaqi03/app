package com.ihealth.aijiakang.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.ihealth.aijiakang.system.AJKLog;
import com.ihealth.aijiakang.utils.DrawableUtils;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/19
 */
public class SwitchImageView extends ImageView{
    private final String TAG = "SwitchImageView";
    private Drawable offDrawable = null;
    private Drawable onDrawable = null;
    public final static int OFF_STATE = -1;
    public final static int ON_STATE = 1;
    private int currentState = 0;


    public SwitchImageView(Context context) {
        super(context);
    }

    public SwitchImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SwitchImageView);
        int offRes = a.getResourceId(R.styleable.SwitchImageView_offSrc, 0);
        AJKLog.i(TAG,"offRes -> " + offRes);
        int onRes = a.getResourceId(R.styleable.SwitchImageView_onSrc,0);
        AJKLog.i(TAG,"onRes -> " + onRes);
        initDrawable(context,offRes,onRes);
        a.recycle();
    }

    private void initDrawable(Context context,int offRes,int onRes){
        if(offRes!=0){
            offDrawable = DrawableUtils.getInstance().getDrawable(context,offRes,null);
        }
        if(onRes!=0){
            onDrawable = DrawableUtils.getInstance().getDrawable(context,onRes,null);
        }
    }

    /**
     * 选择ImageView的显示状态(开||关)
     * Author YanJiaqi
     * Created at 15/11/19 下午1:56
     */

    public void setSwitchState(int state){
        if(state == OFF_STATE){
            this.currentState = OFF_STATE;
            this.setImageDrawable(offDrawable);
        }else if(state == ON_STATE){
            this.currentState = ON_STATE;
            this.setImageDrawable(onDrawable);
        }
    }

    public int getCurrentState(){
        return this.currentState;
    }

    @Override
    protected void onDetachedFromWindow() {
        setImageDrawable(null);
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
