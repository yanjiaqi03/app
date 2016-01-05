package com.ihealth.aijiakang.system;

import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import net.tsz.afinal.utils.Utils;

import java.util.List;

/**
 * Created by YanJiaqi on 15/11/27
 */
public class SystemTool {
    private final String TAG = "SystemTool";
    private static SystemTool mInstance = null;

    public static final int APP_NONETASK = 2;
    public static final int APP_BACKSTAGE = 1;
    public static final int APP_FRONTSTAGE = 0;

    public static SystemTool getInstance() {
        if (mInstance == null) {
            mInstance = new SystemTool();
        }

        return mInstance;
    }

    /**
     * 复制
     * Author YanJiaqi
     * Created at 15/11/27 上午11:45
     */

    public void copy(Context context, String content) {
        if(context == null)
            return;
        // 得到剪贴板管理器
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData textCd = ClipData.newPlainText("", content);
        clipboard.setPrimaryClip(textCd);
    }

    /**
     * 跳转至拨号界面
     * Author YanJiaqi
     * Created at 15/11/27 上午11:52
     */

    public void jumpToPhone(Context context, String phone) {
        if(context == null)
            return;
        Uri uri = Uri.parse("tel:" + phone);
        Intent it = new Intent(Intent.ACTION_DIAL, uri);
        context.startActivity(it);
    }

    /**
     * 获取版本名称
     * Author YanJiaqi
     * Created at 15/11/27 上午11:55
     */

    public String getVersionName(Context context) {
        String version = "";
        if(context == null)
            return version;
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            version = info.versionName;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return version;
    }

    /**
     * 查看应用是否存在
     * Author YanJiaqi
     * Created at 15/11/28 下午6:57
     */

    public boolean checkAppExist( Context context, String packageName )
    {
        if(context == null)
            return false;
        PackageManager packageManager = context.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        for ( int i = 0; i < pinfo.size(); i++ )
        {
            if(pinfo.get(i).packageName.equalsIgnoreCase(packageName))
                return true;
        }
        return false;
    }

    /**
     * 获取应用包名
     * Author YanJiaqi
     * Created at 15/12/3 下午5:53
     */

    public String getPackageName(Context context){
        String packageName = "";
        if(context == null)
            return packageName;
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            packageName = info.packageName;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return packageName;
    }

    /**
     * 判断App在前台 后台 没有运行
     * Author YanJiaqi
     * Created at 15/12/3 下午5:59
     */

    public int isTopActivity(Context context) {
        if(context == null)
            return -1;
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(10);
        String packageName = getPackageName(context);
        AJKLog.i(TAG,"packageName->" + packageName);
        String topActivityPackageName = tasksInfo.get(0).topActivity.getPackageName();
        if (packageName.equals(topActivityPackageName)) {
            return APP_FRONTSTAGE;
        } else {
            for (int i = 1; i < tasksInfo.size(); i++) {
                if (packageName.equals(tasksInfo.get(i).topActivity.getPackageName())) {
                    return APP_BACKSTAGE;
                }
            }
        }
        return APP_NONETASK;
    }

    /**
     *
     * Author YanJiaqi
     * Created at 15/12/7 下午8:57
     */

    public boolean isActivityExist(Context context,String activityClassName){
        Intent intent = new Intent();
        intent.setClassName(getPackageName(context),activityClassName);
        if(intent.resolveActivity(context.getPackageManager()) == null) {
            return false;
        }else{
            return true;
        }
    }
}
