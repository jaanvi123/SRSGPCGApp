package com.gpcgldh.collegeapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Cultural extends AppCompatActivity {

//    ListView listView;
//    ArrayList<String> arrayList;
    WebView webViewnss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultural);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_department);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setTitle("Cultural");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.mainarrow);
        String url="http://www.gpcgldh.ac.in/cultural.php";
        webViewnss=(WebView)this.findViewById(R.id.webview_cultural);
        webViewnss.getSettings().setJavaScriptEnabled(true);
        webViewnss.getSettings().setBuiltInZoomControls(true);
        webViewnss.loadUrl(url);
//        listView=(ListView)findViewById(R.id.listView_cultural);
//        arrayList=new ArrayList<>();
//        arrayList.add("•\tSOLO SONG secured the first position and got GOLD medal.");
//        arrayList.add("•\tFANCY DRESS got Silver Medal.");
//        arrayList.add("•\tSOLO DANCE got Silver Medal.");
//        arrayList.add("•\tGIDDHA team was awarded Bronze Medal.");
//        arrayList.add("•\tCHOREOGRAPHY team got Bronze Medal.");
//        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
//        listView.setAdapter(arrayAdapter);

    }
}
