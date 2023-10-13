package com.hack.easy2invest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView webView = findViewById(R.id.web);

        Bundle extras = getIntent().getExtras();
        String code = "WFC";
        if (extras != null) {
            code = extras.getString("code");
            // and get whatever type user account id is
        }

        // loading https://www.geeksforgeeks.org url in the WebView.
        webView.loadUrl("https://www.nyse.com/quote/XNYS:" + code);

        // this will enable the javascript.
        webView.getSettings().setJavaScriptEnabled(true);

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        webView.setWebViewClient(new WebViewClient());
    }
}