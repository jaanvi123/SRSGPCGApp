package com.gpcgldh.collegeapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AcademicsActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    List<Integer> years;
    Map<Integer, AchievementsData> mapStudentData;
    MyExpandablelist listAdapter;
    AcademicsDatabase db;
    String coursename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academics);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_academics);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //coursesgv=(GridView)findViewById(R.id.gridView_courses);
        setTitle("Academic Roll Of Honour");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.mainarrow);
        expandableListView=(ExpandableListView)findViewById(R.id.expandableListView_main);
        Intent i=getIntent();
        coursename=i.getStringExtra("c_name");
        Log.d("Log_Name",coursename);
        db = new AcademicsDatabase(this);
        fillData();


    }
    public void fillData()
    {
        years=db.getYears(coursename);
        Log.d("size",""+years.size());
        mapStudentData=new HashMap<>();
        for(int i=0;i<years.size();i++){
            AchievementsData d = new AchievementsData();
            d = db.getAchievementsData(coursename,years.get(i));
            mapStudentData.put(years.get(i),d);
        }
        listAdapter= new MyExpandablelist(this,years,mapStudentData);
        expandableListView.setAdapter(listAdapter);


    }
}

