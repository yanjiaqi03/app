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

public class HeightWheelDialog extends Dialog implements
		View.OnClickListener {
	private Context context;
	private final int height_start = 40, height_end = 250;
	private int now_height;
	private WheelView height_wheel;
	private FontTextView cancel, ok;
	private heightCallback callback = null;

	public HeightWheelDialog(Context context, int now_height,
			heightCallback callback) {
		super(context, R.style.daily_activity_dialog);
		this.context = context;
		this.now_height = now_height;
		this.callback = callback;
		this.setCancelable(true);
	}

	public interface heightCallback {
		void getHeight(int height);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Window window = this.getWindow();
		window.setContentView(R.layout.dialog_height_wheel);
		window.setGravity(Gravity.BOTTOM);
		WindowManager windowManager = window.getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		WindowManager.LayoutParams lp = window.getAttributes();
		lp.width = (display.getWidth()); // 设置宽度
		window.setAttributes(lp);

		cancel = (FontTextView) window.findViewById(R.id.wheel_height_cancel_bt);
		cancel.setOnClickListener(this);
		ok = (FontTextView) window.findViewById(R.id.wheel_height_ok_bt);
		ok.setOnClickListener(this);

		if (now_height > height_end) {
			now_height = height_end;
		}
		if (now_height < height_start) {
			now_height = height_start;
		}

		height_wheel = (WheelView) window.findViewById(R.id.height_wheel);
		height_wheel.setViewAdapter(new DateNumericAdapter(context,
				height_start, height_end, now_height - height_start));
		height_wheel.addChangingListener(heightChangedListener);
		height_wheel.addClickingListener(heightClickedListener);
		height_wheel.setCurrentItem(now_height - height_start);

		window.setWindowAnimations(R.style.translate_dialog);
	}

	private OnWheelChangedListener heightChangedListener = new OnWheelChangedListener() {

		@Override
		public void onChanged(WheelView wheel, int oldValue, int newValue) {
			// TODO Auto-generated method stub
			now_height = height_start + wheel.getCurrentItem();
			height_wheel.setViewAdapter(new DateNumericAdapter(context,
					height_start, height_end, wheel.getCurrentItem()));
		}
	};

	private OnWheelClickedListener heightClickedListener = new OnWheelClickedListener() {

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
		case R.id.wheel_height_ok_bt:
			if (callback != null) {
				callback.getHeight(now_height);
			}
			HeightWheelDialog.this.dismiss();
			break;
		case R.id.wheel_height_cancel_bt:
			HeightWheelDialog.this.dismiss();
			break;
		default:
			break;
		}
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
