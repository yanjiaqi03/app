package com.ihealth.aijiakang.widgets.dialog;

import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.ihealth.aijiakang.widgets.ClickBgLinearLayout;
import com.ihealth.aijiakang.widgets.FontTextView;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/11/27
 */
public class ChoosePicDialog extends BaseDialog{
    private final String TAG = "ChoosePicDialog";
    private FontTextView titleTv;
    private ClickBgLinearLayout camera;
    private ClickBgLinearLayout pictureShop;
    private ChooseListener mChooseListener;
    private String title = "";

    public interface ChooseListener{
        void chooseCamera();
        void choosePicshop();
    }

    public ChoosePicDialog(Context context,String title,ChooseListener mChooseListener) {
        super(context, R.layout.dialog_choose_pic, true, Gravity.BOTTOM, R.style.translate_dialog);
        this.mChooseListener = mChooseListener;
        this.title = title;
    }

    @Override
    protected void initviews(Window window) {
        WindowManager.LayoutParams lp = window.getAttributes();
        WindowManager windowManager = window.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        lp.width = (display.getWidth()); // 设置宽度
        window.setAttributes(lp);

        titleTv = (FontTextView) window.findViewById(R.id.choose_title);
        titleTv.setText(title);

        camera = (ClickBgLinearLayout) window.findViewById(R.id.choose_from_camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mChooseListener!=null){
                    mChooseListener.chooseCamera();
                }
                ChoosePicDialog.this.dismiss();
            }
        });

        pictureShop = (ClickBgLinearLayout) window.findViewById(R.id.choose_from_picshop);
        pictureShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mChooseListener!=null){
                    mChooseListener.choosePicshop();
                }
                ChoosePicDialog.this.dismiss();
            }
        });
    }
}
