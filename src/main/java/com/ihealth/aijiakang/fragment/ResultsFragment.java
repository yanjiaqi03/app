package com.ihealth.aijiakang.fragment;

import android.animation.Animator;
import android.content.Context;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ihealth.aijiakang.activity.bp.ResultListActivity;
import com.ihealth.aijiakang.activity.comm.HostActivity;
import com.ihealth.aijiakang.adapter.HostResultsGridAdapter;
import com.ihealth.aijiakang.adapter.datasource.SelectPersonAdapterData;
import com.ihealth.aijiakang.sharedpreference.ResultsConfig;
import com.ihealth.aijiakang.sharedpreference.UserConfig;
import com.ihealth.aijiakang.system.AJKLog;
import com.ihealth.aijiakang.utils.AnimationUtils;
import com.ihealth.aijiakang.utils.ClickStateUtils;
import com.ihealth.aijiakang.utils.ViewUtils;
import com.ihealth.aijiakang.widgets.CustomFrameLayout;
import com.ihealth.aijiakang.widgets.FontTextView;
import com.ihealth.aijiakang.widgets.ResultsCoordinate;
import com.ihealth.aijiakang.widgets.SizeChangedListener;
import com.ihealth.aijiakang.widgets.datasource.ResultsCoordinateData;
import com.ihealth.aijiakang.widgets.datasource.ResultsCoordinateItem;

import net.tsz.afinal.core.AsyncTask;

import java.util.ArrayList;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/18.
 */
public class ResultsFragment extends BaseFragment {
    private final String TAG = "ResultsFragment";
    private final String DEFAULT_TIME = "- - / - - / - -   - - : - -";
    private final String DEFAULT_VALUE = "- -";
    private final int animTime = 300;
    private String name = "";
    private ResultsCoordinateData bpData;
    private ArrayList<ArrayList<ResultsCoordinateItem>> bpValuesList = null;
    private ResultsCoordinateData pulseData;
    private ArrayList<ArrayList<ResultsCoordinateItem>> pulseValuesList = null;
    private boolean isDisplaySelectFamily = false;
    private int gridColums = 0;
    private HostResultsGridAdapter gridAdapter;
    private ArrayList<SelectPersonAdapterData> trunkSelectData = new ArrayList<SelectPersonAdapterData>();
    private ArrayList<SelectPersonAdapterData> tempSelectData = new ArrayList<SelectPersonAdapterData>();

    /**
     * XML Resource
     **/
    private LinearLayout titleLayout;
    private FontTextView titleTv;
    private ImageView titleArrow;
    private FontTextView lastTime;
    private FontTextView lastSys, lastDia, lastPulse;
    private FontTextView lastSysUnit, lastDiaUnit;
    private ResultsCoordinate bpCoordinate;
    private ResultsCoordinate pulseCoordinate;
    private CustomFrameLayout selectFamilyLayout;
    private GridView selectFamilyGrid;
    private View selectFamilyLayoutBg;


    public ResultsFragment() {
        super(R.layout.fragment_host_results);
    }

    @Override
    public void mViewCreated(Context context, View view) {
        titleTv = (FontTextView) view.findViewById(R.id.host_results_title_tv);
        titleTv.setText("");

        titleArrow = (ImageView) view.findViewById(R.id.host_results_title_arrow);

        titleLayout = (LinearLayout) view.findViewById(R.id.host_results_title_layout);
        titleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleLayout.setClickable(false);

                if (isDisplaySelectFamily) {
                    hideSelectLayout();
                } else {
                    new readFamily(selectFamilyLayout.getHeight()).execute();
                }
            }
        });

        lastTime = (FontTextView) view.findViewById(R.id.host_results_last_measure_time);
        lastTime.setText(DEFAULT_TIME);
        lastSys = (FontTextView) view.findViewById(R.id.host_results_lastcontent_sys_value);
        lastSys.setText(DEFAULT_VALUE);
        lastDia = (FontTextView) view.findViewById(R.id.host_results_lastcontent_dia_value);
        lastDia.setText(DEFAULT_VALUE);
        lastPulse = (FontTextView) view.findViewById(R.id.host_results_lastcontent_pulse_value);
        lastPulse.setText(DEFAULT_VALUE);
        lastSysUnit = (FontTextView) view.findViewById(R.id.host_results_lastcontent_sys_unit);
        lastSysUnit.setText(getResources().getString(R.string.mmHg));
        lastDiaUnit = (FontTextView) view.findViewById(R.id.host_results_lastcontent_dia_unit);
        lastDiaUnit.setText(getResources().getString(R.string.mmHg));
        //血压坐标系
        bpCoordinate = (ResultsCoordinate) view.findViewById(R.id.host_results_bp_coordinate);
        ClickStateUtils bpCoorClick = new ClickStateUtils();
        bpCoorClick.setAlphaClick(bpCoordinate,1f);
        bpCoordinate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HostActivity)getActivity()).jumpActivity(getActivity(), ResultListActivity.class);
            }
        });
        //心率坐标系
        pulseCoordinate = (ResultsCoordinate) view.findViewById(R.id.host_results_pulse_coordinate);
        ClickStateUtils pulseCoorClick = new ClickStateUtils();
        pulseCoorClick.setAlphaClick(pulseCoordinate, 1f);
        pulseCoordinate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HostActivity)getActivity()).jumpActivity(getActivity(), ResultListActivity.class);
            }
        });

        selectFamilyLayout = (CustomFrameLayout) view.findViewById(R.id.host_results_selectfamily_layout);
        selectFamilyLayout.setVisibility(View.INVISIBLE);
        selectFamilyLayout.setSizeChangedListener(new SizeChangedListener() {
            @Override
            public void onSizeChanged(int w, int h, int oldw, int oldh) {
                ViewUtils.getInstance().autoHeightAbsListView(gridAdapter, selectFamilyGrid, gridColums, selectFamilyLayout.getHeight());
            }
        });
        selectFamilyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSelectLayout();
            }
        });

        selectFamilyLayoutBg = view.findViewById(R.id.host_results_selectfamily_layout_bg);

        selectFamilyGrid = (GridView) view.findViewById(R.id.host_results_selectfamily_gridview);
        gridAdapter = new HostResultsGridAdapter(getActivity(), trunkSelectData);
        selectFamilyGrid.setAdapter(gridAdapter);
        selectFamilyGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AJKLog.i(TAG,"select item -> " + position);
                selectPerson(position);
            }
        });

        new readData().execute();
    }

    /**
     * 显示选择身份UI更新
     * Author YanJiaqi
     * Created at 15/12/10 下午5:13
     */

    private void showSelectLayout() {
        int totalHeight = ViewUtils.getInstance().getAbsListViewHeight(gridAdapter,selectFamilyGrid,gridColums);
        selectFamilyGrid.setTranslationY(-totalHeight);
        selectFamilyLayout.setVisibility(View.VISIBLE);
        AnimationUtils.getInstance().runTranslationYAnim(selectFamilyGrid, -totalHeight, 0f, animTime, new DecelerateInterpolator(), selectFamilyAnimListener);
        AnimationUtils.getInstance().runRotateAnim(titleArrow, titleArrow.getRotation(), 180f, animTime, new DecelerateInterpolator());
        AnimationUtils.getInstance().runAlphaAnim(selectFamilyLayoutBg,0f,1f,animTime,new LinearInterpolator());
    }

    /**
     * 隐藏选择身份UI更新
     * Author YanJiaqi
     * Created at 15/12/10 下午5:12
     */

    public void hideSelectLayout() {
        AnimationUtils.getInstance().runTranslationYAnim(selectFamilyGrid, 0f, -selectFamilyGrid.getHeight(), animTime, new DecelerateInterpolator(), selectFamilyAnimListener);
        AnimationUtils.getInstance().runRotateAnim(titleArrow, titleArrow.getRotation(), 0f, animTime, new DecelerateInterpolator());
        AnimationUtils.getInstance().runAlphaAnim(selectFamilyLayoutBg, 1f, 0f, animTime, new LinearInterpolator());
    }

    private final Animator.AnimatorListener selectFamilyAnimListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            isDisplaySelectFamily = !isDisplaySelectFamily;
            if(!isDisplaySelectFamily){
                selectFamilyLayout.setVisibility(View.INVISIBLE);
            }
            titleLayout.setClickable(true);
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };
    /**
     * 选择身份UI更新
     * Author YanJiaqi
     * Created at 15/12/10 下午4:59
     */

    private void selectPerson(int position) {
        for (int i = 0; i < trunkSelectData.size(); i++) {
            if (position == i) {
                trunkSelectData.get(i).setSelected(true);
                continue;
            }

            trunkSelectData.get(i).setSelected(false);
        }

        ResultsConfig.getInstance().setConfigResultsSelectWho(getActivity(),trunkSelectData.get(position).getUserId());

        gridAdapter.notifyDataSetChanged();
    }

    /**
     * 读取家庭成员
     * Author YanJiaqi
     * Created at 15/12/10 下午2:26
     */

    private class readFamily extends AsyncTask<Void, Void, Void> {
        private int maxHeight = 0;

        public readFamily(int maxHeight) {
            this.maxHeight = maxHeight;
        }

        @Override
        protected Void doInBackground(Void... params) {
            tempSelectData.clear();
            int selectUserId = ResultsConfig.getInstance().getConfigResultsSelectWho(getActivity());
            int currentUserId = UserConfig.getInstance().getConfigUserID(getActivity());
            int userid[] = new int[]{currentUserId,11,22,33,44};
            for (int i = 0; i < userid.length; i++) {
                if(userid[i] == selectUserId){
                    tempSelectData.add(new SelectPersonAdapterData(userid[i], "", "小坏" + i,true));
                    continue;
                }
                tempSelectData.add(new SelectPersonAdapterData(userid[i], "", "小坏" + i,false));
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (getActivity() == null) {
                return;
            }
            if (gridAdapter != null) {
                trunkSelectData.clear();
                trunkSelectData.addAll(tempSelectData);

                gridColums = 3;
                if (trunkSelectData.size() < 3 || trunkSelectData.size() == 4) {
                    gridColums = 2;
                }
                selectFamilyGrid.setNumColumns(gridColums);

                gridAdapter.notifyDataSetChanged();

                ViewUtils.getInstance().autoHeightAbsListView(gridAdapter, selectFamilyGrid, gridColums, maxHeight);

                showSelectLayout();
            }
        }
    }

    /**
     * 读取本地血压相关数据 (不在AsyncTask中读云)
     * Author YanJiaqi
     * Created at 15/12/9 下午9:01
     */

    private class readData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            //读用户
            name = "小坏";
            //高低压数据封装
            boolean fill = false;
            bpValuesList = new ArrayList<ArrayList<ResultsCoordinateItem>>();
            ArrayList<ResultsCoordinateItem> sysList = new ArrayList<ResultsCoordinateItem>();
            ArrayList<String> bpDates = new ArrayList<String>();
            int sysColor = getResources().getColor(R.color.sys_linecolor);
            for (int i = 0; i < 9; i++) {
                fill = !fill;
                bpDates.add("11." + (i + 3));
                sysList.add(new ResultsCoordinateItem(110 + (int) (Math.random() * 50), fill, sysColor));
            }

            ArrayList<ResultsCoordinateItem> diaList = new ArrayList<ResultsCoordinateItem>();
            int diaColor = getResources().getColor(R.color.dia_linecolor);
            fill = false;
            for (int i = 0; i < 9; i++) {
                fill = !fill;
                diaList.add(new ResultsCoordinateItem(60 + (int) (Math.random() * 50), fill, diaColor));
            }


            bpValuesList.add(sysList);
            bpValuesList.add(diaList);
            bpData = new ResultsCoordinateData(true, bpValuesList, bpDates);

            //心率数据封装
            pulseValuesList = new ArrayList<ArrayList<ResultsCoordinateItem>>();
            ArrayList<ResultsCoordinateItem> pulseList = new ArrayList<ResultsCoordinateItem>();
            ArrayList<String> pulseDates = new ArrayList<String>();
            int pulseColor = getResources().getColor(R.color.pul_linecolor);
            fill = false;
            for (int i = 0; i < 9; i++) {
                fill = !fill;
                pulseDates.add("11." + (i + 3));
                pulseList.add(new ResultsCoordinateItem(60 + (int) (Math.random() * 50), fill, pulseColor));
            }
            pulseValuesList.add(pulseList);
            pulseData = new ResultsCoordinateData(false, pulseValuesList, pulseDates);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (getActivity() == null) {
                return;
            }
            titleTv.setText(name + getResources().getString(R.string.host_result_title));
            bpCoordinate.notifyChangedData(bpData);
            pulseCoordinate.notifyChangedData(pulseData);
        }
    }

    @Override
    public void mOnDestroyView(Context context) {

    }

    @Override
    public void mOnShow() {
        AJKLog.d(TAG, TAG + "-> mOnShow");
    }

    @Override
    public void mOnHidden() {
        AJKLog.d(TAG, TAG + "-> mOnHidden");
        hideSelectLayout();
    }

    public boolean isDisplaySelectFamily() {
        return isDisplaySelectFamily;
    }

    public void setIsDisplaySelectFamily(boolean isDisplaySelectFamily) {
        this.isDisplaySelectFamily = isDisplaySelectFamily;
    }
}
