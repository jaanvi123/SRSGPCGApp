package com.gpcgldh.collegeapp;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.security.Principal;
import java.util.ArrayList;

public class Aboutus extends AppCompatActivity {

    LinearLayout pd,ad,vision,committee;
    CardView principal,admission,cvvision,cvcommittee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_department);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setTitle("About Us");
        toolbar.setNavigationIcon(R.drawable.mainarrow);
        toolbar.setTitleTextColor(Color.WHITE);
       pd=(LinearLayout) findViewById(R.id.textView71_PD);
        ad=(LinearLayout) findViewById(R.id.textView72_ad);
        vision=(LinearLayout) findViewById(R.id.textView73_vm);
        committee=(LinearLayout) findViewById(R.id.textView74_grc);
        pd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Aboutus.this,principal.class);
                startActivity(i);
            }
        });

        ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Aboutus.this,admission.class);
                startActivity(i);

            }
        });
        vision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Aboutus.this,vision.class);
                startActivity(i);

            }
        });
        committee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Aboutus.this,committee.class);
                startActivity(i);
//                Uri uri = Uri.parse("http://www.gpcgldh.ac.in/grievance.php");
//                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
//                startActivity(intent);

            }
        });
    }
}
