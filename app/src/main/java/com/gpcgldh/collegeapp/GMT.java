package com.gpcgldh.collegeapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class GMT extends AppCompatActivity {
//    ListView listView;
//    ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmt);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_department);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setTitle("G.M.T");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.mainarrow);
//        listView=(ListView)findViewById(R.id.listView_gmt);
//        arrayList=new ArrayList<>();
//        arrayList.add("NAME OF THE LAB");
//        arrayList.add("CONSTRUCTION LAB I");
//        arrayList.add("CONSTRUCTION LAB II");
//        arrayList.add("INDUSTRIAL LAB");
//        arrayList.add("COMPUTER LAB");
//        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
//        listView.setAdapter(arrayAdapter);


    }
}
