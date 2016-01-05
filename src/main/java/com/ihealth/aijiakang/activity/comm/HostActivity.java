package com.ihealth.aijiakang.activity.comm;

import com.ihealth.aijiakang.activity.BaseActivity;
import com.ihealth.aijiakang.fragment.DiscoverFragment;
import com.ihealth.aijiakang.fragment.MeFragment;
import com.ihealth.aijiakang.fragment.MeasureFragment;
import com.ihealth.aijiakang.fragment.ResultsFragment;
import com.ihealth.aijiakang.logic.control.FamilyMemberControl;
import com.ihealth.aijiakang.logic.LogicCallbackInterface;
import com.ihealth.aijiakang.sharedpreference.PushConfig;
import com.ihealth.aijiakang.sharedpreference.UserConfig;
import com.ihealth.aijiakang.system.AJKLog;
import com.ihealth.aijiakang.utils.VariableUtils;
import com.ihealth.aijiakang.widgets.SwitchImageView;
import com.ihealth.aijiakang.widgets.SwitchTextView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;

import iHealth.AiJiaKang.MI.R;

public class HostActivity extends BaseActivity implements OnClickListener {
    private final String TAG = "HostActivity";
    public static final int HOST_MEASURE = 0;
    public static final int HOST_RESULTS = 1;
    public static final int HOST_DISCOVER = 2;
    public static final int HOST_ME = 3;
    private int currentPos = -1;
    private MeasureFragment mMeasureFragment;
    private ResultsFragment mResultsFragment;
    private DiscoverFragment mDiscoverFragment;
    private MeFragment mMeFragment;
    private BroadcastReceiver mBroadcastReceiver;
    public static final String NEW_INTENT_ACTION = "onNewIntentAction";
    public static final int GO_TO_TEST = 100;

    /*XML Resource*/
    /*Bottom Options*/
    private LinearLayout tabMeasure;
    private SwitchImageView tabMeasureIv;
    private SwitchTextView tabMeasureTv;
    private LinearLayout tabResults;
    private SwitchImageView tabResultsIv;
    private SwitchTextView tabResultsTv;
    private LinearLayout tabDiscover;
    private SwitchImageView tabDiscoverIv;
    private SwitchTextView tabDiscoverTv;
    private LinearLayout tabMe;
    private SwitchImageView tabMeIv;
    private SwitchTextView tabMeTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_center);

        myFragmentManager = getSupportFragmentManager();
        initBottom();
		/* the main fragment enter */
        selectPos(HOST_RESULTS);

        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if(action.equals(VariableUtils.HOST_ME_UPDATE_USER)){
                    if(mMeFragment!=null && mMeFragment.getActivity()!=null){
                        mMeFragment.setUserInfo();
                    }
                }
            }
        };
        IntentFilter mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(VariableUtils.HOST_ME_UPDATE_USER);
        registerReceiver(mBroadcastReceiver, mIntentFilter);
        PushConfig.getInstance().doPushIntent(this);

        FamilyMemberControl.getInstance().downloadFamilyMembersUserInfo(HostActivity.this,
                UserConfig.getInstance().getConfigCurrentUser(HostActivity.this), new LogicCallbackInterface() {
            @Override
            public void onSuccess(String result) {
                AJKLog.i(TAG, "Download Family Member Success");
            }

            @Override
            public void onFailure(String errorNum, String content) {
                AJKLog.i(TAG, "Download Family Member fail");
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        AJKLog.i(TAG,"OnNewIntent");
        if(intent!=null&&intent.getExtras()!=null){
            int action = intent.getExtras().getInt(NEW_INTENT_ACTION,0);
            switch (action){
                case GO_TO_TEST:
                    selectPos(HOST_MEASURE);
                    break;
                default:
                    break;
            }
        }

        PushConfig.getInstance().doPushIntent(this);
    }

    /**
     * 初始化下方四个Item
     * @author YanJiaqi
     */
    private void initBottom() {
        tabMeasure = (LinearLayout) findViewById(R.id.host_bottom_measure);
        tabMeasure.setOnClickListener(this);
        tabMeasureIv = (SwitchImageView) findViewById(R.id.host_bottom_measure_iv);
        tabMeasureTv = (SwitchTextView) findViewById(R.id.host_bottom_measure_tv);

        tabResults = (LinearLayout) findViewById(R.id.host_bottom_results);
        tabResults.setOnClickListener(this);
        tabResultsIv = (SwitchImageView) findViewById(R.id.host_bottom_results_iv);
        tabResultsTv = (SwitchTextView) findViewById(R.id.host_bottom_results_tv);

        tabDiscover = (LinearLayout) findViewById(R.id.host_bottom_discover);
        tabDiscover.setOnClickListener(this);
        tabDiscoverIv = (SwitchImageView) findViewById(R.id.host_bottom_discover_iv);
        tabDiscoverTv = (SwitchTextView) findViewById(R.id.host_bottom_discover_tv);

        tabMe = (LinearLayout) findViewById(R.id.host_bottom_me);
        tabMe.setOnClickListener(this);
        tabMeIv = (SwitchImageView) findViewById(R.id.host_bottom_me_iv);
        tabMeTv = (SwitchTextView) findViewById(R.id.host_bottom_me_tv);
    }

    public void setItemState(int position){
        switch (position){
            case 0:
                tabMeasureIv.setSwitchState(SwitchImageView.ON_STATE);
                tabMeasureTv.setSwitchState(SwitchTextView.ON_STATE);
                tabResultsIv.setSwitchState(SwitchImageView.OFF_STATE);
                tabResultsTv.setSwitchState(SwitchTextView.OFF_STATE);
                tabDiscoverIv.setSwitchState(SwitchImageView.OFF_STATE);
                tabDiscoverTv.setSwitchState(SwitchTextView.OFF_STATE);
                tabMeIv.setSwitchState(SwitchImageView.OFF_STATE);
                tabMeTv.setSwitchState(SwitchTextView.OFF_STATE);
                break;
            case 1:
                tabResultsIv.setSwitchState(SwitchImageView.ON_STATE);
                tabResultsTv.setSwitchState(SwitchTextView.ON_STATE);
                tabMeasureIv.setSwitchState(SwitchImageView.OFF_STATE);
                tabMeasureTv.setSwitchState(SwitchTextView.OFF_STATE);
                tabDiscoverIv.setSwitchState(SwitchImageView.OFF_STATE);
                tabDiscoverTv.setSwitchState(SwitchTextView.OFF_STATE);
                tabMeIv.setSwitchState(SwitchImageView.OFF_STATE);
                tabMeTv.setSwitchState(SwitchTextView.OFF_STATE);
                break;
            case 2:
                tabDiscoverIv.setSwitchState(SwitchImageView.ON_STATE);
                tabDiscoverTv.setSwitchState(SwitchTextView.ON_STATE);
                tabMeasureIv.setSwitchState(SwitchImageView.OFF_STATE);
                tabMeasureTv.setSwitchState(SwitchTextView.OFF_STATE);
                tabResultsIv.setSwitchState(SwitchImageView.OFF_STATE);
                tabResultsTv.setSwitchState(SwitchTextView.OFF_STATE);
                tabMeIv.setSwitchState(SwitchImageView.OFF_STATE);
                tabMeTv.setSwitchState(SwitchTextView.OFF_STATE);
                break;
            case 3:
                tabMeIv.setSwitchState(SwitchImageView.ON_STATE);
                tabMeTv.setSwitchState(SwitchTextView.ON_STATE);
                tabMeasureIv.setSwitchState(SwitchImageView.OFF_STATE);
                tabMeasureTv.setSwitchState(SwitchTextView.OFF_STATE);
                tabResultsIv.setSwitchState(SwitchImageView.OFF_STATE);
                tabResultsTv.setSwitchState(SwitchTextView.OFF_STATE);
                tabDiscoverIv.setSwitchState(SwitchImageView.OFF_STATE);
                tabDiscoverTv.setSwitchState(SwitchTextView.OFF_STATE);
                break;
        }
    }


    /**
     * 选择跳到第几栏
     * Author YanJiaqi
     * Created at 15/11/18 下午10:19
     */

    public void selectPos(int position) {
        switch (position) {
            case 0:
                if (getSelectPos() == 0) {
                    break;
                }
                currentPos = 0;
                if (mMeasureFragment == null) {
                    mMeasureFragment = new MeasureFragment();
                }
                changeFragment(getmFragmentContent(), mMeasureFragment);
                setmFragmentContent(mMeasureFragment);
                setItemState(0);
                break;
            case 1:
                if (getSelectPos() == 1) {
                    break;
                }
                currentPos = 1;
                if (mResultsFragment == null) {
                    mResultsFragment = new ResultsFragment();
                }
                changeFragment(getmFragmentContent(), mResultsFragment);
                setmFragmentContent(mResultsFragment);
                setItemState(1);
                break;
            case 2:
                if (getSelectPos() == 2) {
                    break;
                }
                currentPos = 2;
                if (mDiscoverFragment == null) {
                    mDiscoverFragment = new DiscoverFragment();
                }
                changeFragment(getmFragmentContent(), mDiscoverFragment);
                setmFragmentContent(mDiscoverFragment);
                setItemState(2);
                break;
            case 3:
                if (getSelectPos() == 3) {
                    break;
                }
                currentPos = 3;
                if (mMeFragment == null) {
                    mMeFragment = new MeFragment();
                }
                changeFragment(getmFragmentContent(), mMeFragment);
                setmFragmentContent(mMeFragment);
                setItemState(3);
                break;
            default:
                break;
        }
    }

    /**
     * 获取当前在第几栏
     * Author YanJiaqi
     * Created at 15/11/18 下午10:18
     */

    public int getSelectPos() {
        return currentPos;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.host_bottom_measure:
                selectPos(0);
                break;
            case R.id.host_bottom_results:
                selectPos(1);
                break;
            case R.id.host_bottom_discover:
                selectPos(2);
                break;
            case R.id.host_bottom_me:
                selectPos(3);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            //记录显示选择家人
            if(mResultsFragment != null && mResultsFragment.isDisplaySelectFamily()){
                mResultsFragment.hideSelectLayout();
                return true;
            }

            exitApp();

            return true;
        }

        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AJKLog.d(TAG,"HostActiviy onDestroy");
        //注销广播
        try {
            unregisterReceiver(mBroadcastReceiver);
        } catch (Exception e){
            e.printStackTrace();
        }

//        removeFragment(mMeasureFragment);
//        removeFragment(mResultsFragment);
//        removeFragment(mDiscoverFragment);
//        removeFragment(mMeFragment);

    }
}
