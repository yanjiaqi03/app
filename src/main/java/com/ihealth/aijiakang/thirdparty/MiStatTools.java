package com.ihealth.aijiakang.thirdparty;

import android.content.Context;

import com.ihealth.aijiakang.system.SwitchUtils;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import com.xiaomi.mistatistic.sdk.URLStatsRecorder;

/**
 * Created by YanJiaqi on 15/12/3
 */
public class MiStatTools {
    private static MiStatTools mInstance = null;

    public static MiStatTools getInstance(){
        if(mInstance == null){
            mInstance = new MiStatTools();
        }

        return mInstance;
    }

    /**
     * 初始化（Application中）
     * Author YanJiaqi
     * Created at 15/12/3 下午1:24
     */

    public void init(Context context){
        if(SwitchUtils.isMiState){
            MiStatInterface.initialize(context, ThirdAppInfo.XM_APP_ID+"", ThirdAppInfo.XM_APP_KEY,"default channel");
            // 实时上报
            MiStatInterface.setUploadPolicy(MiStatInterface.UPLOAD_POLICY_REALTIME, 0);
            // 小米崩溃统计
            MiStatInterface.enableExceptionCatcher(true);
            // 小米网络监控
            URLStatsRecorder.enableAutoRecord();
        }
    }

    /**
     * 页面统计开始
     * Author YanJiaqi
     * Created at 15/12/3 下午1:24
     */

    public void recordStart(Context context,String key){
        if(SwitchUtils.isMiState){
            MiStatInterface.recordPageStart(context, key);
        }
    }

    /**
     * 页面统计结束
     * Author YanJiaqi
     * Created at 15/12/3 下午1:26
     */

    public void recordEnd(Context context){
        if(SwitchUtils.isMiState){
            MiStatInterface.recordPageEnd();
        }
    }

    /**
     * 统计触发事件
     * Author YanJiaqi
     * Created at 15/12/3 下午1:28
     */

    public void recordEvent(String group,String key){
        if(SwitchUtils.isMiState){
            MiStatInterface.recordCountEvent(group, key);
        }
    }
}
