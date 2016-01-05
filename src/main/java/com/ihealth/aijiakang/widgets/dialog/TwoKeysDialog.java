package com.ihealth.aijiakang.widgets.dialog;

import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import org.w3c.dom.Text;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/12/1
 */
public class TwoKeysDialog extends BaseDialog{
    private final String TAG = "TwoKeysDialog";
    public static final int MODE_ORANGE_CANCEL = 100;
    public static final int MODE_ORANGE_CONFIRM = 200;
    private int mode = MODE_ORANGE_CANCEL;
    private String title = "";
    private String content = "";
    private TwoKeysInterface mListener;

    /** XML Resource **/
    private TextView titleTv;
    private TextView contentTv;
    private TextView confirm;
    private TextView cancel;

    public interface TwoKeysInterface{
        public void confirm();
        public void cancel();
    }
    public TwoKeysDialog(Context context, String title, String content,int mode,TwoKeysInterface mListener) {
        super(context, R.layout.dialog_twokeys, true);
        this.title = title;
        this.content = content;
        if(mode == MODE_ORANGE_CANCEL || mode == MODE_ORANGE_CONFIRM){
            this.mode = mode;
        }
        this.mListener = mListener;
    }

    public TwoKeysDialog(Context context, int mode,TwoKeysInterface mListener) {
        super(context, R.layout.dialog_twokeys, true);
        if(mode == MODE_ORANGE_CANCEL || mode == MODE_ORANGE_CONFIRM){
            this.mode = mode;
        }
        this.mListener = mListener;
    }

    @Override
    protected void initviews(Window window) {
        titleTv = (TextView) window.findViewById(R.id.twokeys_dialog_title);
        contentTv = (TextView) window.findViewById(R.id.twokeys_dialog_content);

        confirm = (TextView) window.findViewById(R.id.twokeys_dialog_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TwoKeysDialog.this.dismiss();
                if (mListener != null) {
                    mListener.confirm();
                }
            }
        });

        cancel = (TextView) window.findViewById(R.id.twokeys_dialog_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TwoKeysDialog.this.dismiss();
                if(mListener!=null){
                    mListener.cancel();
                }
            }
        });

        if(mode == MODE_ORANGE_CANCEL){
            confirm.setTextColor(0xff999999);
            confirm.setBackgroundResource(R.drawable.edit_bg);
            cancel.setTextColor(0xffffffff);
            cancel.setBackgroundResource(R.drawable.orange_bt_bg);
        }else if(mode == MODE_ORANGE_CONFIRM){
            confirm.setTextColor(0xffffffff);
            confirm.setBackgroundResource(R.drawable.orange_bt_bg);
            cancel.setTextColor(0xff999999);
            cancel.setBackgroundResource(R.drawable.edit_bg);
        }
    }

    /**
     * 刷新内容
     * Author YanJiaqi
     * Created at 15/12/1 下午7:33
     */

    public void refreshData(String title,String content){
        this.title = title;
        this.content = content;
    }

    @Override
    public void show() {
        super.show();
        if(!title.equals("")){
            titleTv.setText(title);
        }

        contentTv.setText(content);
    }
}
