package com.gpcgldh.collegeapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class galaryactivity extends AppCompatActivity {

    ImageView left,right;
    TouchImageView galary;
    ArrayList<Integer> galaryimage;
    ArrayList<String> galarytext;
    TextView galaryinfo;
    int index = 0;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galaryactivity);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_department);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setTitle("Gallery");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.mainarrow);
        String url="http://www.gpcgldh.ac.in/infrastructure.php";
        webView=(WebView)this.findViewById(R.id.webview_galary);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.loadUrl(url);
//        galary=(TouchImageView) findViewById(R.id.imageView2_galary);
//        galaryinfo=(TextView)findViewById(R.id.textView71_galary);
//        left=(ImageView)findViewById(R.id.imageView4_left);
//        right=(ImageView)findViewById(R.id.imageView3_right);
//        final ArrayList<Integer> galaryimage=new ArrayList<>();
//        galaryimage.add(R.drawable.candle);
//        galaryimage.add(R.drawable.dance);
//        galaryimage.add(R.drawable.function);
//        galaryimage.add(R.drawable.prize);
//        galaryimage.add(R.drawable.athletics);
//        galary.setImageResource(galaryimage.get(index));
//        final ArrayList<String> galarytext=new ArrayList<>();
//        galarytext.add("first");
//        galarytext.add("second");
//        galarytext.add("third");
//        galarytext.add("fourth");
//        galarytext.add("fifth");
//        galaryinfo.setText(galarytext.get(index));
//        left.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                 if(index!=0)
//                 {
//                     index--;
//                     imageViewAnimatedChange(galaryactivity.this,galary,galaryimage.get(index),galarytext.get(index));
////                     galary.setImageResource(galaryimage.get(index));
////                     galaryinfo.setText(galarytext.get(index));
//                 }
//                else
//                 {
//                     index=4;
//                     imageViewAnimatedChange(galaryactivity.this,galary,galaryimage.get(index),galarytext.get(index));
////                     galary.setImageResource(galaryimage.get(index));
////                     galaryinfo.setText(galarytext.get(index));
//                 }
//            }
//        });
//        right.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(index!=4)
//                {
//                    index++;
//                    imageViewAnimatedChange(galaryactivity.this,galary,galaryimage.get(index),galarytext.get(index));
////                    galary.setImageResource(galaryimage.get(index));
////                    galaryinfo.setText(galarytext.get(index));
//                }
//                else
//                {
//                  index=0;
//                    imageViewAnimatedChange(galaryactivity.this,galary,galaryimage.get(index),galarytext.get(index));
////                    galary.setImageResource(galaryimage.get(index));
////                    galaryinfo.setText(galarytext.get(index));
//                }
//
//            }
//        });
//    }
//    public void imageViewAnimatedChange(Context c, final ImageView v, final int new_image, final String text) {
//        final Animation anim_out = AnimationUtils.loadAnimation(c, R.anim.fade_out);
//        final Animation anim_in  = AnimationUtils.loadAnimation(c, R.anim.fade_in);
//        anim_out.setAnimationListener(new Animation.AnimationListener()
//        {
//            @Override public void onAnimationStart(Animation animation) {}
//            @Override public void onAnimationRepeat(Animation animation) {}
//            @Override public void onAnimationEnd(Animation animation)
//            {
//                v.setImageResource(new_image);
//                galaryinfo.setText(text);
//                v.startAnimation(anim_in);
//            }
//        });
//        v.startAnimation(anim_out);
    }
}
