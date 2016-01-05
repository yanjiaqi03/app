package com.ihealth.aijiakang.logic.control;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by lanbaoshi on 15/12/3.
 */
public class BasicControl {
    public Handler myHandler = null;

    public BasicControl() {
        myHandler = new Handler(Looper.getMainLooper());
    }
}
