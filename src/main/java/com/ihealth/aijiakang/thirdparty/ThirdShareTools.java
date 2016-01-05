package com.ihealth.aijiakang.thirdparty;

import java.io.File;
import java.util.HashMap;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.ihealth.aijiakang.system.SystemTool;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.system.email.Email;
import cn.sharesdk.system.text.ShortMessage;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import iHealth.AiJiaKang.MI.R;

public class ThirdShareTools {
	private static ThirdShareTools mInstance = null;
	public static ThirdShareTools getInstance(){
		if(mInstance == null){
			mInstance = new ThirdShareTools();
		}
		
		return mInstance;
	}

	/**
	 * 微信分享图片
	 * @author YanJiaqi
	 * @param imagePath
	 */
	public void WechatSendImageByShareSdk(final Context context,String imagePath){
		PlatformActionListener mPlatformActionListener = new PlatformActionListener() {

			@Override
			public void onError(Platform arg0, int arg1, Throwable arg2) {
				Toast.makeText(context,R.string.ssdk_oks_share_failed, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
				Toast.makeText(context,R.string.ssdk_oks_share_completed, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onCancel(Platform arg0, int arg1) {
				Toast.makeText(context,R.string.ssdk_oks_share_canceled, Toast.LENGTH_SHORT).show();
			}
		};
		ShareParams sp = new ShareParams();
		sp.setShareType(Platform.SHARE_IMAGE);
		sp.setImagePath(imagePath);
		Platform mWechat = ShareSDK.getPlatform(Wechat.NAME);
		mWechat.setPlatformActionListener(mPlatformActionListener);
		mWechat.share(sp);
	}
	
	/**
	 * 微信分享web
	 * @author YanJiaqi
	 * @param title
	 * @param content
	 * @param imageUrl
	 * @param webUrl
	 */
	public void WechatSendWebUrlByShareSdk(final Context context,String title,String content,String imageUrl,String webUrl){
		PlatformActionListener mPlatformActionListener = new PlatformActionListener() {

			@Override
			public void onError(Platform arg0, int arg1, Throwable arg2) {
				Toast.makeText(context,R.string.ssdk_oks_share_failed, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
				Toast.makeText(context,R.string.ssdk_oks_share_completed, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onCancel(Platform arg0, int arg1) {
				Toast.makeText(context,R.string.ssdk_oks_share_canceled, Toast.LENGTH_SHORT).show();
			}
		};
		ShareParams sp = new ShareParams();
		sp.setShareType(Platform.SHARE_WEBPAGE);
		sp.setTitle(title);
		sp.setText(content);
		sp.setImageUrl(imageUrl);
		sp.setUrl(webUrl);
		Platform mWechat = ShareSDK.getPlatform(Wechat.NAME);
		mWechat.setPlatformActionListener(mPlatformActionListener);
		mWechat.share(sp);
	}
	
	/**
	 * 微信朋友圈分享图片
	 * @author YanJiaqi
	 * @param imagePath
	 */
	public void WechatMomentsSendImageByShareSdk(final Context context,String imagePath){
		PlatformActionListener mPlatformActionListener = new PlatformActionListener() {

			@Override
			public void onError(Platform arg0, int arg1, Throwable arg2) {
				Toast.makeText(context,R.string.ssdk_oks_share_failed, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
				Toast.makeText(context,R.string.ssdk_oks_share_completed, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onCancel(Platform arg0, int arg1) {
				Toast.makeText(context,R.string.ssdk_oks_share_canceled, Toast.LENGTH_SHORT).show();
			}
		};
		ShareParams sp = new ShareParams();
		sp.setShareType(Platform.SHARE_IMAGE);
		sp.setImagePath(imagePath);
		Platform mWechatMoments = ShareSDK.getPlatform(WechatMoments.NAME);
		mWechatMoments.setPlatformActionListener(mPlatformActionListener);
		mWechatMoments.share(sp);
	}
	
	/**
	 * 微信朋友圈分享web
	 * @author YanJiaqi
	 * @param title
	 * @param content
	 * @param imageUrl
	 * @param webUrl
	 */
	public void WechatMomentsSendWebUrlByShareSdk(final Context context,String title,String content,String imageUrl,String webUrl){
		PlatformActionListener mPlatformActionListener = new PlatformActionListener() {

			@Override
			public void onError(Platform arg0, int arg1, Throwable arg2) {
				Toast.makeText(context,R.string.ssdk_oks_share_failed, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
				Toast.makeText(context,R.string.ssdk_oks_share_completed, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onCancel(Platform arg0, int arg1) {
				Toast.makeText(context,R.string.ssdk_oks_share_canceled, Toast.LENGTH_SHORT).show();
			}
		};
		ShareParams sp = new ShareParams();
		sp.setShareType(Platform.SHARE_WEBPAGE);
		sp.setTitle(title);
		sp.setText(content);
		sp.setImageUrl(imageUrl);
		sp.setUrl(webUrl);
		Platform mWechatMoments = ShareSDK.getPlatform(WechatMoments.NAME);
		mWechatMoments.setPlatformActionListener(mPlatformActionListener);
		mWechatMoments.share(sp);
	}
	
	/**
	 * 短信分享文本
	 * @author YanJiaqi
	 * @param address
	 * @param content
	 */
	public void MessageShareByShareSdk(String address,String content){
		ShareParams sp = new ShareParams();
		sp.setAddress(address);
		sp.setText(content);
		Platform mShortMessage = ShareSDK.getPlatform(ShortMessage.NAME);
		mShortMessage.share(sp);
	}
	
	/**
	 * 邮件分享图文
	 * @author YanJiaqi
	 * @param address
	 * @param title
	 * @param content
	 * @param imagePath
	 */
	public void EmailShareByShareSdk(String address,String title,String content,String imagePath){
		ShareParams sp = new ShareParams();
		sp.setAddress(address);
		sp.setTitle(title);
		sp.setText(content);
		sp.setImagePath(imagePath);
		Platform mShortMessage = ShareSDK.getPlatform(Email.NAME);
		mShortMessage.share(sp);
	}
	
	/**
	 * 系统分享文件
	 * @author YanJiaqi
	 * @param path
	 */
	public void fileShareBySystem(Context context,String path) {
		if (context == null) {
			return;
		}
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("*/*");
		File file = new File(path);
		if(file==null||!file.exists()){
			return;
		}
		Uri fileUri = Uri.fromFile(file);
		intent.putExtra(Intent.EXTRA_STREAM, fileUri);
		Intent chooserIntent = Intent.createChooser(intent, context.getResources().getString(R.string.ssdk_oks_share_to));
		if (chooserIntent == null) {
			Toast.makeText(context, context.getResources().getString(R.string.ssdk_oks_share_failed), Toast.LENGTH_SHORT).show();
			return;
		}
		context.startActivity(chooserIntent);
	}
	
	/**
	 * 系统分享文件
	 * @author YanJiaqi
	 * @param uri
	 */
	public void fileShareBySystem(Context context,Uri uri) {
		if (context == null) {
			return;
		}
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("*/*");
		intent.putExtra(Intent.EXTRA_STREAM, uri);
		Intent chooserIntent = Intent.createChooser(intent, context.getResources().getString(R.string.ssdk_oks_share_to));
		if (chooserIntent == null) {
			Toast.makeText(context, context.getResources().getString(R.string.ssdk_oks_share_failed), Toast.LENGTH_SHORT).show();
			return;
		}
		context.startActivity(chooserIntent);
	}
	
	/**
	 * 系统分享图片
	 * @author YanJiaqi
	 * @param path
	 */
	public void imgShareBySystem(Context context,String path){
		if (context == null) {
			return;
		}
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("image/jpeg");
		File file = new File(path);
		if(file==null||!file.exists()){
			return;
		}
		Uri fileUri = Uri.fromFile(file);
		intent.putExtra(Intent.EXTRA_STREAM, fileUri);
		Intent chooserIntent = Intent.createChooser(intent, context.getResources().getString(R.string.ssdk_oks_share_to));
		if (chooserIntent == null) {
			Toast.makeText(context, context.getResources().getString(R.string.ssdk_oks_share_failed), Toast.LENGTH_SHORT).show();
			return;
		}
		context.startActivity(chooserIntent);
	}
	
	/**
	 * 指定平台使用系统分享图片
	 * @author YanJiaqi
	 * @param packageName
	 * @param mainEntryName
	 * @param path
	 */
	public void imgShareBySystem(Context context,String packageName,String mainEntryName,String path){
		if (context == null) {
			return;
		}
		if(!SystemTool.getInstance().checkAppExist(context,packageName)){
			Toast.makeText(context, context.getString(R.string.ssdk_oks_share_no_app), Toast.LENGTH_SHORT).show();
			return;
		}
		Intent targeted = new Intent(Intent.ACTION_SEND);
		targeted.setType("image/jpeg");
		File file = new File(path);
		if(file==null||!file.exists()){
			return;
		}
		Uri fileUri = Uri.fromFile(file);
		targeted.putExtra(Intent.EXTRA_STREAM, fileUri);
		ComponentName componentName = new ComponentName(packageName, mainEntryName);
		targeted.setComponent(componentName);
		context.startActivity(targeted);
	}
}
