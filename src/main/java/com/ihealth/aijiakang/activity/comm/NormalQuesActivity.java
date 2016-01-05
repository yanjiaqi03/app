package com.ihealth.aijiakang.activity.comm;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import com.ihealth.aijiakang.activity.BaseActivity;
import com.ihealth.aijiakang.adapter.NormalQuesAdapter;
import com.ihealth.aijiakang.adapter.datasource.NormalQuesAdapterData;
import com.ihealth.aijiakang.widgets.AnimatedExpandableListView;

import net.tsz.afinal.core.AsyncTask;

import java.util.ArrayList;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/12/5
 */
public class NormalQuesActivity extends BaseActivity{
    private final String TAG = "NormalQuesActivity";
    private ArrayList<NormalQuesAdapterData> trunkData = new ArrayList<NormalQuesAdapterData>();
    private ArrayList<NormalQuesAdapterData> tempData = new ArrayList<NormalQuesAdapterData>();
    private NormalQuesAdapter expandAdapter;

    /** XML Resource **/
    private FrameLayout back;
    private AnimatedExpandableListView expandListview;

    @Override
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);

        setContentView(R.layout.activity_normalques);

        initViews();
    }

    private void initViews(){
        back = (FrameLayout) findViewById(R.id.normalques_return);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        expandListview = (AnimatedExpandableListView) findViewById(R.id.normalques_expandlistview);
        expandAdapter = new NormalQuesAdapter(this, trunkData, new NormalQuesAdapter.ClickGroupListener() {
            @Override
            public void click(int groupPosition) {
                for (int i = 0; i < trunkData.size(); i++) {
                    if (i == groupPosition) {
                        if (expandListview.isGroupExpanded(i)) {
                            expandListview.collapseGroupWithAnimation(i);
                            continue;
                        }
                        expandListview.expandGroupWithAnimation(i);
                        continue;
                    }
                    if (expandListview.isGroupExpanded(i)) {
                        expandListview.collapseGroupWithAnimation(i);
                    }
                }
            }
        });
        expandListview.setAdapter(expandAdapter);

        new readQuestion().execute();
    }

    /**
     * 读取本地已有的问题
     * Author YanJiaqi
     * Created at 15/12/5 下午6:04
     */

    private class readQuestion extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            tempData.clear();
            tempData.add(new NormalQuesAdapterData("1.爱家康如何使用？", "1.后打开空间大开发肯德基爱和健康打开来得及啊扩大开放还打卡喝咖啡打卡卡将发挥贷记卡科大好看哈！"));
            tempData.add(new NormalQuesAdapterData("2.爱家康如何使用？","1.后打开空间大开发肯德基爱和健康打开来得及啊扩大开放还打卡喝咖啡打卡卡将发挥贷记卡科大好看哈！"));
            tempData.add(new NormalQuesAdapterData("3.爱家康如何使用？","1.后打开空间大开发肯德基爱和健康打开来得及啊扩大\n\n2.开放还打卡喝咖啡打卡卡将发挥贷记卡科大好看哈！"));
            tempData.add(new NormalQuesAdapterData("4.爱家康如何使用？","1.后打开空间大开发肯德基\n\n2.爱和健康打开来得及啊扩大开放还打卡喝咖啡\n\n3.打卡卡将发挥贷记卡科大好看哈！"));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            trunkData.clear();
            trunkData.addAll(tempData);
            if(expandAdapter!=null){
                expandAdapter.notifyDataSetChanged();
            }
        }
    }
}
