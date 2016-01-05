package com.ihealth.aijiakang.widgets;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.ihealth.aijiakang.fragment.GuideFragment;

import java.util.ArrayList;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/20
 */
public class GuidePager extends FrameLayout {
    private final String TAG = "GuidePager";
    private ViewPager mViewPager = null;
    private GuideChangeListener mGuideChangeListener = null;

    public GuidePager(Context context) {
        super(context);
    }

    public interface GuideChangeListener {
        void onSelected(int position);
    }

    public void setGuideChangeListener(GuideChangeListener mGuideChangeListener) {
        this.mGuideChangeListener = mGuideChangeListener;
    }

    public GuidePager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPager(context);
    }

    private void initPager(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_guidepager, null);
        mViewPager = (ViewPager) view.findViewById(R.id.guide_viewpager);
        mViewPager.setOnPageChangeListener(mOnPageChangeListener);
        addView(view, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (mGuideChangeListener != null) {
                mGuideChangeListener.onSelected(position);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /**
     * 向ViewPager添加资源(一张图为一页)
     * Author YanJiaqi
     * Created at 15/11/20 下午4:00
     */

    public void addPagers(FragmentManager manager,int[] imgResource) {
        if (mViewPager == null || imgResource == null || imgResource.length == 0) {
            return;
        }
        ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
        for (int i = 0; i < imgResource.length; i++) {
            fragmentList.add(new GuideFragment(imgResource[i]));
        }
        GuideAdapter mGuideAdapter = new GuideAdapter(manager, fragmentList);
        mViewPager.setAdapter(mGuideAdapter);
        mViewPager.setCurrentItem(0);
    }

    class GuideAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> list;

        public GuideAdapter(FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

    public ViewPager getmViewPager(){
        return this.mViewPager;
    }
}
