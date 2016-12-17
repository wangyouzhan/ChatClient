package com.imooc.wangyouzhan.chatclient.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.imooc.wangyouzhan.chatclient.R;

public class WebViewActivity extends AppCompatActivity {


    private WebView web_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        web_view = (WebView) findViewById(R.id.web_view);

        web_view.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        web_view.loadUrl("http://v.juhe.cn/toutiao/index?type=&key=d904a524b2822f6191ec745995e67f16");


    }
}


































