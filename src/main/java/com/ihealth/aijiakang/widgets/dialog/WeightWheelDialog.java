package com.ihealth.aijiakang.widgets.dialog;

import iHealth.AiJiaKang.MI.R;

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
import com.ihealth.aijiakang.widgets.wheel.NumericWheelAdapter;
import com.ihealth.aijiakang.widgets.wheel.OnWheelChangedListener;
import com.ihealth.aijiakang.widgets.wheel.OnWheelClickedListener;
import com.ihealth.aijiakang.widgets.wheel.WheelView;

public class WeightWheelDialog extends Dialog implements
		View.OnClickListener {
	private Context context;
	private final int weight_int_start = 10, weight_int_end = 160;
	private final int weight_float_start = 0, weight_float_end = 9;

	private float now_weight;
	private int now_weight_int,now_weight_float;
	private WheelView weight_int_wheel,weight_float_wheel;
	private FontTextView cancel, ok;
	private weightCallback callback = null;

	public WeightWheelDialog(Context context, float now_weight,
							 weightCallback callback) {
		super(context, R.style.daily_activity_dialog);
		this.context = context;
		this.now_weight = now_weight;
		this.callback = callback;
		this.setCancelable(true);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Window window = this.getWindow();
		window.setContentView(R.layout.dialog_weight_wheel);
		window.setGravity(Gravity.BOTTOM);
		WindowManager windowManager = window.getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		WindowManager.LayoutParams lp = window.getAttributes();
		lp.width = (display.getWidth()); // 设置宽度
		window.setAttributes(lp);

		cancel = (FontTextView) window.findViewById(R.id.wheel_weight_cancel_bt);
		cancel.setOnClickListener(this);
		ok = (FontTextView) window.findViewById(R.id.wheel_weight_ok_bt);
		ok.setOnClickListener(this);


		now_weight_int = (int)now_weight;
		if(now_weight_int < weight_int_start){
			now_weight_int = weight_int_start;
		}
		now_weight_float = (int) (now_weight*10%10);
		if (now_weight_int > weight_int_end) {
			now_weight_int = weight_int_end;
		}

		weight_int_wheel = (WheelView) window.findViewById(R.id.weight_wheel_int);
		weight_int_wheel.setViewAdapter(new DateNumericAdapter(context,
				weight_int_start, weight_int_end, now_weight_int-weight_int_start));
		weight_int_wheel.addChangingListener(weightIntChangedListener);
		weight_int_wheel.addClickingListener(weightIntClickedListener);
		weight_int_wheel.setCurrentItem(now_weight_int-weight_int_start);
		
		
		weight_float_wheel = (WheelView) window.findViewById(R.id.weight_wheel_float);
		weight_float_wheel.setViewAdapter(new DateNumericAdapter(context,
				weight_float_start, weight_float_end, now_weight_float-weight_float_start));
		weight_float_wheel.addChangingListener(weightFloatChangedListener);
		weight_float_wheel.addClickingListener(weightFloatClickedListener);
		weight_float_wheel.setCurrentItem(now_weight_float-weight_float_start);
		weight_float_wheel.setCyclic(true);
		
		window.setWindowAnimations(R.style.translate_dialog);
	}

	private OnWheelChangedListener weightIntChangedListener = new OnWheelChangedListener() {

		@Override
		public void onChanged(WheelView wheel, int oldValue, int newValue) {
			// TODO Auto-generated method stub
			now_weight_int = weight_int_start + wheel.getCurrentItem();
			weight_int_wheel.setViewAdapter(new DateNumericAdapter(context,
					weight_int_start, weight_int_end, wheel.getCurrentItem()));
		}
	};

	private OnWheelClickedListener weightIntClickedListener = new OnWheelClickedListener() {

		@Override
		public void onItemClicked(WheelView wheel, int itemIndex) {
			// TODO Auto-generated method stub
			wheel.setCurrentItem(itemIndex, true);
		}
	};
	
	private OnWheelChangedListener weightFloatChangedListener = new OnWheelChangedListener() {

		@Override
		public void onChanged(WheelView wheel, int oldValue, int newValue) {
			// TODO Auto-generated method stub
			now_weight_float = weight_float_start + wheel.getCurrentItem();
			weight_float_wheel.setViewAdapter(new DateNumericAdapter(context,
					weight_float_start, weight_float_end, wheel.getCurrentItem()));
		}
	};

	private OnWheelClickedListener weightFloatClickedListener = new OnWheelClickedListener() {

		@Override
		public void onItemClicked(WheelView wheel, int itemIndex) {
			// TODO Auto-generated method stub
			wheel.setCurrentItem(itemIndex, true);
		}
	};

	/**
	 * Adapter for numeric wheels. Highlights the current value.
	 */
	private class DateNumericAdapter extends NumericWheelAdapter {
		// Index of current item
		int currentItem;
		// Index of item to be highlighted
		int currentValue;

		/**
		 * Constructor
		 */
		public DateNumericAdapter(Context context, int minValue, int maxValue,
				int current) {
			super(context, minValue, maxValue);
			this.currentValue = current;
		}

		@Override
		protected void configureTextView(TextView view) {
			super.configureTextView(view);
			if (currentItem == currentValue) {
				view.setTextColor(0xffff6633);
				view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
			} else {
				view.setTextColor(0xff999999);
				view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
			}

//			view.setTypeface(Typeface.DEFAULT);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			currentItem = index;
			return super.getItem(index, cachedView, parent);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.wheel_weight_ok_bt:
			if (callback != null) {
				now_weight = (now_weight_int*10+now_weight_float)*1f/10;
				callback.getWeight(now_weight);
			}
			WeightWheelDialog.this.dismiss();
			break;
		case R.id.wheel_weight_cancel_bt:
			WeightWheelDialog.this.dismiss();
			break;
		default:
			break;
		}
	}
	
	public interface weightCallback{
		void getWeight(float weight);
	}
	// /**
	// * Adapter for string based wheel. Highlights the current value.
	// */
	// private class DateArrayAdapter extends ArrayWheelAdapter<String> {
	// // Index of current item
	// int currentItem;
	// // Index of item to be highlighted
	// int currentValue;
	//
	// /**
	// * Constructor
	// */
	// public DateArrayAdapter(Context context, String[] items, int current) {
	// super(context, items);
	// this.currentValue = current;
	// }
	//
	// @Override
	// protected void configureTextView(TextView view) {
	// super.configureTextView(view);
	// if (currentItem == currentValue) {
	// view.setTextColor(0xfff27725);
	// view.setTypeface(Typeface.DEFAULT_BOLD);
	// }else{
	// view.setTextColor(0xff000000);
	// view.setTypeface(Typeface.DEFAULT);
	// }
	// }
	//
	// @Override
	// public View getItem(int index, View cachedView, ViewGroup parent) {
	// currentItem = index;
	// return super.getItem(index, cachedView, parent);
	// }
	// }
}
