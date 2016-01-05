package com.ihealth.aijiakang.activity.comm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.ihealth.aijiakang.activity.BaseActivity;
import com.ihealth.aijiakang.imageloader.ImageLoaderTools;
import com.ihealth.aijiakang.logic.control.DownloadEventControl;
import com.ihealth.aijiakang.logic.LogicCallbackInterface;
import com.ihealth.aijiakang.logic.control.LoginControl;
import com.ihealth.aijiakang.sharedpreference.AppConfig;
import com.ihealth.aijiakang.sharedpreference.UserConfig;
import com.ihealth.aijiakang.system.AJKLog;
import com.ihealth.aijiakang.widgets.GuidePager;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/19
 */
public class WelcomeActivity extends BaseActivity {
    private final String TAG = "WelcomeActivity";
    /*XML Resource*/
    private ImageView welcomeWebImg;
    private GuidePager mGuidePager;

    @Override
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        setContentView(R.layout.activity_welcome);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initViews();
    }

    private void initViews() {
        welcomeWebImg = (ImageView) findViewById(R.id.welcome_web_img);
        welcomeWebImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mGuidePager = (GuidePager) findViewById(R.id.welcome_guideview);
        mGuidePager.setClickable(true);
        mGuidePager.addPagers(getSupportFragmentManager(), new int[]{R.mipmap.app_intro_0, R.mipmap.app_intro_1, R.mipmap.app_intro_2});
        mGuidePager.setGuideChangeListener(new GuidePager.GuideChangeListener() {
            @Override
            public void onSelected(int position) {
                AJKLog.i(TAG, "mGuidePager->position = " + position);
            }
        });
        runNetTask();
    }

    /**
     * 跳转Activity Runnable
     * Author YanJiaqi
     * Created at 15/11/19 下午4:50
     */

    private Runnable jumpRunnable = new Runnable() {
        @Override
        public void run() {
            if (isShowGuide()) {
                mGuidePager.setVisibility(View.VISIBLE);
            } else {
                final String currentUserName = UserConfig.getInstance().getConfigCurrentUser(WelcomeActivity.this);
                if(currentUserName.equals("")) {
                    jumpActivity(WelcomeActivity.this, LoginActivity.class);
                    WelcomeActivity.this.finish();
                }else if(!currentUserName.equals("Guest")) {
                    LoginControl.getInstance().autoLogin(WelcomeActivity.this, currentUserName, UserConfig.getInstance().getConfigPassword(WelcomeActivity.this), new LogicCallbackInterface() {
                        @Override
                        public void onSuccess(String result) {
                            jumpActivity(WelcomeActivity.this, HostActivity.class);
                            WelcomeActivity.this.finish();
                        }
                        @Override
                        public void onFailure(String errorNum, String content) {
                            AJKLog.i(TAG, "自动登录失败");
                            jumpActivity(WelcomeActivity.this, HostActivity.class);
                            WelcomeActivity.this.finish();
                        }
                    });
                }else {
                    jumpActivity(WelcomeActivity.this, HostActivity.class);
                    WelcomeActivity.this.finish();
                }
            }
        }
    };

    /**
     * 是否显示引导页
     * Author YanJiaqi
     * Created at 15/11/20 下午4:54
     */

    private boolean isShowGuide() {
//        String introductionValue = AppConfig.getInstance().getConfigIntroduction(WelcomeActivity.this);
//        if(introductionValue.equals("")) {
//            return true;
//        }
        return false;
    }

    /**
     * 执行网络任务
     * Author YanJiaqi
     * Created at 15/11/20 下午4:55
     */
    private void runNetTask(){
        //模拟网络请求(有网络执行网络，无网络延时)
        DownloadEventControl.getInstance().downloadAdvertisement(WelcomeActivity.this, "AJK", new LogicCallbackInterface() {
            @Override
            public void onSuccess(String result) {
                    ImageLoaderTools.getInstance().displayNetImg(result, welcomeWebImg);
                    if(AppConfig.getInstance().getConfigAdverImageLinkUrl(WelcomeActivity.this) != "") {
                        AJKLog.i(TAG, AppConfig.getInstance().getConfigAdverImageLinkUrl(WelcomeActivity.this));
                        welcomeWebImg.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Uri uri = Uri.parse(AppConfig.getInstance().getConfigAdverImageLinkUrl(WelcomeActivity.this));
                                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(it);
                            }
                        });
                    }

            }
            @Override
            public void onFailure(String errorNum, String content) {

            }
        });
        myHandler.postDelayed(jumpRunnable, 3000);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
