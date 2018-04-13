package com.gpcgldh.collegeapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class AfterLoginActivity extends AppCompatActivity {

    Button feedback,notification,passwordchange,newuser,delnot,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_afterlogin);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setTitle("S.R.S GPCG LUDHIANA");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.mainarrow);
        feedback=(Button)findViewById(R.id.button_feedback);
        notification=(Button)findViewById(R.id.buttonlogin2_n);
        passwordchange=(Button)findViewById(R.id.button_newpassword);
        newuser=(Button)findViewById(R.id.button_newuser);
        delnot=(Button)findViewById(R.id.button_deletenotif);
        logout=(Button)findViewById(R.id.button_logout);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AfterLoginActivity.this,FeedbackDisplayActivity.class);
                startActivity(i);
            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent (AfterLoginActivity.this,NotificationSubmitActivity.class);
                startActivity(i);
            }
        });
        passwordchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent (AfterLoginActivity.this,ChangePasswordActivity.class);
                startActivity(i);
            }
        });
        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent (AfterLoginActivity.this,NewUserActivity.class);
                startActivity(i);
            }
        });
        delnot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent (AfterLoginActivity.this,AdminNotActivity.class);
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });
    }
}
