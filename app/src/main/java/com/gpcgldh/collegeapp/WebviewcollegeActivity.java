package com.gpcgldh.collegeapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

public class WebviewcollegeActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webviewcollege);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_department);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setTitle("S.R.S GPCG LUDHIANA");
        toolbar.setNavigationIcon(R.drawable.mainarrow);
        toolbar.setTitleTextColor(Color.WHITE);
        String url="http://www.gpcgldh.ac.in/principal-desk.php";
        webView=(WebView)this.findViewById(R.id.webview_college);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.loadUrl(url);
    }
}
