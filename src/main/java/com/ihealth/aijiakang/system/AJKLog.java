package com.ihealth.aijiakang.system;

import android.util.Log;

/**
 * @author
 * 统一管理log
 */
public class AJKLog {
	public static void i(String tag, String msg) {
		if (SwitchUtils.isDebug) {
			Log.i(tag, msg);
		}
	}

	public static void i(String tag, String msg, Throwable tr) {
		if (SwitchUtils.isDebug) {
			Log.i(tag, msg, tr);
		}
	}

	public static void e(String tag, String msg) {
		if (SwitchUtils.isDebug) {
			Log.e(tag, msg);
		}
	}

	public static void e(String tag, String msg, Throwable tr) {
		if (SwitchUtils.isDebug) {
			Log.e(tag, msg, tr);
		}
	}

	public static void d(String tag, String msg) {
		if (SwitchUtils.isDebug) {
			Log.d(tag, msg);
		}
	}

	public static void d(String tag, String msg, Throwable tr) {
		if (SwitchUtils.isDebug) {
			Log.d(tag, msg, tr);
		}
	}

	public static void v(String tag, String msg) {
		if (SwitchUtils.isDebug) {
			Log.v(tag, msg);
		}
	}
	
	public static void v(String tag, String msg,Throwable tr) {
		if (SwitchUtils.isDebug) {
			Log.v(tag, msg,tr);
		}
	}

	public static void w(String tag, String msg) {
		if (SwitchUtils.isDebug) {
			Log.w(tag, msg);
		}
	}
	
	public static void w(String tag, String msg,Throwable tr) {
		if (SwitchUtils.isDebug) {
			Log.w(tag, msg,tr);
		}
	}
}
