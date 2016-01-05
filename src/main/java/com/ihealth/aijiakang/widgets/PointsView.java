package com.ihealth.aijiakang.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.ihealth.aijiakang.system.AJKLog;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/12/2
 */
public class PointsView extends BaseView{
    private final String TAG = "PointsView";
    private float pointSize;
    private float pointInterval;
    private Paint mPaint;
    private int SelectPos = 0;
    private int totalSize = 4;
    private int selectColor = 0;
    private int unselectColor = 0;

    public PointsView(Context context) {
        super(context);
        this.pointSize = context.getResources().getDimension(R.dimen.point_size);
        this.pointInterval = context.getResources().getDimension(R.dimen.point_interval);
    }

    public PointsView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PointsView);
        this.pointSize = context.getResources().getDimension(R.dimen.point_size);
        this.pointInterval = context.getResources().getDimension(R.dimen.point_interval);

        totalSize = a.getInt(R.styleable.PointsView_pointNum, 4);
        selectColor = a.getColor(R.styleable.PointsView_select, 0xffff6633);
        unselectColor = a.getColor(R.styleable.PointsView_unselect, 0xffe6e6e6);

        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(this.getLayoutParams()!=null){
            if(this.getLayoutParams().width <= 0 || this.getLayoutParams().height<=0){
                AJKLog.i(TAG, "onMeasure");
                this.getLayoutParams().height = (int) pointSize;
                this.getLayoutParams().width = (int) ((totalSize-1)*pointInterval + pointSize);
                this.setLayoutParams(this.getLayoutParams());
            }
        }
    }

    @Override
    protected void drawSub(Rect mRect, Canvas canvas, float ratioX, float ratioY) {
        if(mPaint == null){
            mPaint = new Paint();
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setAntiAlias(true);
        }
        for(int i = 0;i<totalSize;i++){
            if(i == SelectPos){
                mPaint.setColor(selectColor);
            }else{
                mPaint.setColor(unselectColor);
            }

            canvas.drawCircle(i*pointInterval+pointSize/2,pointSize/2,pointSize/2,mPaint);
        }
    }

    public void setSelectPosition(int position){
        SelectPos = position;
        invalidate();
    }

    @Override
    protected void logic() {

    }

    @Override
    protected void reset() {

    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
        ViewGroup.LayoutParams lp = this.getLayoutParams();
        if(lp!=null){
            lp.height = (int) pointSize;
            lp.width = (int) ((totalSize-1)*pointInterval + pointSize);
        }
        this.setLayoutParams(lp);
    }
}
