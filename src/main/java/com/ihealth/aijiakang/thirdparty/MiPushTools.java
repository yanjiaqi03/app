package com.ihealth.aijiakang.thirdparty;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;

import com.ihealth.aijiakang.sharedpreference.UserConfig;
import com.ihealth.aijiakang.system.AJKLog;
import com.ihealth.aijiakang.utils.VariableUtils;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;


/**
 * Created by YanJiaqi on 15/12/3
 */
public class MiPushTools {
    private final String TAG = "MiPushTools";
    private static MiPushTools mInstance = null;

    public static MiPushTools getInstance(){
        if(mInstance == null){
            mInstance = new MiPushTools();
        }

        return mInstance;
    }

    public void init(Context context){
        if(context == null)
            return;
        String currentUser = UserConfig.getInstance().getConfigCurrentUser(context);
        if(currentUser.equals("") || currentUser.equals(UserConfig.USER_GUEST)) {
            unRegistPush(context);
            return;
        }
        if (shouldInit(context)) {
            MiPushClient.registerPush(context, ThirdAppInfo.XM_APP_ID + "", ThirdAppInfo.XM_APP_KEY);
        }
        //打开Log
        LoggerInterface newLogger = new LoggerInterface() {

            @Override
            public void setTag(String tag) {
                // ignore
            }
            @Override
            public void log(String content, Throwable t) {
                AJKLog.d(TAG, content, t);
            }

            @Override
            public void log(String content) {
                AJKLog.d(TAG, content);
            }
        };
        Logger.setLogger(context, newLogger);
    }

    /**
     * 设置UserAccount推送模式
     * Author YanJiaqi
     * Created at 15/12/3 下午4:33
     */

    public void setUserAccount(Context context,String userAccount){
        if(context == null)
            return;
        MiPushClient.setUserAccount(context, userAccount, "");
    }

    /**
     * 设置当前UserAccount的推送模式
     * Author YanJiaqi
     * Created at 15/12/3 下午4:40
     */

    public void setCurrentUserPush(Context context){
        if(context == null)
            return;
        String currentUser = UserConfig.getInstance().getConfigCurrentUser(context);
        setUserAccount(context, currentUser);
    }

    /**
     * 注销推送
     * Author YanJiaqi
     * Created at 15/12/3 下午4:40
     */

    public void unRegistPush(Context context){
        if(context == null)
            return;
        MiPushClient.unregisterPush(context);
    }

    /**
     * 清除该应用的通知栏消息
     * Author YanJiaqi
     * Created at 15/12/7 下午9:50
     */

    public void clearNotifications(Context context){
        if(context == null)
            return;
        MiPushClient.clearNotification(context);
    }

    private boolean shouldInit(Context context) {
        if(context == null)
            return false;
        ActivityManager am = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = context.getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }
}
