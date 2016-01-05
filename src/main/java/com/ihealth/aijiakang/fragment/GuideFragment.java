package com.ihealth.aijiakang.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.ihealth.aijiakang.utils.DrawableUtils;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/20
 */
public class GuideFragment extends BaseFragment {
    private int imgResource;
    private ImageView guideImg;

    public GuideFragment(){
        this(0);
    }
    @SuppressLint("ValidFragment")
    public GuideFragment(int imgResource){
        super(R.layout.fragment_guide);
        this.imgResource = imgResource;
    }
    @Override
    public void mViewCreated(Context context, View view) {
        guideImg = (ImageView) view.findViewById(R.id.guide_fragment_iv);
        guideImg.setImageDrawable(DrawableUtils.getInstance().getRGB565Drawable(getActivity(),imgResource));
    }

    @Override
    public void mOnDestroyView(Context context) {

    }

    @Override
    public void mOnShow() {

    }

    @Override
    public void mOnHidden() {

    }
}
