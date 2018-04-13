package com.gpcgldh.collegeapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ECE extends AppCompatActivity {

   // ListView listView;
    //ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ece);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_department);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setTitle("E.C.E");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.mainarrow);
//        listView=(ListView)findViewById(R.id.listView3_ece);
//        arrayList=new ArrayList<>();
//        arrayList.add("NAME OF THE LAB");
//        arrayList.add("T.V ENGG. LAB");
//        arrayList.add("COMMUNICATION LAB");
//        arrayList.add("INSTRUMENTATION & P.L.C LAB");
//        arrayList.add("DIGITAL ELECTRONICS AND MICROPROCESSOR LAB");
//        arrayList.add("ELECTRICAL MACHINE LAB");
//        arrayList.add("BASIC ELECTRONICS LAB");
//        arrayList.add("COMPUTER LAB");
//        arrayList.add("PROJECT LAB");
//        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
//        listView.setAdapter(arrayAdapter);

    }
}
