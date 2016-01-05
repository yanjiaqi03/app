package com.ihealth.aijiakang.widgets.dialog;

import android.content.Context;
import android.view.Window;
import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/24
 */
public class LoadingDialog extends BaseDialog{
    private Context context;
    public LoadingDialog(Context context) {
        super(context, R.layout.dialog_loading, false);
        this.context = context;
    }

    @Override
    protected void initviews(Window window) {

    }
}
