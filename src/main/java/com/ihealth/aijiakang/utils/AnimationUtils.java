package com.ihealth.aijiakang.utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.view.View;

/**
 * Created by YanJiaqi on 15/11/19
 */
public class AnimationUtils {
    private static AnimationUtils mInstance = null;
    public static AnimationUtils getInstance(){
        if(mInstance == null){
            mInstance = new AnimationUtils();
        }

        return mInstance;
    }

    public void runAlphaAnim(View view,float from,float to,long duration,TimeInterpolator interpolator){
        ObjectAnimator objAlpha = ObjectAnimator.ofFloat(view,"alpha",from,to);
        objAlpha.setDuration(duration);
        objAlpha.setInterpolator(interpolator);
        objAlpha.start();
    }

    public void runRotateAnim(View view,float from,float to,long duration,TimeInterpolator interpolator){
        ObjectAnimator objRotate = ObjectAnimator.ofFloat(view,"rotation",from,to);
        objRotate.setDuration(duration);
        objRotate.setInterpolator(interpolator);
        objRotate.start();
    }

    public void runTranslationXAnim(View view,float from,float to,long duration,TimeInterpolator interpolator){
        ObjectAnimator objTrans = ObjectAnimator.ofFloat(view,"translationX",from,to);
        objTrans.setDuration(duration);
        objTrans.setInterpolator(interpolator);
        objTrans.start();
    }

    public void runTranslationXAnim(View view,float from,float to,long duration,TimeInterpolator interpolator,Animator.AnimatorListener listener){
        ObjectAnimator objTrans = ObjectAnimator.ofFloat(view,"translationX",from,to);
        objTrans.setDuration(duration);
        objTrans.setInterpolator(interpolator);
        objTrans.start();
        objTrans.addListener(listener);
    }

    public void runTranslationYAnim(View view,float from,float to,long duration,TimeInterpolator interpolator){
        ObjectAnimator objTrans = ObjectAnimator.ofFloat(view,"translationY",from,to);
        objTrans.setDuration(duration);
        objTrans.setInterpolator(interpolator);
        objTrans.start();
    }

    public void runTranslationYAnim(View view,float from,float to,long duration,TimeInterpolator interpolator,Animator.AnimatorListener listener){
        ObjectAnimator objTrans = ObjectAnimator.ofFloat(view,"translationY",from,to);
        objTrans.setDuration(duration);
        objTrans.setInterpolator(interpolator);
        objTrans.start();
        objTrans.addListener(listener);
    }
}
