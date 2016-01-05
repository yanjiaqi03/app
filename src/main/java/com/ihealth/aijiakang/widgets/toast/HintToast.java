package com.ihealth.aijiakang.widgets.toast;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by YanJiaqi on 15/11/23
 */
public class HintToast extends Toast{
    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public HintToast(Context context) {
        super(context);
    }
}
