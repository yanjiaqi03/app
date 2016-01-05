package com.ihealth.aijiakang.widgets.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ihealth.aijiakang.widgets.FontTextView;
import com.ihealth.aijiakang.widgets.wheel.ArrayWheelAdapter;
import com.ihealth.aijiakang.widgets.wheel.NumericWheelAdapter;
import com.ihealth.aijiakang.widgets.wheel.OnWheelChangedListener;
import com.ihealth.aijiakang.widgets.wheel.OnWheelClickedListener;
import com.ihealth.aijiakang.widgets.wheel.StringWheelAdapter;
import com.ihealth.aijiakang.widgets.wheel.WheelView;

import iHealth.AiJiaKang.MI.R;

public class GenderWheelDialog extends Dialog implements
        View.OnClickListener {
    private Context context;
    private int now_gender = 0;
    private WheelView gender_wheel;
    private FontTextView cancel, ok;
    private genderCallback callback = null;
    private String MALE = "";
    private String FEMALE = "";
    private String[] genderArr;

    public GenderWheelDialog(Context context, String gender,
                             genderCallback callback) {
        super(context, R.style.daily_activity_dialog);
        this.context = context;
        this.MALE = context.getResources().getString(R.string.male);
        this.FEMALE = context.getResources().getString(R.string.female);
        if (gender.equals(FEMALE)) {
            now_gender = 1;
        } else {
            now_gender = 0;
        }
        this.genderArr = new String[]{MALE,FEMALE};
        this.callback = callback;
        this.setCancelable(true);
    }

    public interface genderCallback {
        void getGender(String gender);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.setContentView(R.layout.dialog_gender_wheel);
        window.setGravity(Gravity.BOTTOM);
        WindowManager windowManager = window.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (display.getWidth()); // 设置宽度
        window.setAttributes(lp);

        cancel = (FontTextView) window.findViewById(R.id.wheel_gender_cancel_bt);
        cancel.setOnClickListener(this);
        ok = (FontTextView) window.findViewById(R.id.wheel_gender_ok_bt);
        ok.setOnClickListener(this);


        gender_wheel = (WheelView) window.findViewById(R.id.gender_wheel);
        gender_wheel.setViewAdapter(new GenderWheelAdapter(context, genderArr,now_gender));
        gender_wheel.addChangingListener(genderChangedListener);
        gender_wheel.addClickingListener(genderClickedListener);
        gender_wheel.setCurrentItem(now_gender);

        window.setWindowAnimations(R.style.translate_dialog);
    }

    private OnWheelChangedListener genderChangedListener = new OnWheelChangedListener() {

        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            // TODO Auto-generated method stub
            now_gender = wheel.getCurrentItem();
            gender_wheel.setViewAdapter(new GenderWheelAdapter(context, genderArr,now_gender));
        }
    };

    private OnWheelClickedListener genderClickedListener = new OnWheelClickedListener() {

        @Override
        public void onItemClicked(WheelView wheel, int itemIndex) {
            // TODO Auto-generated method stub
            wheel.setCurrentItem(itemIndex, true);
        }
    };

    //	/**
//	 * Adapter for numeric wheels. Highlights the current value.
//	 */
    private class GenderWheelAdapter extends ArrayWheelAdapter<String> {
        int currentItem;
        int currentValue;

        public GenderWheelAdapter(Context context, String[] items,int currentValue) {
            super(context, items);
            this.currentValue = currentValue;
        }

        @Override
        protected void configureTextView(TextView view) {
            super.configureTextView(view);
            if(currentValue == currentItem){
                view.setTextColor(0xffff6633);
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
            }else{
                view.setTextColor(0xff999999);
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
            }
        }

        @Override
        public View getItem(int index, View convertView, ViewGroup parent) {
            currentItem = index;
            return super.getItem(index, convertView, parent);
        }
    }
//	private class GenderWheelAdapter extends StringWheelAdapter {
//		// Index of current item
//		int currentItem;
//		// Index of item to be highlighted
//		int currentValue;
//
//		/**
//		 * Constructor
//		 */
//		public GenderWheelAdapter(Context context, int minValue, int maxValue,
//				int current) {
//			super(context, minValue, maxValue);
//			this.currentValue = current;
//		}
//
//		@Override
//		protected void configureTextView(TextView view) {
//			super.configureTextView(view);
//			if (currentItem == currentValue) {
//				view.setTextColor(0xffff6633);
//				view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
//			} else {
//				view.setTextColor(0xff999999);
//				view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
//			}
//
////			view.setTypeface(Typeface.DEFAULT);
//		}
//
//		@Override
//		public View getItem(int index, View cachedView, ViewGroup parent) {
//			currentItem = index;
//			return super.getItem(index, cachedView, parent);
//		}
//	}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wheel_gender_ok_bt:
                if (callback != null) {
                    if (now_gender == 0) {
                        callback.getGender(MALE);
                    } else if (now_gender == 1) {
                        callback.getGender(FEMALE);
                    }
                }
                GenderWheelDialog.this.dismiss();
                break;
            case R.id.wheel_gender_cancel_bt:
                GenderWheelDialog.this.dismiss();
                break;
            default:
                break;
        }
    }
}
