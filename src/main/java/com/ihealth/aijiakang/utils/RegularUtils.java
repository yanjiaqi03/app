package com.ihealth.aijiakang.utils;

import java.util.regex.Pattern;

/**
 * Created by YanJiaqi on 15/11/20
 */
public class RegularUtils {
    private static RegularUtils mInstance = null;

    public static RegularUtils getInstance(){
        if(mInstance == null){
            mInstance = new RegularUtils();
        }
        return mInstance;
    }

    /**
     * 手机号正则判断
     * Author YanJiaqi
     * Created at 15/11/20 下午2:38
     */

    public boolean checkMobile(String mobile) {
        if(mobile.length()==11){
            return Pattern.matches("(\\+\\d+)?1[34578]\\d{9}$", mobile);
        }else{
            return false;
        }
    }

    /**
     * 将13502044642 -> 135****4642
     * Author YanJiaqi
     * Created at 15/12/2 上午1:42
     */

    public String changePhoneToX(String phone){
        if(!checkMobile(phone)){
            return phone;
        }
        StringBuilder sb = new StringBuilder(phone);
        sb.replace(3, 7, "****");
        return sb.toString();
    }
}
