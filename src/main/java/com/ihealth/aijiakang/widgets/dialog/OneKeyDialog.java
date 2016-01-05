package com.ihealth.aijiakang.widgets.dialog;

import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.ihealth.aijiakang.widgets.FontTextView;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/12/1
 */
public class OneKeyDialog extends BaseDialog{
    private final String TAG = "OneKeyDialog";
    private String title = "";
    private String content = "";
    private OneKeyInterface mListener;

    /** XML Resource **/
    private TextView titleTv;
    private TextView contentTv;
    private TextView confirm;

    public interface  OneKeyInterface{
        public void confirm();
    }
    public OneKeyDialog(Context context,String title,String content,OneKeyInterface mListener) {
        super(context, R.layout.dialog_onekey, true);
        this.title = title;
        this.content = content;
        this.mListener = mListener;
    }

    public OneKeyDialog(Context context,OneKeyInterface mListener) {
        super(context, R.layout.dialog_onekey, true);
        this.mListener = mListener;
    }


    @Override
    protected void initviews(Window window) {
        titleTv = (TextView) window.findViewById(R.id.onekey_dialog_title);
        contentTv = (TextView) window.findViewById(R.id.onekey_dialog_content);

        confirm = (TextView) window.findViewById(R.id.onekey_dialog_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OneKeyDialog.this.dismiss();
                if(mListener!=null){
                    mListener.confirm();
                }
            }
        });
    }

    /**
     * 显示对话框并刷新内容
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
