package com.gpcgldh.collegeapp;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class activityinfrastructure extends AppCompatActivity {

    ImageView left,right;
    TouchImageView galary;
    ArrayList<Integer> galaryimage;
    ArrayList<String> galarytext;
    TextView galaryinfo;
    int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityinfrastructure);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_department);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setTitle("Infrastructure");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.mainarrow);
        galary=(TouchImageView)findViewById(R.id.imageView2_galary);
        galaryinfo=(TextView)findViewById(R.id.textView71_infra);
        left=(ImageView)findViewById(R.id.imageView3_left);
        right=(ImageView)findViewById(R.id.imageView4_right);
        final ArrayList<Integer> galaryimage=new ArrayList<>();
        galaryimage.add(R.drawable.fdlab);
        galaryimage.add(R.drawable.gmtlad);
        galaryimage.add(R.drawable.mop);
        galaryimage.add(R.drawable.oslab);
        galaryimage.add(R.drawable.phylab);
        galaryimage.add(R.drawable.prolab);
        galaryimage.add(R.drawable.sclab);
        galaryimage.add(R.drawable.library);
        galary.setImageResource(galaryimage.get(index));
        final ArrayList<String> galarytext=new ArrayList<>();
        galarytext.add("Garment Construction Lab");
        galarytext.add("Garment Manufacturing Lab");
        galarytext.add("Computer Lab");
        galarytext.add("Network Lab");
        galarytext.add("Physics Lab");
        galarytext.add("Programming Lab");
        galarytext.add("Audio/Video Lab");
        galarytext.add("Library");
       galaryinfo.setText(galarytext.get(index));
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index!=0)
                {
                    index--;
                    imageViewAnimatedChange(activityinfrastructure.this,galary,galaryimage.get(index),galarytext.get(index));
//                    galary.setImageResource(galaryimage.get(index));
//                    galaryinfo.setText(galarytext.get(index));
                }
                else
                {
                    index=7;
                    imageViewAnimatedChange(activityinfrastructure.this,galary,galaryimage.get(index),galarytext.get(index));
//                    galary.setImageResource(galaryimage.get(index));
//                    galaryinfo.setText(galarytext.get(index));
                }
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index!=7)
                {
                    index++;
                    imageViewAnimatedChange(activityinfrastructure.this,galary,galaryimage.get(index),galarytext.get(index));
//                    galary.setImageResource(galaryimage.get(index));
//                    galaryinfo.setText(galarytext.get(index));
                }
                else
                {
                    index=0;
                    imageViewAnimatedChange(activityinfrastructure.this,galary,galaryimage.get(index),galarytext.get(index));
//                    galary.setImageResource(galaryimage.get(index));
//                    galaryinfo.setText(galarytext.get(index));
                }

            }
        });

    }
    public void imageViewAnimatedChange(Context c, final ImageView v, final int new_image, final String text) {
        final Animation anim_out = AnimationUtils.loadAnimation(c, R.anim.fade_out);
        final Animation anim_in  = AnimationUtils.loadAnimation(c, R.anim.fade_in);
        anim_out.setAnimationListener(new Animation.AnimationListener()
        {
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationRepeat(Animation animation) {}
            @Override public void onAnimationEnd(Animation animation)
            {
                v.setImageResource(new_image);
                galaryinfo.setText(text);
                v.startAnimation(anim_in);
            }
        });
        v.startAnimation(anim_out);
    }

}
