package com.gpcgldh.collegeapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AppliedScience extends AppCompatActivity {

   // ListView listView;
    ArrayList<String> arrayList;
    TextView nol,pl,cl,ecl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applied_science);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_department);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setTitle("Applied Science");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.mainarrow);
//        listView=(ListView)findViewById(R.id.listView_as);
//        arrayList=new ArrayList<>();
//        arrayList.add("NAME OF THE LAB");
//        arrayList.add("PHYSICS LAB");
//        arrayList.add("CHEMISTRY LAB");
//        arrayList.add("ENGLISH COMMUNICATION LAB");
//        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
//        listView.setAdapter(arrayAdapter);
        nol=(TextView)findViewById(R.id.textView5_nol);
    }
}
