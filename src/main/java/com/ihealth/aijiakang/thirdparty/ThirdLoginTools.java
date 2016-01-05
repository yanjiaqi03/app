package com.ihealth.aijiakang.thirdparty;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.ihealth.aijiakang.system.AJKLog;
import com.xiaomi.account.openauth.XMAuthericationException;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.account.openauth.XiaomiOAuthFuture;
import com.xiaomi.account.openauth.XiaomiOAuthResults;
import com.xiaomi.account.openauth.XiaomiOAuthorize;

import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;

public class ThirdLoginTools {
	private final String TAG = "ThirdLoginTools";
	private static ThirdLoginTools mInstance = null;
	private final String SHARESDK_SUCCESS = "ShareSdk_Success";
	private final String SHARESDK_FAILED = "ShareSdk_Failed";
	private final String SHARESDK_CANCEL = "ShareSdk_Cancel";

	public static ThirdLoginTools getInstance() {
		if (mInstance == null) {
			mInstance = new ThirdLoginTools();
		}

		return mInstance;
	}

	public interface ThirdLoginListener {
		void cancel();

		void success(ThirdLoginData data);

		void error(String error);
	}
	
	/**
	 * 小米第三方登录
	 * 
	 * @author YanJiaqi
	 * @param mListener
	 */
	public void MIThirdLogin(Activity context,ThirdLoginListener mListener) {
		if(mListener == null){
			return;
		}
		XiaomiOAuthFuture<XiaomiOAuthResults> future = new XiaomiOAuthorize().setAppId(ThirdAppInfo.XM_APP_ID)
				.setRedirectUrl(ThirdAppInfo.XM_REDIRECT_URI)
				.setScope(new int[] { XiaomiOAuthConstants.SCOPE_PROFILE, XiaomiOAuthConstants.SCOPE_RELATION,
						XiaomiOAuthConstants.SCOPE_OPEN_ID, XiaomiOAuthConstants.SCOPE_PHONE })
				.startGetAccessToken(context);
		new getMIAccessToken(context,future, mListener).execute();
	}

	/**
	 * 获取小米登录AccessToken
	 * 
	 * @author yanjiaqi
	 */
	private class getMIAccessToken extends AsyncTask<Void, Void, String> {
		private XiaomiOAuthFuture<XiaomiOAuthResults> future;
		private ThirdLoginListener mListener;
		private String doneResult = "";
		private ThirdLoginData data = null;
		private Context context = null;

		public getMIAccessToken(Context context,XiaomiOAuthFuture<XiaomiOAuthResults> future, ThirdLoginListener mListener) {
			this.future = future;
			this.mListener = mListener;
			this.context = context;
		}

		@Override
		protected String doInBackground(Void... params) {
			try {
				XiaomiOAuthResults result = future.getResult();
				if (result.hasError()) {
					@SuppressWarnings("unused")
					int errorCode = result.getErrorCode();
					doneResult = result.getErrorMessage();
				} else {
					String accessToken = result.getAccessToken();
					String macKey = result.getMacKey();
					String macAlgorithm = result.getMacAlgorithm();

					data = getMIProfile(context, accessToken, macKey, macAlgorithm);
					if(data!=null && data.getUserId().length()>0){
						doneResult = "success";
					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
				doneResult = e1.toString();
			} catch (OperationCanceledException e1) {
				e1.printStackTrace();
				doneResult = "cancel";
			} catch (XMAuthericationException e1) {
				e1.printStackTrace();
				doneResult = e1.toString();
			} catch (JSONException e1) {
				e1.printStackTrace();
				doneResult = e1.toString();
			}
			return doneResult;
		}

		@Override
		protected void onPostExecute(String result) {
			if (mListener != null) {
				if (result.equals("success")) {
					mListener.success(data);
				} else if (result.equals("cancel")) {
					mListener.cancel();
				} else {
					mListener.error(result);
				}
			}
		}
	}

	/**
	 * 获取小米登录的名片及手机号码
	 * 
	 * @author yanjiaqi
	 * @param context
	 * @param accessToken
	 * @param macKey
	 * @param macAlgorithm
	 * @throws JSONException 
	 */
	private ThirdLoginData getMIProfile(Context context, String accessToken, String macKey, String macAlgorithm)
			throws IOException, OperationCanceledException, XMAuthericationException, JSONException {
		ThirdLoginData data = new ThirdLoginData();
		if(context == null){
			return data;
		}
		//获取用户名片
		XiaomiOAuthFuture<String> future = new XiaomiOAuthorize().callOpenApi(context, ThirdAppInfo.XM_APP_ID,
				XiaomiOAuthConstants.OPEN_API_PATH_PROFILE, accessToken, macKey, macAlgorithm);
		String result = future.getResult();
		AJKLog.i(TAG, "result -> " + result);
		JSONTokener jsonTParser = new JSONTokener(result);
		JSONObject jsonORegist = (JSONObject) jsonTParser.nextValue();
		result = jsonORegist.getString("data");
		jsonTParser = new JSONTokener(result);
		jsonORegist = (JSONObject) jsonTParser.nextValue();
		long mid = jsonORegist.getLong("userId");
		String nickName = jsonORegist.getString("miliaoNick");
		String iconUrl = jsonORegist.getString("miliaoIcon");
		data.setUserId(mid+"");
		data.setNickName(nickName);
		data.setIcon(iconUrl);
		//获取用户绑定电话
		future = new XiaomiOAuthorize().callOpenApi(context, ThirdAppInfo.XM_APP_ID,
				XiaomiOAuthConstants.OPEN_API_PATH_PHONE, accessToken, macKey, macAlgorithm);
		result = future.getResult();
		AJKLog.i(TAG, "result -> " + result);
		jsonTParser = new JSONTokener(result);
		jsonORegist = (JSONObject) jsonTParser.nextValue();
		result = jsonORegist.getString("data");
		jsonTParser = new JSONTokener(result);
		jsonORegist = (JSONObject) jsonTParser.nextValue();
		String phoneNum = jsonORegist.getString("phone");
		data.setPhone(phoneNum);
		
		return data;
	}

	/**
	 * 微信第三方登录
	 * @author YanJiaqi
	 */
	public void WechatThirdLogin(final ThirdLoginListener mListener,final Handler mHandler){
		if(mListener == null){
			return;
		}
		PlatformActionListener mPlatformListener = new PlatformActionListener() {
			
			@Override
			public void onError(Platform arg0, int arg1, final Throwable arg2) {
				AJKLog.i(TAG, "authorize error->" + arg2.toString());
				if(mHandler!=null){
					mHandler.post(new Runnable() {
						@Override
						public void run() {
							mListener.error(arg2.toString());
						}
					});
				}
			}
			
			@Override
			public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
				AJKLog.i(TAG, "authorize success");
				PlatformActionListener mPlatformListener = new PlatformActionListener() {

					@Override
					public void onCancel(Platform arg0, int arg1) {
						AJKLog.i(TAG, "authorize cancel");
						if(mHandler!=null){
							mHandler.post(new Runnable() {
								@Override
								public void run() {
									mListener.cancel();
								}
							});
						}
					}

					@Override
					public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
						AJKLog.i(TAG, "msg = " + arg2.toString());
						final ThirdLoginData mThirdLoginData = new ThirdLoginData();
						mThirdLoginData.setUserId(arg2.get("unionid") + "");
						mThirdLoginData.setNickName(arg2.get("nickname") + "");
						mThirdLoginData.setIcon(arg2.get("headimgurl") + "");
						mThirdLoginData.setSex(arg2.get("sex") + "");

						if(mHandler!=null){
							mHandler.post(new Runnable() {
								@Override
								public void run() {
									mListener.success(mThirdLoginData);
								}
							});
						}
					}

					@Override
					public void onError(Platform arg0, int arg1, final Throwable arg2) {
						AJKLog.i(TAG, "authorize error->" + arg2.toString());
						if(mHandler!=null){
							mHandler.post(new Runnable() {
								@Override
								public void run() {
									mListener.error(arg2.toString());
								}
							});
						}
					}
				};
				
				Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
				wechat.setPlatformActionListener(mPlatformListener); 
				wechat.showUser(null);
			}
			
			@Override
			public void onCancel(Platform arg0, int arg1) {
				AJKLog.i(TAG, "authorize cancel");
				if(mHandler!=null){
					mHandler.post(new Runnable() {
						@Override
						public void run() {
							mListener.cancel();
						}
					});
				}
			}
		};
		Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
		wechat.SSOSetting(false);  //设置false表示使用SSO授权方式
		wechat.setPlatformActionListener(mPlatformListener); 
		wechat.authorize();
	}
}