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

public class DateWheelDialog extends Dialog implements View.OnClickListener {
	private Context context;
	private final int year_start = 1930, year_end = 2050;
	private final int month_start = 1, month_end = 12;
	private int day_start = 1, day_end = 31;
	private int now_year, now_month, now_day;
	private WheelView year_wheel, month_wheel, day_wheel;
	private FontTextView cancel, ok;
	private dateCallback callback = null;

	public DateWheelDialog(Context context, int now_year, int now_month, int now_day, dateCallback callback) {
		super(context, R.style.daily_activity_dialog);
		this.context = context;
		this.now_year = now_year;
		this.now_month = now_month;
		this.now_day = now_day;
		this.callback = callback;
		this.setCancelable(true);
	}

	public interface dateCallback {
		void getDate(int year, int month, int day);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Window window = this.getWindow();
		window.setContentView(R.layout.dialog_date_wheel);
		window.setGravity(Gravity.BOTTOM);
		WindowManager windowManager = window.getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		WindowManager.LayoutParams lp = window.getAttributes();
		lp.width = (display.getWidth()); // 设置宽度
		window.setAttributes(lp);

		cancel = (FontTextView) window.findViewById(R.id.wheel_cancel_bt);
		cancel.setOnClickListener(this);
		ok = (FontTextView) window.findViewById(R.id.wheel_ok_bt);
		ok.setOnClickListener(this);

		if (now_year > year_end) {
			now_year = year_end;
		}
		if (now_year < year_start) {
			now_year = year_start;
		}

		if (now_month > month_end) {
			now_month = month_end;
		}
		if (now_month < month_start) {
			now_month = month_start;
		}

		if (now_day > day_end) {
			now_day = day_end;
		}
		if (now_day < day_start) {
			now_day = day_start;
		}
		year_wheel = (WheelView) window.findViewById(R.id.date_wheel_year);
		month_wheel = (WheelView) window.findViewById(R.id.date_wheel_month);
		day_wheel = (WheelView) window.findViewById(R.id.date_wheel_day);

		year_wheel.setViewAdapter(new DateNumericAdapter(context, year_start, year_end, now_year - year_start));
		year_wheel.addChangingListener(yearChangedListener);
		year_wheel.addClickingListener(yearClickedListener);
		year_wheel.setCurrentItem(now_year - year_start);

		month_wheel.setViewAdapter(new DateNumericAdapter(context, month_start, month_end, now_month - month_start));
		month_wheel.addChangingListener(monthChangedListener);
		month_wheel.addClickingListener(monthClickedListener);
		month_wheel.setCurrentItem(now_month - month_start);

		day_wheel.setViewAdapter(new DateNumericAdapter(context, day_start, day_end, now_day - day_start));
		day_wheel.addChangingListener(dayChangedListener);
		day_wheel.addClickingListener(dayClickedListener);
		day_wheel.setCurrentItem(now_day - day_start);

		window.setWindowAnimations(R.style.translate_dialog);
	}

	private void setDayRain(int year, int month) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			day_end = 31;
		} else {
			day_end = 30;
		}

		if (year % 4 == 0) {// 闰年
			if (month == 2) {
				day_end = 29;
			}
		} else {
			if (month == 2) {
				day_end = 28;
			}
		}
		int day = now_day;
		if(day>=day_end){
			now_day = day_end;
		}else{
			now_day = day;
		}
		if(now_day-1>=0){
			day_wheel.setViewAdapter(new DateNumericAdapter(context, day_start, day_end, now_day-1));
		}else{//防止输入0
			day_wheel.setViewAdapter(new DateNumericAdapter(context, day_start, day_end, now_day));
		}
		
		day_wheel.setCurrentItem(now_day - day_start, false);
	}

	private OnWheelChangedListener yearChangedListener = new OnWheelChangedListener() {

		@Override
		public void onChanged(WheelView wheel, int oldValue, int newValue) {
			// TODO Auto-generated method stub
			now_year = year_start + wheel.getCurrentItem();
			year_wheel.setViewAdapter(new DateNumericAdapter(context, year_start, year_end, wheel.getCurrentItem()));
			setDayRain(now_year, now_month);
		}
	};

	private OnWheelClickedListener yearClickedListener = new OnWheelClickedListener() {

		@Override
		public void onItemClicked(WheelView wheel, int itemIndex) {
			// TODO Auto-generated method stub
			wheel.setCurrentItem(itemIndex, true);
		}
	};

	private OnWheelChangedListener monthChangedListener = new OnWheelChangedListener() {

		@Override
		public void onChanged(WheelView wheel, int oldValue, int newValue) {
			// TODO Auto-generated method stub
			now_month = month_start + wheel.getCurrentItem();
			month_wheel.setViewAdapter(new DateNumericAdapter(context, month_start, month_end, wheel.getCurrentItem()));
			setDayRain(now_year, now_month);
		}
	};

	private OnWheelClickedListener monthClickedListener = new OnWheelClickedListener() {

		@Override
		public void onItemClicked(WheelView wheel, int itemIndex) {
			// TODO Auto-generated method stub
			wheel.setCurrentItem(itemIndex, true);
		}
	};

	private OnWheelChangedListener dayChangedListener = new OnWheelChangedListener() {

		@Override
		public void onChanged(WheelView wheel, int oldValue, int newValue) {
			// TODO Auto-generated method stub
			now_day = day_start + wheel.getCurrentItem();
			day_wheel.setViewAdapter(new DateNumericAdapter(context, day_start, day_end, wheel.getCurrentItem()));
		}
	};

	private OnWheelClickedListener dayClickedListener = new OnWheelClickedListener() {

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
		public DateNumericAdapter(Context context, int minValue, int maxValue, int current) {
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
		case R.id.wheel_ok_bt:
			if (callback != null) {
				callback.getDate(now_year, now_month, now_day);
			}
			DateWheelDialog.this.dismiss();
			break;
		case R.id.wheel_cancel_bt:
			DateWheelDialog.this.dismiss();
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
