package com.ihealth.aijiakang.utils;

import android.content.Context;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/12/11
 */
public class TimerUtils {
    private static TimerUtils mInstance = null;
    /** 验证码部分 **/
    private Timer codeTimer = null;
    private int codeTimerCount = 0;
    private CodeTimerInterface codeListener = null;
    /**************/


    public synchronized static TimerUtils getInstance(){
        if(mInstance == null){
            mInstance = new TimerUtils();
        }

        return mInstance;
    }

    /**
     * 开始执行验证码计时
     * Author YanJiaqi
     * Created at 15/12/11 上午11:07
     */

    public void startCodeTimer(final Context context, final Handler mHandler,CodeTimerInterface mListener){
        codeTimerCount = context.getResources().getInteger(R.integer.verifycode_timer_count);
        codeListener = mListener;
        if(codeListener!=null){
            codeListener.onPreTimer();
        }
        if(codeTimer == null){
            codeTimer = new Timer();
        }
        codeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (context == null) {
                    cancelCodeTimer();
                    return;
                }
                if (mHandler != null) {
                    mHandler.post(updateGetCodeUI);
                }
            }
        }, 0, 1000);
    }

    /**
     * 更新UI
     * Author YanJiaqi
     * Created at 15/12/11 上午11:20
     */

    private Runnable updateGetCodeUI = new Runnable() {
        @Override
        public void run() {
            codeTimerCount --;
            if(codeTimerCount<0){
                cancelCodeTimer();
                if(codeListener!=null){
                    codeListener.onOverTimer();
                }
                return;
            }

            codeListener.onUpdateTimer(codeTimerCount);
        }
    };

    /**
     * 停止验证码计时
     * Author YanJiaqi
     * Created at 15/12/11 上午11:07
     */

    public void cancelCodeTimer(){
        if(codeTimer!=null){
            codeTimer.cancel();
            codeTimer = null;
        }
    }

    public interface CodeTimerInterface{
        void onPreTimer();
        void onUpdateTimer(int timerCount);
        void onOverTimer();
    }

}
