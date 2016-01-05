package com.ihealth.aijiakang.activity.user;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.ihealth.aijiakang.activity.BaseActivity;
import com.ihealth.aijiakang.database.data.Data_UserInfo;
import com.ihealth.aijiakang.imageloader.ImageLoaderTools;
import com.ihealth.aijiakang.logic.LogicCallbackInterface;
import com.ihealth.aijiakang.logic.LogicMethod;
import com.ihealth.aijiakang.logic.control.UserInfoControl;
import com.ihealth.aijiakang.sharedpreference.UserConfig;
import com.ihealth.aijiakang.system.AJKLog;
import com.ihealth.aijiakang.utils.BitMapUtils;
import com.ihealth.aijiakang.utils.CameraUtils;
import com.ihealth.aijiakang.utils.DateUtils;
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
public class UserInfoActivity extends BaseActivity {
    private final String TAG = "UserInfoActivity";
    private enum GENDER {
        MALE, FEMALE, NONE
    }
    private boolean nickNameChanged = false;
    private String logoPath = "";
    private String name = "";
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
        setContentView(R.layout.activity_userinfo);
        initViews();
    }

    private void initViews() {
        back = (FrameLayout) findViewById(R.id.userinfo_return);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });
        save = (FontTextView) findViewById(R.id.userinfo_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nickEt.getText().toString().trim();
                if(nickNameChanged){
                    if(!nickEt.checkNameWithToast(name)){
                        return;
                    }
                }
                final int currentUserId = UserConfig.getInstance().getConfigUserID(UserInfoActivity.this);
                final int userId = getIntent().getIntExtra(UserConfig.USER_ID, currentUserId);
                AJKLog.i(TAG, "currentUserId = " + currentUserId);
                AJKLog.i(TAG, "userId = " + userId);
                String base64Image = LogicMethod.getInstance().getBase64Image(UserInfoActivity.this, userId, logoPath);
                final long birthday = LogicMethod.getInstance().getBirthday(mYear, mMonth, mDay);
                final long logoTS = System.currentTimeMillis() / 1000;
                final int cloudGender = LogicMethod.getInstance().getCloudGender(gender.ordinal());
                showLoadingBar(true);
                AJKLog.i(TAG, "---" + gender.ordinal());
                AJKLog.i(TAG, "birthday ＝ " + birthday);
                UserInfoControl.getInstance().uploadUserInfo(UserInfoActivity.this, userId, name, mWeight,
                        birthday, mHeight, base64Image, logoTS, cloudGender, new LogicCallbackInterface() {
                            @Override
                            public void onSuccess(String result) {
                                showLoadingBar(false);
                                logoPath = result;
                                HintToast.makeText(UserInfoActivity.this, getString(R.string.userinfo_save_success), Toast.LENGTH_SHORT).show();
                                if(currentUserId == userId) {
                                    UserInfoActivity.this.sendBroadcast(new Intent(VariableUtils.HOST_ME_UPDATE_USER));
                                }else {
                                    UserInfoActivity.this.sendBroadcast(new Intent(VariableUtils.RELATIONSHIP_UPDATE_FAMILY));
                                }
                            }
                            @Override
                            public void onFailure(String errorNum, String content) {
                                showLoadingBar(false);
                                HintToast.makeText(UserInfoActivity.this, getString(R.string.userinfo_save_fail), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        logoLayout = (ClickBgRelativeLayout) findViewById(R.id.userinfo_logo_layout);
        logoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CameraUtils.getInstance().choosePhotoFromActivityForOne(UserInfoActivity.this);
            }
        });
        logoIv = (ImageView) findViewById(R.id.userinfo_logo);
        logoIv.setImageDrawable(null);
        nickEt = (FontEditText) findViewById(R.id.userinfo_nickname);
        nickEt.setText("");
        nickEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nickNameChanged = true;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        nickEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                AJKLog.i(TAG, "hasFocus -> " + hasFocus);
                if (!hasFocus) {
                    nickEt.setCursorVisible(false);
                }
            }
        });

        nickEt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
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

        birthLayout = (ClickBgRelativeLayout) findViewById(R.id.userinfo_birth_layout);
        birthLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (birthDialog == null || !birthDialog.isShowing()) {
                    birthDialog = new DateWheelDialog(UserInfoActivity.this, mYear, mMonth, mDay, new DateWheelDialog.dateCallback() {
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

        birthTv = (FontTextView) findViewById(R.id.userinfo_birth);
        birthTv.setText("");

        genderLayout = (ClickBgRelativeLayout) findViewById(R.id.userinfo_gender_layout);
        genderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (genderDialog == null || !genderDialog.isShowing()) {
                    genderDialog = new GenderWheelDialog(UserInfoActivity.this, genderTv.getText().toString().trim(), new GenderWheelDialog.genderCallback() {
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

        genderTv = (FontTextView) findViewById(R.id.userinfo_gender);
        genderTv.setText("");

        heightLayout = (ClickBgRelativeLayout) findViewById(R.id.userinfo_height_layout);
        heightLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (heightDialog == null || !heightDialog.isShowing()) {
                    heightDialog = new HeightWheelDialog(UserInfoActivity.this, mHeight, new HeightWheelDialog.heightCallback() {
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

        heightTv = (FontTextView) findViewById(R.id.userinfo_height);
        heightTv.setText("");

        weightLayout = (ClickBgRelativeLayout) findViewById(R.id.userinfo_weight_layout);
        weightLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (weightDialog == null || !weightDialog.isShowing()) {
                    weightDialog = new WeightWheelDialog(UserInfoActivity.this, mWeight, new WeightWheelDialog.weightCallback() {
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
        weightTv = (FontTextView) findViewById(R.id.userinfo_weight);
        weightTv.setText("");
        new readUser().execute();
        downloadUserInfo();
    }

    /**
     * 获取性别字符串
     * Author YanJiaqi
     * Created at 15/11/26 下午10:37
     */

    public void getGender(GENDER e) {
        AJKLog.i(TAG, "getGender = " + e);
        switch (e) {
            case MALE:
                genderTv.setText(getResources().getString(R.string.male));
                break;
            case FEMALE:
                genderTv.setText(getResources().getString(R.string.female));
                break;
            case NONE:
                genderTv.setText(getResources().getString(R.string.userinfo_un_edit));
                break;
        }
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

    private void downloadUserInfo() {
        showLoadingBar(true);
        int currentUserId = UserConfig.getInstance().getConfigUserID(UserInfoActivity.this);
        int userId = getIntent().getIntExtra(UserConfig.USER_ID, currentUserId);
        AJKLog.i(TAG, "downloadUserInfo currentUserId = " + currentUserId);
        AJKLog.i(TAG, "downloadUserInfo userId = " + userId);
        UserInfoControl.getInstance().downLoadUserInfo(UserInfoActivity.this, userId, new LogicCallbackInterface() {
            @Override
            public void onSuccess(String result) {
                showLoadingBar(false);
                new readUser().execute();
            }
            @Override
            public void onFailure(String errorNum, String content) {
                showLoadingBar(false);
                new readUser().execute();
            }
        });
    }
    /**
     * 读取用户信息异步任务
     * Author YanJiaqi
     * Created at 15/11/26 下午10:50
     */
    private class readUser extends AsyncTask<Void, Data_UserInfo, Data_UserInfo> {
        @Override
        protected Data_UserInfo doInBackground(Void... voids) {
            int currentUserId = UserConfig.getInstance().getConfigUserID(UserInfoActivity.this);
            final int userId = getIntent().getIntExtra(UserConfig.USER_ID, currentUserId);
            return UserInfoControl.getInstance().getUserInfo(UserInfoActivity.this, userId);
        }
        @Override
        protected void onPostExecute(Data_UserInfo userInfo) {
            updateUserInfo(userInfo);
        }
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
                    CameraUtils.getInstance().cutGalleryPhoto(UserInfoActivity.this, Uri.parse("file://" + logoPath), VariableUtils.PATH + System.currentTimeMillis() + ".jpg", 1, 1);
                }
            } else if (requestCode == CameraUtils.GALLERY_PHOTO_CUT) {
                logoPath = CameraUtils.getInstance().getPhotoAbsolutePath(UserInfoActivity.this, data);
                logoIv.setImageDrawable(null);
                if (iconBmp != null && !iconBmp.isRecycled()) {
                    iconBmp.recycle();
                }
                int iconSize = (int) getResources().getDimension(R.dimen.userinfo_logo_size);
                iconBmp = BitMapUtils.getInstance().getScaledBitmap(logoPath, iconSize, iconSize);
                logoIv.setImageDrawable(DrawableUtils.getInstance().getCircleDrawable(iconBmp));
            }
        }
    }

    private void updateUserInfo(Data_UserInfo userInfo) {
        if(userInfo != null) {
            long millis = userInfo.getBirthDay() * 1000;
            AJKLog.i(TAG, "millis = " + millis);
            DateUtils dateUtils = DateUtils.getInstance();
            dateUtils.setTimeMillis(millis);
            mYear = dateUtils.getYear();
            AJKLog.i(TAG, "mYear ＝ " + mYear);
            mMonth = dateUtils.getMonth();
            AJKLog.i(TAG, "mMonth ＝ " + mMonth);
            mDay = dateUtils.getDay();
            AJKLog.i(TAG, "mDay ＝ " + mDay);
            logoPath = userInfo.getLogo();
            name = userInfo.getName();
            gender = GENDER.values()[LogicMethod.getInstance().getGender(userInfo.getGender())];
            mHeight = userInfo.getHeight();
            mWeight = userInfo.getWeight();

            ImageLoaderTools.getInstance().displayNetRoundImg(logoPath, logoIv);
            nickEt.setText(name);
            nickEt.setSelection(name.length());//光标位置最后
            birthTv.setText(getBirth(mYear, mMonth, mDay));
            getGender(gender);
            heightTv.setText(getMyHeight(mHeight));
            weightTv.setText(getMyWeight(mWeight));
        }
    }
}
