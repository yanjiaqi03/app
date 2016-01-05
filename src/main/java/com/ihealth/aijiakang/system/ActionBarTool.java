package com.ihealth.aijiakang.system;

import android.view.Window;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by YanJiaqi on 15/11/23
 */
public class ActionBarTool {
    private static ActionBarTool mInstance = null;

    public static ActionBarTool getInstance() {
        if (mInstance == null) {
            mInstance = new ActionBarTool();
        }

        return mInstance;
    }

    /**
     * 显示沉浸式通知栏并且设置为深色字体
     * 只可用于MIUI6及以上，其他手机可能没有效果，但不会崩溃，
     * Author YanJiaqi
     * Created at 15/11/23 下午4:06
     */

    public void invokeImmerseActionBar(Window window) {
        try {
            Class clazz = window.getClass();
            int tranceFlag = 0;
            int darkModeFlag = 0;
            Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_TRANSPARENT");
            tranceFlag = field.getInt(layoutParams);
            field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(window, tranceFlag, tranceFlag); //只需要状态栏透明
            extraFlagField.invoke(window, tranceFlag | darkModeFlag, tranceFlag | darkModeFlag);//状态栏透明且黑色字体
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
