package com.ihealth.aijiakang.activity.user;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ihealth.aijiakang.activity.BaseActivity;
import com.ihealth.aijiakang.logic.control.FamilyMemberControl;
import com.ihealth.aijiakang.logic.LogicCallbackInterface;
import com.ihealth.aijiakang.logic.LogicMethod;
import com.ihealth.aijiakang.system.AJKLog;
import com.ihealth.aijiakang.utils.BitMapUtils;
import com.ihealth.aijiakang.utils.CameraUtils;
import com.ihealth.aijiakang.utils.DrawableUtils;
import com.ihealth.aijiakang.utils.VariableUtils;
import com.ihealth.aijiakang.widgets.ClickBgRelativeLayout;
import com.ihealth.aijiakang.widgets.FontEditText;
import com.ihealth.aijiakang.widgets.FontTextView;
import com.ihealth.aijiakang.widgets.dialog.DateWheelDialog;
import com.ihealth.aijiakang.widgets.dialog.GenderWheelDialog;
import com.ihealth.aijiakang.widgets.dialog.HeightWheelDialog;
import com.ihealth.aijiakang.widgets.dialog.WeightWheelDialog;
import com.ihealth.aijiakang.widgets.toast.HintToast;

import java.util.List;

import iHealth.AiJiaKang.MI.R;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by YanJiaqi on 15/11/26
 */
public class AddFamilyPersonActivity extends BaseActivity {
    private final String TAG = "AddFamilyPersonActivity";

    private enum GENDER {
        MALE, FEMALE, NONE
    }

    private String logoPath = "";
    private int mYear = 0;
    private int mMonth = 0;
    private int mDay = 0;
    private GENDER gender = GENDER.NONE;
    private int mHeight = 0;
    private float mWeight = 0f;
    private DateWheelDialog birthDialog = null;
    private GenderWheelDialog genderDialog = null;
    private HeightWheelDialog heightDialog = null;
    private WeightWheelDialog weightDialog = null;
    private Bitmap iconBmp;
    /**
     * XML Resource
     **/
    private FrameLayout back;
    private FontTextView save;
    private ClickBgRelativeLayout logoLayout;
    private ImageView logoIv;
    private FontEditText nickEt;
    private ClickBgRelativeLayout birthLayout;
    private FontTextView birthTv;
    private ClickBgRelativeLayout genderLayout;
    private FontTextView genderTv;
    private ClickBgRelativeLayout heightLayout;
    private FontTextView heightTv;
    private ClickBgRelativeLayout weightLayout;
    private FontTextView weightTv;

    @Override
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        setContentView(R.layout.activity_addfamilyperson);
        initViews();
    }

    private void initViews() {
        back = (FrameLayout) findViewById(R.id.addfamilyperson_return);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });
        save = (FontTextView) findViewById(R.id.addfamilyperson_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (logoPath != null && logoPath.length() > 0){
                    String name = nickEt.getText().toString().trim();
                    if(!nickEt.checkNameWithToast(name)){
                        return;
                    }
                    addNewFamilyMember();
                }else{
                    HintToast.makeText(AddFamilyPersonActivity.this,getResources().getString(R.string.logo_not_null),HintToast.LENGTH_SHORT).show();
                }
            }
        });
        save.setClickable(true);
        save.setAlpha(1f);

        logoLayout = (ClickBgRelativeLayout) findViewById(R.id.addfamilyperson_logo_layout);
        logoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CameraUtils.getInstance().choosePhotoFromActivityForOne(AddFamilyPersonActivity.this);
            }
        });
        logoIv = (ImageView) findViewById(R.id.addfamilyperson_logo);

        nickEt = (FontEditText) findViewById(R.id.addfamilyperson_nickname);

            nickEt.setText("");
            nickEt.setOnFocusChangeListener(new View.OnFocusChangeListener()

            {
                @Override
                public void onFocusChange (View v,boolean hasFocus){
                AJKLog.i(TAG, "hasFocus -> " + hasFocus);
                if (!hasFocus) {
                    nickEt.setCursorVisible(false);
                }
            }
            }

            );

            nickEt.setOnTouchListener(new View.OnTouchListener()

            {
                @Override
                public boolean onTouch (View v, MotionEvent event){
                AJKLog.i(TAG, "nickEt->onTouch");
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (!nickEt.isCursorVisible()) {
                        nickEt.setCursorVisible(true);
                        nickEt.setSelection(nickEt.getText().toString().length());
                    }
                }
                return false;
            }
        });

        nickEt.setClickable(true);
        nickEt.setCursorVisible(false);
        nickEt.setText("");
        birthLayout = (ClickBgRelativeLayout) findViewById(R.id.addfamilyperson_birth_layout);
        birthLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (birthDialog == null || !birthDialog.isShowing()) {
                    birthDialog = new DateWheelDialog(AddFamilyPersonActivity.this, mYear, mMonth, mDay, new DateWheelDialog.dateCallback() {
                        @Override
                        public void getDate(int year, int month, int day) {
                            mYear = year;
                            mMonth = month;
                            mDay = day;

                            birthTv.setText(getBirth(mYear, mMonth, mDay));
                        }
                    });
                    birthDialog.show();
                }
            }
        });

        birthTv = (FontTextView) findViewById(R.id.addfamilyperson_birth);

        genderLayout = (ClickBgRelativeLayout) findViewById(R.id.addfamilyperson_gender_layout);
        genderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (genderDialog == null || !genderDialog.isShowing()) {
                    genderDialog = new GenderWheelDialog(AddFamilyPersonActivity.this, genderTv.getText().toString().trim(), new GenderWheelDialog.genderCallback() {
                        @Override
                        public void getGender(String txt) {
                            genderTv.setText(txt);
                            if (txt.equals(getResources().getString(R.string.male))) {
                                gender = GENDER.MALE;
                            } else if (txt.equals(getResources().getString(R.string.female))) {
                                gender = GENDER.FEMALE;
                            } else {
                                gender = GENDER.NONE;
                            }
                        }
                    });
                }
                genderDialog.show();
            }
        });

        genderTv = (FontTextView) findViewById(R.id.addfamilyperson_gender);

        heightLayout = (ClickBgRelativeLayout) findViewById(R.id.addfamilyperson_height_layout);
        heightLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (heightDialog == null || !heightDialog.isShowing()) {
                    heightDialog = new HeightWheelDialog(AddFamilyPersonActivity.this, mHeight, new HeightWheelDialog.heightCallback() {
                        @Override
                        public void getHeight(int height) {
                            mHeight = height;
                            heightTv.setText(getMyHeight(mHeight));
                        }
                    });
                    heightDialog.show();
                }
            }
        });

        heightTv = (FontTextView) findViewById(R.id.addfamilyperson_height);

        weightLayout = (ClickBgRelativeLayout) findViewById(R.id.addfamilyperson_weight_layout);
        weightLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (weightDialog == null || !weightDialog.isShowing()) {
                    weightDialog = new WeightWheelDialog(AddFamilyPersonActivity.this, mWeight, new WeightWheelDialog.weightCallback() {
                        @Override
                        public void getWeight(float weight) {
                            mWeight = weight;
                            weightTv.setText(getMyWeight(mWeight));
                        }
                    });
                    weightDialog.show();
                }
            }
        });
        weightTv = (FontTextView) findViewById(R.id.addfamilyperson_weight);
    }

    /**
     * 获取生日字符串
     * Author YanJiaqi
     * Created at 15/11/26 下午10:44
     */

    public String getBirth(int year, int month, int day) {
        if (year <= 0 || month <= 0 || day <= 0) {
            return getResources().getString(R.string.userinfo_un_edit);
        }
        return ((year > 0 ? year : "") + getResources().getString(R.string.wheel_date_year)
                + (month > 0 ? month : "") + getResources().getString(R.string.wheel_date_month)
                + (day > 0 ? day : "") + getResources().getString(R.string.wheel_date_day));
    }

    /**
     * 获取身高字符串
     * Author YanJiaqi
     * Created at 15/11/26 下午10:44
     */

    public String getMyHeight(int height) {
        if (height <= 0) {
            return getResources().getString(R.string.userinfo_un_edit);
        }
        return ((height > 0 ? height : "") + getResources().getString(R.string.wheel_height_unit));
    }

    /**
     * 获取体重字符串
     * Author YanJiaqi
     * Created at 15/11/26 下午10:44
     */

    public String getMyWeight(float weight) {
        if (weight <= 0) {
            return getResources().getString(R.string.userinfo_un_edit);
        }
        return ((weight > 0f ? weight : "") + getResources().getString(R.string.wheel_weight_unit));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CameraUtils.REQUEST_IMAGE) {
                // Get the result list of select image paths
                List<String> paths = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                // do your logic ....
                if (paths != null && paths.size() > 0) {
                    logoPath = paths.get(0);
                    CameraUtils.getInstance().cutGalleryPhoto(AddFamilyPersonActivity.this, Uri.parse("file://" + logoPath), VariableUtils.PATH + System.currentTimeMillis() + ".jpg", 1, 1);
                }
            } else if (requestCode == CameraUtils.GALLERY_PHOTO_CUT) {
                logoPath = CameraUtils.getInstance().getPhotoAbsolutePath(AddFamilyPersonActivity.this, data);
                logoIv.setImageDrawable(null);
                if (iconBmp != null && !iconBmp.isRecycled()) {
                    iconBmp.recycle();
                }
                int iconSize = (int) getResources().getDimension(R.dimen.userinfo_logo_size);
                iconBmp = BitMapUtils.getInstance().getScaledBitmap(logoPath, iconSize, iconSize);
                logoIv.setImageDrawable(DrawableUtils.getInstance().getCircleDrawable(iconBmp));

//                checkSaveState(nickEt.getText().toString().trim());
            }
        }
    }

//    /**
//     * 检查完成按钮状态
//     * Author YanJiaqi
//     * Created at 15/12/7 下午4:16
//     */
//
//    private void checkSaveState(String name){
//        if(logoPath!=null&&logoPath.length()>0&&nickEt.checkName(name)){
//            save.setClickable(true);
//            save.setAlpha(1f);
//        }else{
//            save.setClickable(false);
//            save.setAlpha(0.5f);
//        }
//    }

    private void addNewFamilyMember() {
        String base64Image = LogicMethod.getInstance().getBase64Image(AddFamilyPersonActivity.this, -1, logoPath);
        final long birthday = LogicMethod.getInstance().getBirthday(mYear, mMonth, mDay);
        final long logoTS = System.currentTimeMillis() / 1000;
        final int cloudGender = LogicMethod.getInstance().getCloudGender(gender.ordinal());
        AJKLog.i(TAG, "addNewFamilyMember cloudGender = " + cloudGender);
        showLoadingBar(true);
        FamilyMemberControl.getInstance().addFamilyMember(AddFamilyPersonActivity.this, nickEt.getText().toString(),
                mWeight, birthday, mHeight, base64Image, logoTS, cloudGender, new LogicCallbackInterface() {
                    @Override
                    public void onSuccess(String result) {
                        //更新完成通知家人管理刷新界面
                        showLoadingBar(false);
                        AJKLog.i(TAG, "Add family member success");
                        AddFamilyPersonActivity.this.sendBroadcast(new Intent(VariableUtils.RELATIONSHIP_UPDATE_FAMILY));
                        goBack();
                    }

                    @Override
                    public void onFailure(String errorNum, String content) {
                        showLoadingBar(false);
                        AJKLog.i(TAG, "Add family member fail");
                    }
                });
    }
}
