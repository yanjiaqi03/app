package com.ihealth.aijiakang.activity.user;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ihealth.aijiakang.activity.BaseActivity;
import com.ihealth.aijiakang.adapter.RelationshipAdapter;
import com.ihealth.aijiakang.adapter.datasource.RelationshipAdapterData;
import com.ihealth.aijiakang.database.data.Data_Family_Member;
import com.ihealth.aijiakang.logic.control.FamilyMemberControl;
import com.ihealth.aijiakang.logic.database.FamilyMemberLogic;
import com.ihealth.aijiakang.logic.LogicCallbackInterface;
import com.ihealth.aijiakang.sharedpreference.UserConfig;
import com.ihealth.aijiakang.system.AJKLog;
import com.ihealth.aijiakang.utils.AnimationUtils;
import com.ihealth.aijiakang.utils.VariableUtils;
import com.ihealth.aijiakang.widgets.dialog.TwoKeysDialog;

import java.util.ArrayList;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/27
 */
public class RelationshipActivity extends BaseActivity {
    private final String TAG = "RelationshipActivity";
    private RelationshipAdapter mAdapter;
    private ArrayList<RelationshipAdapterData> trunkData = new ArrayList<RelationshipAdapterData>();
    private ArrayList<RelationshipAdapterData> tempData = new ArrayList<RelationshipAdapterData>();
    private BroadcastReceiver mBroadcastReceiver;
    private final int NOT_EDIT = 0;
    private final int EDITING = 1;
    private int editState = NOT_EDIT;
    private TwoKeysDialog deleteDialog = null;

    /**
     * XML Resource
     **/
    private FrameLayout back;
    private LinearLayout addPerson;
    private LinearLayout noPersonLayout;
    private ListView mListView;
    private TextView edit;
    private TextView editFinish;

    @Override
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);

        setContentView(R.layout.activity_relationship);

        initViews();

        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals(VariableUtils.RELATIONSHIP_UPDATE_FAMILY)) {
                    AJKLog.i(TAG, "Update family member");
                    new readRelationship().execute();
                }
            }
        };
        IntentFilter mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(VariableUtils.RELATIONSHIP_UPDATE_FAMILY);
        registerReceiver(mBroadcastReceiver, mIntentFilter);
        downloadFamilyMember();
    }

    private void initViews() {
        back = (FrameLayout) findViewById(R.id.relationship_return);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

        addPerson = (LinearLayout) findViewById(R.id.relationship_add);
        addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpActivity(RelationshipActivity.this, AddFamilyPersonActivity.class);
            }
        });

        edit = (TextView) findViewById(R.id.relationship_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editState == NOT_EDIT) {
                    runEdit();
                }
            }
        });

        editFinish = (TextView) findViewById(R.id.relationship_edit_finish);
        editFinish.setVisibility(View.GONE);
        editFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editState == EDITING) {
                    runEditFinish();
                }
            }
        });

        noPersonLayout = (LinearLayout) findViewById(R.id.relationship_noperson_layout);

        mListView = (ListView) findViewById(R.id.relationship_listview);
        mAdapter = new RelationshipAdapter(this, trunkData, new RelationshipAdapter.ClickListener() {
            @Override
            public void itemClick(int position) {
                //进入个人信息
                Bundle bundle = new Bundle();
                bundle.putInt(UserConfig.USER_ID, trunkData.get(position).getUserId());
                jumpActivity(RelationshipActivity.this, UserInfoActivity.class, bundle);
            }

            @Override
            public void deleteClick(int position) {

                showDeleteDialog(position);
            }
        });
        mListView.setAdapter(mAdapter);

        new readRelationship().execute();
    }

    /**
     * 读取家人
     * Author YanJiaqi
     * Created at 15/11/27 下午6:12
     */

    private class readRelationship extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            tempData.clear();
//            tempData.add(new RelationshipAdapterData(0, "http://photo.icxo.com/20091/2009123081575.jpg", "小坏"));
//            tempData.add(new RelationshipAdapterData(0, "http://images2.4hw.com.cn/20150412/e523d10d01552e0015edfee86da95635.png", "小明"));
//            tempData.add(new RelationshipAdapterData(0, "http://img.xmfish.com/attachment/month_0805/20080501_f75fb217157da81c93eeo0x0cZK6XR9j.jpg", "小花"));
//            tempData.add(new RelationshipAdapterData(0, "http://img1.imgtn.bdimg.com/it/u=3899173834,1482769735&fm=21&gp=0.jpg", "小楠"));
//            tempData.add(new RelationshipAdapterData(0, "http://photo.icxo.com/20091/2009123081575.jpg", "小坏"));
//            tempData.add(new RelationshipAdapterData(0, "http://images2.4hw.com.cn/20150412/e523d10d01552e0015edfee86da95635.png", "小明"));
//            tempData.add(new RelationshipAdapterData(0, "http://img.xmfish.com/attachment/month_0805/20080501_f75fb217157da81c93eeo0x0cZK6XR9j.jpg", "小花"));
//            tempData.add(new RelationshipAdapterData(0, "http://img1.imgtn.bdimg.com/it/u=3899173834,1482769735&fm=21&gp=0.jpg", "小楠"));
//            tempData.add(new RelationshipAdapterData(0, "http://photo.icxo.com/20091/2009123081575.jpg", "小坏"));
//            tempData.add(new RelationshipAdapterData(0, "http://images2.4hw.com.cn/20150412/e523d10d01552e0015edfee86da95635.png", "小明"));
//            tempData.add(new RelationshipAdapterData(0, "http://img.xmfish.com/attachment/month_0805/20080501_f75fb217157da81c93eeo0x0cZK6XR9j.jpg", "小花"));
//            tempData.add(new RelationshipAdapterData(0, "http://img1.imgtn.bdimg.com/it/u=3899173834,1482769735&fm=21&gp=0.jpg", "小楠"));
//            tempData.add(new RelationshipAdapterData(0, "http://photo.icxo.com/20091/2009123081575.jpg", "小坏"));
//            tempData.add(new RelationshipAdapterData(0, "http://images2.4hw.com.cn/20150412/e523d10d01552e0015edfee86da95635.png", "小明"));
//            tempData.add(new RelationshipAdapterData(0, "http://img.xmfish.com/attachment/month_0805/20080501_f75fb217157da81c93eeo0x0cZK6XR9j.jpg", "小花"));
//            tempData.add(new RelationshipAdapterData(0, "http://img1.imgtn.bdimg.com/it/u=3899173834,1482769735&fm=21&gp=0.jpg", "小楠"));
            setFamilyDataToList();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            trunkData.clear();
            trunkData.addAll(tempData);
            if (mAdapter != null) {
                mAdapter.notifyDataSetChanged();
            }
            if (trunkData.size() == 0) {
                noPersonLayout.setVisibility(View.VISIBLE);
            } else {
                noPersonLayout.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 执行编辑操作
     * Author YanJiaqi
     * Created at 15/12/7 下午4:59
     */

    private void runEdit() {
        editState = EDITING;
        edit.setVisibility(View.GONE);
        editFinish.setVisibility(View.VISIBLE);
        setListViewEdit(true);
        AnimationUtils.getInstance().runTranslationXAnim(mListView, mListView.getTranslationX(), 0, 200, new DecelerateInterpolator());
    }

    /**
     * 执行编辑完成操作
     * Author YanJiaqi
     * Created at 15/12/7 下午4:59
     */

    private void runEditFinish() {
        editState = NOT_EDIT;
        edit.setVisibility(View.VISIBLE);
        editFinish.setVisibility(View.GONE);
        setListViewEdit(false);
        float trans = getResources().getDimension(R.dimen.relationship_delete_layout_marginleft);
        AnimationUtils.getInstance().runTranslationXAnim(mListView, mListView.getTranslationX(), trans, 200, new DecelerateInterpolator());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (editState == EDITING) {
                runEditFinish();
            } else {
                goBack();
            }
            return true;
        }

        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销广播
        try {
            unregisterReceiver(mBroadcastReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置listview是否在编辑状态(修改数据源)
     * Author YanJiaqi
     * Created at 15/12/7 下午6:11
     */

    private void setListViewEdit(boolean isEditing) {
        if (trunkData == null && mAdapter == null) {
            return;
        }

        for (int i = 0; i < trunkData.size(); i++) {
            trunkData.get(i).setIsEditing(isEditing);
        }

        mAdapter.notifyDataSetChanged();
    }

    /**
     * 显示删除提示框
     * Author YanJiaqi
     * Created at 15/12/7 下午6:30
     */

    private void showDeleteDialog(final int position) {
        AJKLog.i(TAG, "showDeleteDialog position = " + position);
        deleteDialog = new TwoKeysDialog(RelationshipActivity.this, TwoKeysDialog.MODE_ORANGE_CONFIRM,
                new TwoKeysDialog.TwoKeysInterface() {
                    @Override
                    public void confirm() {
                        //执行删除家人
                        showLoadingBar(true);
                        FamilyMemberControl.getInstance().delFamilyMember(RelationshipActivity.this, trunkData.get(position).getUserId(), new LogicCallbackInterface() {
                            @Override
                            public void onSuccess(String result) {
                                //执行删除家人成功
                                AJKLog.i(TAG, "Delete family member success");
                                if (mAdapter != null) {
                                    trunkData.remove(position);
                                    mAdapter.notifyDataSetChanged();
                                    AJKLog.i(TAG, "Update family member list");
                                }
                                showLoadingBar(false);
                            }
                            @Override
                            public void onFailure(String errorNum, String content) {
                                showLoadingBar(false);
                            }
                        });
                    }
                    @Override
                    public void cancel() {

                    }
                });

        if(!deleteDialog.isShowing()){
            deleteDialog.refreshData("",getResources().getString(R.string.relationship_isdelete) +
            "（" +
            trunkData.get(position).getUserName() +
            "）");
            deleteDialog.show();
        }
    }

    /**
     * 下载家人信息
     * @author lanbaoshi
     * created at 15/12/7 下午6:45
     */
    private void downloadFamilyMember() {
        String currentUser = UserConfig.getInstance().getConfigCurrentUser(RelationshipActivity.this);
        showLoadingBar(true);
        FamilyMemberControl.getInstance().downloadFamilyMembersUserInfo(RelationshipActivity.this, currentUser, new LogicCallbackInterface() {
            @Override
            public void onSuccess(String result) {
                showLoadingBar(false);
                AJKLog.i(TAG, "Download family member success");
            }
            @Override
            public void onFailure(String errorNum, String content) {
                showLoadingBar(false);
                AJKLog.i(TAG, "Download family member fail");
            }
        });
    }

    private void setFamilyDataToList() {
        int currentFamilyId = UserConfig.getInstance().getConfigFamilyId(RelationshipActivity.this);
        ArrayList<Data_Family_Member> list = new FamilyMemberLogic(RelationshipActivity.this).searchFamilyMember(currentFamilyId, 0);
        for(int i=0; i<list.size(); i++) {
            tempData.add(new RelationshipAdapterData(list.get(i).getUserId(), list.get(i).getHeadImg(), list.get(i).getRemark()));
        }
    }
}
