package com.ihealth.aijiakang.widgets.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import iHealth.AiJiaKang.MI.R;

public abstract class BaseDialog extends Dialog{
	private int layoutId;
	private int gravity = -1;
	private int animId = -1;
	
	
	public BaseDialog(Context context,int layoutId) {
		super(context,R.style.daily_activity_dialog);
		
		this.layoutId = layoutId;
	}
	
	public BaseDialog(Context context,int layoutId,Boolean cancelable) {
		super(context,R.style.daily_activity_dialog);
		
		this.layoutId = layoutId;
		this.setCancelable(cancelable);
	}
	
	public BaseDialog(Context context,int layoutId,Boolean cancelable,int messGravity) {
		super(context,R.style.daily_activity_dialog);
		
		this.layoutId = layoutId;
		this.setCancelable(cancelable);
	}
	
	public BaseDialog(Context context,int layoutId,Boolean cancelable,int gravity,int animId) {
		super(context,R.style.daily_activity_dialog);
		
		this.layoutId = layoutId;
		this.setCancelable(cancelable);
		this.gravity = gravity;
		this.animId = animId;
	}
	
	protected abstract void initviews(Window window);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Window window = this.getWindow();
		window.setContentView(layoutId);
		if(gravity!=-1){
			window.setGravity(gravity);
		}else{
			window.setGravity(Gravity.CENTER);
		}
		
		initviews(window);
		
		if(animId!=-1){
			 window.setWindowAnimations(animId);
		}
	}
}
