package com.ihealth.aijiakang.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ihealth.aijiakang.system.AJKLog;

import iHealth.AiJiaKang.MI.MyApplication;
import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/19
 */
public class FontTextView extends TextView {
    private final String TAG = "FontTextView";

    public FontTextView(Context context) {
        super(context);
        setFont(context, MyApplication.DEFAULT_HYQH);
    }

    public FontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FontTextView);
        String fontPath = a.getString(R.styleable.FontTextView_fontPath);
        if(fontPath==null||fontPath.length()==0){
            setFont(context,MyApplication.DEFAULT_HYQH);
        }else{
            setFont(context,fontPath);
        }
        AJKLog.i(TAG,"fontPath -> " + fontPath);
        int weight = a.getInteger(R.styleable.FontTextView_fontWeight,0);
        setWeight(weight);
        a.recycle();
    }

    private void setFont(Context context,String fontPath){
        MyApplication appContext = (MyApplication) context.getApplicationContext();
        this.setTypeface(appContext.getFont(fontPath));
    }

    private void setWeight(int weight){
        if(weight > 0){
            this.getPaint().setFakeBoldText(true);
        }else{
            this.getPaint().setFakeBoldText(false);
        }
    }
}
