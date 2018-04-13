package com.gpcgldh.collegeapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

public class nss extends AppCompatActivity {

    WebView webViewnss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nss);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_department);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setTitle("N.S.S");
        toolbar.setNavigationIcon(R.drawable.mainarrow);
        toolbar.setTitleTextColor(Color.WHITE);
        String url="http://www.gpcgldh.ac.in/nss.php";
        webViewnss=(WebView)this.findViewById(R.id.webview_nss);
        webViewnss.getSettings().setJavaScriptEnabled(true);
        webViewnss.getSettings().setBuiltInZoomControls(true);
        webViewnss.loadUrl(url);
        //https://www.google.co.in/maps/uv?hl=en&pb=!1s0x391a83bc00218a35:0xb7f08d3dc1221227!2m19!2m2!1i80!2i80!3m1!2i20!16m13!1b1!2m2!1m1!1e1!2m2!1m1!1e3!2m2!1m1!1e5!2m2!1m1!1e4!3m1!7e115!4shttps://picasaweb.google.com/lh/sredir?uname%3D103942422267413185642%26id%3D6339775475113177122%26target%3DPHOTO!5ssrs+college+ludhiana+-+Google+Search&imagekey=!1e3!2s-psAlO6DArgE/V_toNH3asCI/AAAAAAAAAE0/0YXBNlYZajQLwxdASZ-xHsM5III_ZbWLQCJkC&sa=X&ved=0ahUKEwjp2Mnzt9zSAhXLnZQKHY3PB68QoioIeTAO
    }
}
