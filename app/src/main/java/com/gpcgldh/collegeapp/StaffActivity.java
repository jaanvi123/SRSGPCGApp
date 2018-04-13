package com.gpcgldh.collegeapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

public class StaffActivity extends AppCompatActivity {

    WebView webViewnss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_department);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setTitle("Our Faculty");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.mainarrow);
        Intent i=getIntent();
        String url=i.getStringExtra("url");
      //  String url="http://www.gpcgldh.ac.in/faculty_department.php";
        webViewnss=(WebView)this.findViewById(R.id.webview_staff);
        webViewnss.getSettings().setJavaScriptEnabled(true);
        webViewnss.getSettings().setBuiltInZoomControls(true);
        webViewnss.loadUrl(url);
    }
}
