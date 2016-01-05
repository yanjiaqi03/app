package com.ihealth.aijiakang.activity.comm;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ihealth.aijiakang.activity.BaseActivity;
import com.ihealth.aijiakang.imageloader.ImageLoaderTools;
import com.ihealth.aijiakang.system.AJKLog;
import com.ihealth.aijiakang.utils.CameraUtils;
import com.ihealth.aijiakang.utils.ClickStateUtils;
import com.ihealth.aijiakang.widgets.FontEditText;
import com.ihealth.aijiakang.widgets.FontTextView;
import com.ihealth.aijiakang.widgets.SwitchImageView;
import com.ihealth.aijiakang.widgets.SwitchTextView;
import com.ihealth.aijiakang.widgets.flowlayout.AddPictureLayout;
import com.ihealth.aijiakang.widgets.flowlayout.FlowLayout;
import com.ihealth.aijiakang.widgets.toast.HintToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import iHealth.AiJiaKang.MI.R;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by YanJiaqi on 15/11/29
 */
public class FeedBackActivity extends BaseActivity{
    private final String TAG = "FeedBackActivity";
    private ArrayList<SwitchTextView> textviewArr = new ArrayList<SwitchTextView>();
    private String[] questions = new String[]{"连接问题","查看记录问题","无法充电","家庭管理问题","点击测量没反应","吉吉没反应","吉吉没反应","吉吉没反应"};
    private View addImgView;
    private enum MACHINE{
        BP3M,BP3L,NONE
    }
    private MACHINE machineSelect = MACHINE.NONE;
    private ArrayList<String> pathList = new ArrayList<String>();

    /** XML Resource **/
    private FrameLayout back;
    private FlowLayout normalQuesLayout;
    private FontEditText contentEdit;
    private FontTextView contentRightTag;
    private AddPictureLayout imgAddLayout;
    private LayoutTransition imgAddTransition;
    private RelativeLayout bp3mLayout;
    private SwitchImageView bp3mSelect;
    private RelativeLayout bp3lLayout;
    private SwitchImageView bp3lSelect;
    private FontEditText contactEt;
    private FontTextView commit;

    @Override
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);

        setContentView(R.layout.activity_feedback);

        initViews();
    }

    private void initViews(){
        back = (FrameLayout) findViewById(R.id.feedback_return);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

        normalQuesLayout = (FlowLayout) findViewById(R.id.feedback_normal_question_layout);
        for(int i = 0;i<questions.length;i++){
            addNormalQuesView(normalQuesLayout,questions[i]);
        }

        contentRightTag = (FontTextView) findViewById(R.id.feedback_edit_right_tag);
        contentRightTag.setText("0");

        contentEdit = (FontEditText) findViewById(R.id.feedback_content_edit);
        contentEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                contentRightTag.setText(charSequence.length() + "");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        imgAddLayout = (AddPictureLayout) findViewById(R.id.feedback_addimg_layout);
        loadAddIcon(imgAddLayout);

        imgAddTransition = new LayoutTransition();
        imgAddTransition.setAnimator(LayoutTransition.APPEARING, imgAddTransition.getAnimator(LayoutTransition.APPEARING));
        imgAddTransition.setAnimator(LayoutTransition.CHANGE_APPEARING,
                imgAddTransition.getAnimator(LayoutTransition.CHANGE_APPEARING));
        imgAddTransition.setAnimator(LayoutTransition.DISAPPEARING, imgAddTransition.getAnimator(LayoutTransition.DISAPPEARING));
        imgAddTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING,
                imgAddTransition.getAnimator(LayoutTransition.CHANGE_DISAPPEARING));

        imgAddLayout.setLayoutTransition(imgAddTransition);

        bp3mLayout = (RelativeLayout) findViewById(R.id.feedback_machine_bp3m);
        bp3mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(machineSelect != MACHINE.BP3M){
                    machineSelect = MACHINE.BP3M;
                    bp3mSelect.setSwitchState(SwitchImageView.ON_STATE);
                    bp3lSelect.setSwitchState(SwitchImageView.OFF_STATE);
                }
            }
        });
        bp3mSelect = (SwitchImageView) findViewById(R.id.feedback_bp3m_select_icon);

        bp3lLayout = (RelativeLayout) findViewById(R.id.feedback_machine_bp3l);
        bp3lLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(machineSelect != MACHINE.BP3L) {
                    machineSelect = MACHINE.BP3L;
                    bp3mSelect.setSwitchState(SwitchImageView.OFF_STATE);
                    bp3lSelect.setSwitchState(SwitchImageView.ON_STATE);
                }
            }
        });
        bp3lSelect = (SwitchImageView) findViewById(R.id.feedback_bp3l_select_icon);

        machineSelect = MACHINE.NONE;
        bp3lSelect.setSwitchState(SwitchImageView.OFF_STATE);
        bp3mSelect.setSwitchState(SwitchImageView.OFF_STATE);


        contactEt = (FontEditText) findViewById(R.id.feedback_contact_edittext);

        commit = (FontTextView) findViewById(R.id.feedback_commit_bt);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runCommit();
            }
        });
    }

    /**
     * 添加反馈图片
     * Author YanJiaqi
     * Created at 15/11/30 下午4:26
     */

    private void addContentImg(final AddPictureLayout parent, final String path){
        LayoutInflater mInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View addLayout = mInflater.inflate(R.layout.layout_addimg_item, null);

        final ImageView img = (ImageView) addLayout.findViewById(R.id.feedback_addimg_item_iv);
        img.setImageResource(R.mipmap.ic_launcher);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse("file://" + path), "image/*");
                FeedBackActivity.this.startActivity(intent);
            }
        });
        ImageLoaderTools.getInstance().displaySDImg(path, img);
        ClickStateUtils clickUtils = new ClickStateUtils();
        clickUtils.setAlphaClick(img,1f);

        final ImageView delete = (ImageView) addLayout.findViewById(R.id.feedback_addimg_item_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent.removeView(addLayout);
                pathList.remove(path);
                adjustAddView(parent);
            }
        });

        parent.addView(addLayout, 0);
        adjustAddView(parent);
    }

    /**
     * 判断是否显示添加图片按钮
     * Author YanJiaqi
     * Created at 15/11/30 下午4:31
     */

    private void adjustAddView(AddPictureLayout parent){
        if(addImgView!=null){
            if(parent.getChildCount()>4){
                imgAddLayout.setLayoutTransition(null);
                addImgView.setVisibility(View.GONE);
                imgAddLayout.setLayoutTransition(imgAddTransition);
            }else{
                addImgView.setVisibility(View.VISIBLE);
            }
        }
    }
    /**
     * 添加添加按钮
     * Author YanJiaqi
     * Created at 15/11/30 下午4:24
     */

    private void loadAddIcon(final AddPictureLayout parent){
        LayoutInflater mInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        addImgView = mInflater.inflate(R.layout.layout_addimg_item, null);
        ImageView delete = (ImageView) addImgView.findViewById(R.id.feedback_addimg_item_delete);
        delete.setVisibility(View.GONE);
        ImageView add = (ImageView) addImgView.findViewById(R.id.feedback_addimg_item_iv);
        add.setImageResource(R.mipmap.addimg_add_icon);
        ClickStateUtils clickUtils = new ClickStateUtils();
        clickUtils.setAlphaClick(add,add.getAlpha());
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CameraUtils.getInstance().choosePhotoFromActivityForMuti(FeedBackActivity.this,4-(parent.getChildCount()-1));
            }
        });
        parent.addView(addImgView);
    }
    /**
     * 添加常见问题
     * Author YanJiaqi
     * Created at 15/11/30 上午12:02
     */

    private void addNormalQuesView(FlowLayout parent,String text){
        View view = LayoutInflater.from(this).inflate(R.layout.layout_normal_question_item,null);
        final SwitchTextView mTextView = (SwitchTextView) view.findViewById(R.id.normal_question_item_tv);
        mTextView.setSwitchState(SwitchTextView.OFF_STATE);
        mTextView.setText(text);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int state = mTextView.getCurrentState();
                if (state == SwitchTextView.OFF_STATE) {
                    mTextView.setSwitchState(SwitchTextView.ON_STATE);
                    textviewArr.add(mTextView);
                } else if (state == SwitchTextView.ON_STATE) {
                    mTextView.setSwitchState(SwitchTextView.OFF_STATE);
                    textviewArr.remove(mTextView);
                }
            }
        });
        parent.addView(view);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CameraUtils.REQUEST_IMAGE){
            if(resultCode == RESULT_OK){
                // Get the result list of select image paths
                List<String> paths = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                // do your logic ....
                for(int i = 0;i<paths.size();i++){
                    AJKLog.i(TAG, "path -> " + paths.get(i));
                    if(!pathList.contains(paths.get(i))) {
                        pathList.add(paths.get(i));
                        addContentImg(imgAddLayout, paths.get(i));
                    }
                }
            }
        }
    }

    /**
     * 执行提交反馈
     * Author YanJiaqi
     * Created at 15/12/1 下午2:58
     */

    private void runCommit(){
        if(contentEdit.getText().toString().trim().length()==0){
            HintToast.makeText(this,getResources().getString(R.string.feedback_content_not_null),HintToast.LENGTH_SHORT).show();
            return;
        }

        if(machineSelect == MACHINE.NONE){
            HintToast.makeText(this,getResources().getString(R.string.feedback_select_not_null),HintToast.LENGTH_SHORT).show();
            return;
        }

        //常见问题
        String normalQues = "";
        for(int i = 0;i<textviewArr.size();i++){
            normalQues = textviewArr.get(i).getText().toString().trim();
            AJKLog.i(TAG,"normalQues -> " + normalQues);
        }

        //反馈内容
        String content = contentEdit.getText().toString().trim();
        AJKLog.i(TAG,"content -> " + content);

        //反馈图片
        for(int i = 0;i<pathList.size();i++){
            AJKLog.i(TAG,"imgPath -> " + pathList.get(i));
        }

        //血压计类型
        switch (machineSelect){
            case BP3M:
                AJKLog.i(TAG,"BP3M 血压计");
                break;
            case BP3L:
                AJKLog.i(TAG,"BP3L 血压计");
                break;
        }

        //联系方式
        String contact = contactEt.getText().toString().trim();
        AJKLog.i(TAG,"contact -> " + contact);

        //Do Cloud Logic...

    }
}
