package com.ihealth.aijiakang.widgets.flowlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.ihealth.aijiakang.system.AJKLog;

/**
 * Created by YanJiaqi on 15/11/30
 */
public class AddPictureLayout extends FlowLayout{
    private final String TAG = "AddPictureLayout";
    private int colums = 4;
    private Context context;
    private int itemSize = 0;


    public AddPictureLayout(Context context) {
        super(context);
        this.context = context;
    }

    public AddPictureLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getWidth();
        if(width > 0){
            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            this.itemSize = (width - paddingLeft - paddingRight) / colums;
//            AJKLog.i(TAG, "itemSize = " + itemSize);

            int childCount = getChildCount();
            for(int i = 0;i<childCount;i++){
                final View child = this.getChildAt(i);
                if (child.getVisibility() == GONE) {
                    continue;
                }
                final LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if(lp!=null){
//                    AJKLog.i(TAG, "child lp != null");
                    lp.width = itemSize;
                    lp.height = itemSize;
                }else{
//                    AJKLog.i(TAG, "child lp == null");
                    child.setLayoutParams(new ViewGroup.LayoutParams(itemSize,itemSize));
                }
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
