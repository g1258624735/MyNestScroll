package com.lmj.com.mynestscroll;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);
        WebView web = findViewById(R.id.web);
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        web.addJavascriptInterface(new MyClass(), "test");
        web.loadUrl("file:///android_asset/1.html");
        web.setWebViewClient(new WebViewClient());
        web.setWebChromeClient(new WebChromeClient());
    }

    public class MyClass extends Object {
        @JavascriptInterface
        public void test() {
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
            new AlertDialog.Builder(WebActivity.this).setTitle("nihao").setMessage("我是js弹出框").create().show();
//                }
//            },300);

        }
    }

}
