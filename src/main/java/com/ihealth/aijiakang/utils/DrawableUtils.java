package com.ihealth.aijiakang.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.ihealth.aijiakang.imageloader.CircleDrawable;
import com.ihealth.aijiakang.system.AJKLog;

public class DrawableUtils {
	private final String TAG = "DrawableUtils";
	private static DrawableUtils mInstance = null;

	public static DrawableUtils getInstance() {
		if (mInstance == null) {
			mInstance = new DrawableUtils();
		}

		return mInstance;
	}
	/**
	 * 获取RGB565更节约内存的Drawable
	 * 
	 * @author YanJiaqi
	 * @param context
	 * @param path
	 * @return
	 */
	public BitmapDrawable getRGB565Drawable(Context context, String path){
		if (context == null) {
			return null;
		}
		
		return new BitmapDrawable(context.getResources(),BitMapUtils.getInstance().getRGB565Bmp(path));
	}
	/**
	 * 获取RGB565更节约内存的Drawable
	 * 
	 * @author YanJiaqi
	 * @param context
	 * @return
	 */
	public BitmapDrawable getRGB565Drawable(Context context, int resid){
		if (context == null) {
			return null;
		}
		
		return new BitmapDrawable(context.getResources(),BitMapUtils.getInstance().getRGB565Bmp(context,resid));
	}
	/**
	 * 根据路径获取drawable() opt传null 为 argb8888
	 * 
	 * @author YanJiaqi
	 * @param context
	 * @param path
	 * @param opt
	 * @return
	 */
	public Drawable getDrawable(Context context, String path, BitmapFactory.Options opt) {
		if (context == null) {
			return null;
		}
		return new BitmapDrawable(context.getResources(), BitMapUtils.getInstance().getBitmap(path, opt));
	}

	/**
	 * 根据drawableId获取drawable() opt传null 为 argb8888
	 * 
	 * @author YanJiaqi
	 * @param context
	 * @param resid
	 * @param opt
	 * @return
	 */
	public Drawable getDrawable(Context context, int resid, BitmapFactory.Options opt) {
		if (context == null) {
			return null;
		}
		if(opt == null){
			return context.getResources().getDrawable(resid);
		}
		return new BitmapDrawable(context.getResources(), BitMapUtils.getInstance().getBitmap(context, resid, opt));
	}
	
	/**
	 * drawable转bitmap
	 * 
	 * @author YanJiaqi
	 * @param drawable
	 * @return
	 */
	public Bitmap drawableToBitamp(Drawable drawable) {
		BitmapDrawable bd = (BitmapDrawable) drawable;
		return bd.getBitmap();
	}

	/**
	 * 获取圆形drawable
	 * Author YanJiaqi
	 * Created at 15/11/27 下午3:48
	 */

	public Drawable getCircleDrawable(Bitmap bitmap){
		return new CircleDrawable(bitmap);
	}

	/**
	 * 清空drawable
	 * Author YanJiaqi
	 * Created at 15/12/2 下午4:12
	 */

	public void clearDrawable(Drawable drawable){
//		AJKLog.i(TAG,"clearDrawable");
		if(drawable!=null && drawable instanceof BitmapDrawable){
			Bitmap bmp = ((BitmapDrawable)drawable).getBitmap();
			if (bmp != null && !bmp.isRecycled()){
				bmp.recycle();
				bmp = null;
			}
		}
	}
}
