package com.ihealth.aijiakang.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

import com.ihealth.aijiakang.system.AJKLog;
import com.ihealth.aijiakang.utils.RegularUtils;
import com.ihealth.aijiakang.widgets.toast.HintToast;

import iHealth.AiJiaKang.MI.MyApplication;
import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/19
 */
public class FontEditText extends EditText {
    private final String TAG = "FontEditText";
    private Context context;

    public FontEditText(Context context) {
        super(context);
        this.context = context;
    }

    public FontEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FontEditText);
        String fontPath = a.getString(R.styleable.FontEditText_editFontPath);
        if(fontPath==null||fontPath.length()==0){
            setFont(context,MyApplication.DEFAULT_HYQH);
        }else{
            setFont(context,fontPath);
        }
        AJKLog.i(TAG,"fontPath -> " + fontPath);
        int weight = a.getInteger(R.styleable.FontEditText_editFontWeight,0);
        setWeight(weight);
        a.recycle();
    }

    private void setFont(Context context,String fontPath){
        MyApplication appContext = (MyApplication) context.getApplicationContext();
        this.setTypeface(appContext.getFont(fontPath));
    }

    private void setWeight(int weight){
        if(weight > 0){
            this.getPaint().setFakeBoldText(true);
        }else{
            this.getPaint().setFakeBoldText(false);
        }
    }

    /**
     * 验证昵称
     * Author YanJiaqi
     * Created at 15/12/11 上午10:53
     */

    public boolean checkName(String name){
        if(name.length()>0&&name.length()<=getResources().getInteger(R.integer.username_max_length)){
            return true;
        }

        return false;
    }

    /**
     * 验证昵称并含有Toast提示
     * Author YanJiaqi
     * Created at 15/12/11 上午10:53
     */

    public boolean checkNameWithToast(String name){
        boolean check = checkName(name);
        if(!check){
            HintToast.makeText(context, getResources().getString(R.string.check_username_error), HintToast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    /**
     * 验证手机号
     * Author YanJiaqi
     * Created at 15/12/11 上午10:50
     */

    public boolean checkPhone(String phone){
        if(!RegularUtils.getInstance().checkMobile(phone)){
            return false;
        }

        return true;
    }

    /**
     * 验证手机号并含有Toast提示
     * Author YanJiaqi
     * Created at 15/12/11 上午10:51
     */

    public boolean checkPhoneWithToast(String phone){
        boolean check = checkPhone(phone);
        if(!check){
            HintToast.makeText(context, getResources().getString(R.string.check_phone_error), HintToast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    /**
     * 校验验证码
     * Author YanJiaqi
     * Created at 15/12/11 上午10:56
     */

    public boolean checkVerifyCode(String code){
        if(code.length() > 0){
            return true;
        }

        return false;
    }

    /**
     * 校验验证码并含有Toast
     * Author YanJiaqi
     * Created at 15/12/11 上午10:56
     */

    public boolean checkVerifyCodeWithToast(String code){
        boolean check = checkVerifyCode(code);
        if(!check){
            HintToast.makeText(context, getResources().getString(R.string.verifycode_not_be_null), HintToast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    /**
     * 验证密码输入
     * Author YanJiaqi
     * Created at 15/12/11 上午10:46
     */

    public boolean checkPasswd(String passwd){
        if(passwd.length() < getResources().getInteger(R.integer.password_min_length)
                || passwd.length() > getResources().getInteger(R.integer.password_max_length)){
            return false;
        }

        return true;
    }

    /**
     * 验证密码输入并含Toast提示
     * Author YanJiaqi
     * Created at 15/12/11 上午10:47 
     */

    public boolean checkPasswdWithToast(String passwd){
        boolean check = checkPasswd(passwd);
        if(!check){
            HintToast.makeText(context, getResources().getString(R.string.change_passwd_new_error), HintToast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
