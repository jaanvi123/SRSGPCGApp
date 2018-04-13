package com.gpcgldh.collegeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    ImageView imageViewcollege;
    Handler h;
    Runnable r;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewcollege=(ImageView)findViewById(R.id.imageView2_college);
        r=new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,NavigationActivity.class);
                startActivity(intent);
                finish();
            }
        };
        h=new Handler();
        new MyTask().execute();
        Intent servIntent = new Intent(this,NotificationService.class);
        startService(servIntent);
//        preferences = getSharedPreferences(getString(R.string.preference_name),MODE_PRIVATE);
//        if(preferences.getBoolean(getString(R.string.key_first_run),true)){
//            //db initialize
//            editor = preferences.edit();
//            editor.putBoolean(getString(R.string.key_first_run),false);
//            editor.apply();
//            h.postDelayed(r, 2000);
//        }
//        else {
//
//            h.postDelayed(r, 3000);
//        }
    }

    public class MyTask extends AsyncTask<String,String,Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            // return null;
            try {
                File file = new File(MainActivity.this.getFilesDir().getAbsolutePath().replace("files", "databases") + "/achievements");
                if (!file.exists()) {
                    Log.d("log_tag", "decompressing");
                    Decompress.unzipFromAssets(MainActivity.this, "achievements.zip");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;

            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (!aBoolean) {
                Toast.makeText(MainActivity.this,"Files doesn't install properly!!",Toast.LENGTH_LONG).show();
                // showError
            }
            else{
                h.postDelayed(r, 2000);
            }
        }

    }

}
