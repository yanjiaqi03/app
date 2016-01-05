package com.ihealth.aijiakang.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/12/9
 */
public class ResultsLastContentBg extends BaseView{
    private Paint linePaint = null;
    public ResultsLastContentBg(Context context) {
        super(context);
        initWidges(context);
    }

    public ResultsLastContentBg(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWidges(context);
    }

    private void initWidges(Context context){
        if(linePaint == null){
            linePaint = new Paint();
            linePaint.setColor(context.getResources().getColor(R.color.EBEBEB));
            linePaint.setStrokeWidth(context.getResources().getDimension(R.dimen.line_width));
            linePaint.setAntiAlias(true);
            linePaint.setStyle(Paint.Style.FILL);
        }
    }
    @Override
    protected void drawSub(Rect mRect, Canvas canvas, float ratioX, float ratioY) {
        canvas.drawLine((mRect.left + mRect.width()/3)*ratioX,0
        ,(mRect.left + mRect.width()/3)*ratioX,mRect.height()*ratioY,linePaint);

        canvas.drawLine((mRect.left + mRect.width()*2/3)*ratioX,0
                ,(mRect.left + mRect.width()*2/3)*ratioX,mRect.height()*ratioY,linePaint);
    }

    @Override
    protected void logic() {

    }

    @Override
    protected void reset() {

    }
}
