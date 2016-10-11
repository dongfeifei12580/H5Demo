package com.bc.h5demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private WebView mWebView;
    private Button mButton;
    private WebAppInterface mWebAppInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (WebView) findViewById(R.id.wv_main);
        mButton = (Button) findViewById(R.id.bt_main);
        mWebAppInterface=new WebAppInterface(this);
        mWebView.loadUrl("file:///android_asset/index.html");
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new WebAppInterface(this), "app");
        mButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        mWebAppInterface.showName("I while be back!");
    }

    class WebAppInterface {
        private Context mContext;

        public WebAppInterface(Context mContext) {
            this.mContext = mContext;
        }

        @JavascriptInterface
        public void sayHello(String name) {
            Toast.makeText(mContext, "name=" + name, Toast.LENGTH_LONG).show();
        }

        public void showName(String name) {
            mWebView.loadUrl("javascript:showName('" + name + "')");
        }

    }
}
