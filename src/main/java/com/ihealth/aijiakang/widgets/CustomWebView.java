package com.ihealth.aijiakang.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by YanJiaqi on 16/1/5
 */
public class CustomWebView extends WebView{
    private WebViewListener mWebViewListener = null;

    public CustomWebView(Context context) {
        super(context);
        initParams(context);
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initParams(context);
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams(context);
    }

    private void initParams(Context context){
        this.clearCache(true);
        this.clearHistory();
        this.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        this.getSettings().setJavaScriptEnabled(true);
        this.getSettings().setTextZoom(100);
        this.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(mWebViewListener!=null){
                    mWebViewListener.pageFinish();
                }
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if(mWebViewListener!=null){
                    mWebViewListener.pageStart();
                }
            }

        });
        this.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (mWebViewListener != null) {
                    mWebViewListener.pageProgress(newProgress);
                }
            }
        });
    }

    public interface WebViewListener{
        public void pageStart();
        public void pageFinish();
        public void pageProgress(int progress);
    }

    public void setWebViewListener(WebViewListener mWebViewListener){
        this.mWebViewListener = mWebViewListener;
    }
}
