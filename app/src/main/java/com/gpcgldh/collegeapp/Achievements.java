package com.gpcgldh.collegeapp;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Achievements extends AppCompatActivity {

    LinearLayout academins,culture,sports,nss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_department);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setTitle("Achievements");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.mainarrow);
        culture=(LinearLayout) findViewById(R.id.textView72_cultural);
        sports=(LinearLayout) findViewById(R.id.textView73_sports);
        academins=(LinearLayout) findViewById(R.id.textView71_academins);
        nss=(LinearLayout)findViewById(R.id.textView74_nss);
        culture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Achievements.this,Cultural.class);
                startActivity(i);
            }
        });
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Achievements.this,Sports.class);
                startActivity(i);
            }
        });
        academins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Achievements.this,CoursesActivity.class);
                startActivity(i);

            }
        });
        nss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //http://www.gpcgldh.ac.in/nss.php
//                Uri uri = Uri.parse("http://www.gpcgldh.ac.in/nss.php");
//                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
//                startActivity(intent);
                Intent i=new Intent(Achievements.this,nss.class);
                startActivity(i);

            }
        });
    }
}
