package com.gpcgldh.collegeapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class vision extends AppCompatActivity {

    TextView visiontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vision);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_department);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setTitle("Vision & Mission");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.mainarrow);
        visiontext=(TextView)findViewById(R.id.textView5_v);
        visiontext.setText("• To make available appropriate women technicians to the industry including the manpower in the high technology, new and emerging technology areas as per their demand.\n •\tTo impart vocational skills to the women of the state with a view to create equality and transform the lives for the benefit of the society in a diverse nation.\n •\tTo help increase the overall productivity and economy of the state by empowering women to become equal partners in all technological, managerial and decision making community development activities. ");
    }
}
