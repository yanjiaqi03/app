package com.ihealth.aijiakang.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ihealth.aijiakang.widgets.CustomWebView;
import com.ihealth.aijiakang.widgets.FontTextView;

import iHealth.AiJiaKang.MI.R;

/**
 * Created by YanJiaqi on 15/12/5
 */
public abstract class WebActivity extends BaseActivity{
    /** XML Resource **/
    private FrameLayout back;
    private FontTextView title;
    private ProgressBar webLoadingBar;
    private CustomWebView webView;
    @Override
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);

        setContentView(R.layout.activity_web);

        initViews();
    }

    private void initViews(){
        back = (FrameLayout) findViewById(R.id.web_return);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        title = (FontTextView) findViewById(R.id.web_title);

        webLoadingBar = (ProgressBar) findViewById(R.id.web_progressbar);
        webLoadingBar.setProgress(0);
        webLoadingBar.setVisibility(View.GONE);

        webView = (CustomWebView) findViewById(R.id.web_webview);
        webView.setWebViewListener(new CustomWebView.WebViewListener() {
            @Override
            public void pageStart() {
                webLoadingBar.setVisibility(View.VISIBLE);
                webLoadingBar.setProgress(0);
            }

            @Override
            public void pageFinish() {
                webLoadingBar.setVisibility(View.GONE);
            }

            @Override
            public void pageProgress(int progress) {
                webLoadingBar.setProgress(progress);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(webView.canGoBack()){
                webView.goBack();
            }else{
                goBack();
            }
            return true;
        }

        return false;
    }

    public WebView getWebView(){
        return webView;
    }

    public TextView getTitleTv(){
        return title;
    }
}
