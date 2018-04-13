package com.gpcgldh.collegeapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NotificationSubmitActivity extends AppCompatActivity {

    EditText notification;
    Button submitbutton;
    String sendnotification;
    ArrayList<NameValuePair> list;
    ProgressDialog progressDialog;
    AlertDialog.Builder builder,builders,buildern;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_submit);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_notification);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setTitle("Notification");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.mainarrow);
        notification=(EditText)findViewById(R.id.edittext_notificationsubmit);
        submitbutton=(Button)findViewById(R.id.button2_notification);
        builder=new AlertDialog.Builder(this);
        builder.setMessage("Please fill the field!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builders=new AlertDialog.Builder(this);
        builders.setMessage("Data Submitted");
        builders.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        buildern=new AlertDialog.Builder(this);
        buildern.setMessage("No Network Connection");
        buildern.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (notification.length()==0)
                {
                    AlertDialog dialog=builder.create();
                    dialog.show();
                }

                else {
                    sendnotification = notification.getText().toString();
                    NotificationSubmitClass notificationSubmitClass = new NotificationSubmitClass();
                    notificationSubmitClass.execute(sendnotification);
                }
            }
        });
    }
    void show()
    {
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("LOADING...");
        progressDialog.show();
    }
    public class NotificationSubmitClass extends AsyncTask<String,Integer,Boolean>
    {

        @Override
        protected Boolean doInBackground(String... params) {
            list=new ArrayList<>();
            list.add(new BasicNameValuePair("sendnotification",params[0]));
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost("http://112.196.5.135:8081/collegeapp/noificationsubmit.php");
            try
            {
                httpPost.setEntity(new UrlEncodedFormEntity(list));
                HttpResponse httpResponse=httpClient.execute(httpPost);
                HttpEntity httpEntity=httpResponse.getEntity();
                InputStream responseget;
                responseget=httpEntity.getContent();
                InputStreamReader inputStreamReader=new InputStreamReader(responseget);
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String getline;
                StringBuilder sb=new StringBuilder();
                while((getline=bufferedReader.readLine())!=null)
                {
                    sb.append(getline);
                }
                String data=sb.toString();
                Log.d("log_response",data);
                JSONObject json=new JSONObject(data);
                String r = json.getString("response");
                if(r.equalsIgnoreCase("insert")) {
                    return true;
                }

            }
            catch (Exception e)
            {
                Log.d("Log_Exception",e.toString());
            }
            return false;
        }

        @Override
        protected void onPreExecute() {

            show();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            progressDialog.dismiss();
            if(aBoolean){
                //Toast.makeText(NotificationSubmitActivity.this,"Inserted",Toast.LENGTH_SHORT).show();
                AlertDialog dialog=builders.create();
                dialog.show();
            }
            else
            {
               // Toast.makeText(NotificationSubmitActivity.this,"Not Inserted",Toast.LENGTH_SHORT).show();
                AlertDialog dialog=buildern.create();
                dialog.show();
            }
            super.onPostExecute(aBoolean);
        }
    }
}
