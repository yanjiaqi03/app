package com.ihealth.aijiakang.logic.control;

import android.content.Context;

import com.ihealth.aijiakang.logic.LogicCallbackInterface;
import com.ihealth.aijiakang.sharedpreference.AppConfig;
import com.ihealth.aijiakang.system.AJKLog;
import com.ihealth.ihealthcloudlibrary.cloudInterface.AJKCloudMethod;
import com.ihealth.ihealthcloudlibrary.cloudInterface.CloudCallback;
import com.ihealth.ihealthcloudlibrary.model.output.OutputModelActInfo;

import java.util.ArrayList;

/**
 * Created by lanbaoshi on 15/11/27.
 */
public class DownloadEventControl {
    private final String TAG = "DownloadEventControl";
    private static DownloadEventControl mInstance;
    private LogicCallbackInterface mCallback;

    private DownloadEventControl() {

    }

    public static DownloadEventControl getInstance() {
        if(mInstance == null) {
            mInstance = new DownloadEventControl();
        }
        return mInstance;
    }

    public void downloadAdvertisement(final Context context, String appName, LogicCallbackInterface callback) {
        if(context == null) {
            return;
        }
        this.mCallback = callback;
        AJKCloudMethod.getInstance().downloadActInfo(context, appName, new CloudCallback<OutputModelActInfo>() {
            @Override
            public void onSuccess(ArrayList<OutputModelActInfo> arrayList) {
                if(context == null) {
                    return;
                }
                for(int i=0; i<arrayList.size(); i++) {
                    AJKLog.i(TAG, "下载活动成功");
                    AJKLog.i(TAG, "TS = " + arrayList.get(i).getEndTimeTS());
                    AppConfig.getInstance().setConfigValueAdverEndTime(context, arrayList.get(i).getEndTimeTS());
                    AppConfig.getInstance().setConfigAdverImageUrl(context, arrayList.get(i).getImgUrl());
                    AppConfig.getInstance().setConfigAdverImageLinkUrl(context, arrayList.get(i).getImgLinkUrl());
                }
                long endTime = AppConfig.getInstance().getConfigAdverEndTime(context);
                AJKLog.i(TAG, "SystemTime = " + System.currentTimeMillis());
                if(endTime > (System.currentTimeMillis() / 1000)) {
                    mCallback.onSuccess(AppConfig.getInstance().getConfigAdverImageUrl(context));
                }else {
                    mCallback.onSuccess("");
                }
            }
            @Override
            public void onFailure(String backNum, String str) {
                if(context == null) {
                    return;
                }
                AJKLog.i(TAG, "下载活动失败");
                if(AppConfig.getInstance().getConfigAdverEndTime(context) > (System.currentTimeMillis() / 1000)) {
                    mCallback.onSuccess(AppConfig.getInstance().getConfigAdverImageUrl(context));
                }else {
                    mCallback.onFailure(backNum, str);
                }
            }
        });
    }
}
