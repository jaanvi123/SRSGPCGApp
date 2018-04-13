package com.gpcgldh.collegeapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Aboutappactivity extends AppCompatActivity {

    //Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutappactivity);

       // toolbar=(Toolbar)findViewById(R.id.toolbar_notification);
        //
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_appteam);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setTitle("App Team");
        toolbar.setNavigationIcon(R.drawable.mainarrow);
        toolbar.setTitleTextColor(Color.WHITE);
    }
}
