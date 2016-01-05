package com.ihealth.aijiakang.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.ihealth.aijiakang.system.AJKLog;
import com.ihealth.aijiakang.system.ActionBarTool;
import com.ihealth.aijiakang.widgets.dialog.LoadingDialog;
import com.ihealth.aijiakang.widgets.toast.HintToast;

import java.util.Iterator;
import java.util.Map;

import iHealth.AiJiaKang.MI.MyApplication;
import iHealth.AiJiaKang.MI.R;

public abstract class BaseActivity extends FragmentActivity {
    /**
     * Application
     *
     * @author xiaohuai
     **/
    private final String TAG = "BaseActivity";
    protected FragmentManager myFragmentManager = null;
    protected MyApplication myApplication;
    public Handler myHandler = null;
    protected LoadingDialog mLoadingBar;
    private long clickTime = 0;

    public Fragment getmFragmentContent() {
        return mFragmentContent;
    }

    public void setmFragmentContent(Fragment mFragmentContent) {
        this.mFragmentContent = mFragmentContent;
    }

    private Fragment mFragmentContent = null;

    @Override
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置通知栏变透明，并且显示dark字体
        ActionBarTool.getInstance().invokeImmerseActionBar(getWindow());
        myApplication = (MyApplication) getApplicationContext();
        myApplication.getActivityMap().put(getClass().getName(), this);
        myHandler = new Handler();
        mLoadingBar = new LoadingDialog(this);

        System.gc();
    }

    public void changeFragment(Fragment from, Fragment to) {
        FragmentTransaction ft = myFragmentManager.beginTransaction()
//				.addToBackStack(null)  // can back to from
//				.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out) //frament switch animation
                ;
        if (from == null) {
            ft.replace(R.id.center_fragment, to).commit();
        } else {
            if (!to.isAdded()) {
                ft.hide(from).add(R.id.center_fragment, to).commit();
            } else {
                ft.hide(from).show(to).commit();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myApplication.getActivityMap().remove(getClass().getName());
        if (myHandler != null) {
            myHandler.removeCallbacksAndMessages(null);
            myHandler = null;
        }
    }

    /**
     * Activity 跳转(没有finish)
     * Author YanJiaqi
     * Created at 15/11/19 下午4:48
     */

    public void jumpActivity(Context context, Class<?> cla) {
        Intent intent = new Intent(context, cla);
        context.startActivity(intent);
    }

    /**
     * Activity 跳转(没有finish)
     * Author YanJiaqi
     * Created at 15/11/19 下午4:48
     */

    public void jumpActivity(Context context, Class<?> cla,int flags,Bundle extras) {
        Intent intent = new Intent(context, cla);
        intent.setFlags(flags);
        intent.putExtras(extras);
        context.startActivity(intent);
    }

    /**
     * Activity 跳转(没有finish)
     * Author YanJiaqi
     * Created at 15/11/19 下午4:48
     */

    public void jumpActivity(Context context, Class<?> cla, Bundle extras) {
        Intent intent = new Intent(context, cla);
        intent.putExtras(extras);
        context.startActivity(intent);
    }

    /**
     * 点击非编辑框区域隐藏软键盘
     * Author YanJiaqi
     * Created at 15/11/19 下午5:20
     */

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    v.clearFocus();
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 判断是否点击其他区域关闭软键盘
     * Author YanJiaqi
     * Created at 15/11/19 下午5:21
     */

    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            // 获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

    public void showLoadingBar(boolean isLoading) {
        if (mLoadingBar != null) {
            if (isLoading) {
                mLoadingBar.show();
            } else {
                mLoadingBar.dismiss();
            }
        }
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    /**
     * 返回事件
     * Author YanJiaqi
     * Created at 15/11/28 下午7:22
     */

    public void goBack() {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            goBack();
            return true;
        }

        return false;
    }

    /**
     * 再次点击退出程序
     * Author YanJiaqi
     * Created at 15/11/28 下午7:56
     */

    protected void exitApp() {
        if ((System.currentTimeMillis() - clickTime) > 2000) {
            HintToast.makeText(this, getResources().getString(R.string.click_again_exist),
                    HintToast.LENGTH_SHORT).show();
            clickTime = System.currentTimeMillis();
        } else {
            clearAllActivity();
        }
    }

    /**
     * 清楚所有的Actvity
     * Author YanJiaqi
     * Created at 15/11/28 下午8:53
     */

    protected void clearAllActivity(){
        Iterator iter = myApplication.getActivityMap().entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            AJKLog.i(TAG, key);
            myApplication.getActivityMap().get(key).finish();
        }
    }

    /**
     * 移除fragment
     * Author YanJiaqi
     * Created at 15/12/2 下午4:53
     */

    protected void removeFragment(Fragment fragment){
        if(fragment!=null&&fragment.isAdded()){
            getSupportFragmentManager().beginTransaction().remove(fragment);
        }
    }
}
