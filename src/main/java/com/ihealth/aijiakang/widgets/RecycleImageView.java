package com.ihealth.aijiakang.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.ihealth.aijiakang.utils.DrawableUtils;

/**
 * Created by YanJiaqi on 15/12/2
 */
public class RecycleImageView extends ImageView{
    public RecycleImageView(Context context) {
        super(context);
    }

    public RecycleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecycleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDetachedFromWindow() {
        setImageBitmap(null);
        DrawableUtils.getInstance().clearDrawable(getDrawable());
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
