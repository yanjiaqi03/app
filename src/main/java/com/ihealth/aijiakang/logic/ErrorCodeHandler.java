package com.ihealth.aijiakang.logic;

import android.content.Context;

import com.ihealth.aijiakang.widgets.toast.HintToast;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by lanbaoshi on 15/11/25.
 */
public class ErrorCodeHandler {

    private static ErrorCodeHandler mInstance;
    private ErrorCodeHandler() {}
    public static ErrorCodeHandler getInstance() {
        if(mInstance == null) {
            mInstance = new ErrorCodeHandler();
        }
        return mInstance;
    }

    public synchronized void handleLoginErrorCode(Context context, String errorCode) {
        switch (Integer.parseInt(errorCode)) {
            case 204 :
                HintToast.makeText(context,
                        context.getResources().getString(R.string.user_login_signin_toast_204), HintToast.LENGTH_SHORT).show();
                break;
            case 205 :
                HintToast.makeText(context,
                        context.getResources().getString(R.string.user_login_signin_toast_205), HintToast.LENGTH_SHORT).show();
                break;
            case 206 :
                HintToast.makeText(context,
                        context.getResources().getString(R.string.user_login_signin_toast_206), HintToast.LENGTH_SHORT).show();
                break;
            case 207 :
                HintToast.makeText(context,
                        context.getResources().getString(R.string.user_login_signin_toast_207), HintToast.LENGTH_SHORT).show();
                break;
            case 209 :
                HintToast.makeText(context,
                        context.getResources().getString(R.string.user_login_signin_toast_209), HintToast.LENGTH_SHORT).show();
                break;
        }

    }

    public synchronized void handleRegisterErrorCode(Context context, String errorCode) {
        switch (Integer.parseInt(errorCode)) {
            case 201 :
                HintToast.makeText(context,
                        context.getResources().getString(R.string.user_register_signin_toast_201), HintToast.LENGTH_SHORT).show();
                break;
            case 260 :
                HintToast.makeText(context,
                        context.getResources().getString(R.string.user_register_signin_toast_260), HintToast.LENGTH_SHORT).show();
                break;
            case 259 :
                HintToast.makeText(context,
                        context.getResources().getString(R.string.user_register_signin_toast_259), HintToast.LENGTH_SHORT).show();
                break;

        }
    }
}
